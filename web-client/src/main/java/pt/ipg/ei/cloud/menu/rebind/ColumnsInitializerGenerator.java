package pt.ipg.ei.cloud.menu.rebind;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import groovy.lang.Binding;
import groovy.lang.GString;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.gwtbootstrap3.client.ui.Pagination;
import pt.ipg.ei.cloud.menu.client.columns.ColumnInitializer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColumnsInitializerGenerator extends Generator {

    private static String deCapitalize(String cap) {
        return Character.toLowerCase(cap.charAt(0)) + cap.substring(1);
    }

    private static List<Map<String, String>> getEntities(Column[] methods) {
        List<Map<String, String>> returnObject = new ArrayList<Map<String, String>>();
        for (final Column column : methods) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("name", column.name());
            map.put("label", column.headerName());
            returnObject.add(map);
        }
        return returnObject;
    }

    private boolean isPagerMethodPresent(JClassType jClassType, JClassType paginationType) throws NotFoundException {
        JMethod method = jClassType.getMethod("initializePager", new JType[]{paginationType});
        return method != null;
    }

    @Override
    public String generate(TreeLogger logger, GeneratorContext context, String typeName) throws UnableToCompleteException {

        GroovyShell shell = new GroovyShell();
        Script script = shell.parse(new InputStreamReader(getClass().getResourceAsStream("/templates/templateColumns.groovy")));
        Binding binding = new Binding();
        String packageName = ColumnInitializer.class.getPackage().getName();
        final String typeImp;

        try {
            JClassType type = context.getTypeOracle().getType(typeName);
            ColumnDefinition columnDefinition = type.getAnnotation(ColumnDefinition.class);
            if (columnDefinition == null) {
                throw new IllegalStateException("misses the annotation @ColumnDefinition in top of class");
            }
            typeImp = type.getName() + "Imp";
            final Class<?> definitionFor = columnDefinition.definitionFor();
            binding.setVariable("subType", type.getName());
            binding.setVariable("typeImp", typeImp);
            binding.setVariable("packageInit", packageName);
            binding.setVariable("packageVar", definitionFor.getPackage().getName());
            binding.setVariable("type", definitionFor.getSimpleName());
            binding.setVariable("typeImported", definitionFor.getName());
            binding.setVariable("typeVar", deCapitalize(definitionFor.getSimpleName()));
            binding.setVariable("entities", getEntities(columnDefinition.columns()));
            final JClassType paginationType = context.getTypeOracle().getType(Pagination.class.getCanonicalName());
            binding.setVariable("initializePager", isPagerMethodPresent(type, paginationType));
        } catch (NotFoundException e) {
            logger.log(TreeLogger.Type.ERROR, "error", e);
            return null;
        }

        script.setBinding(binding);
        PrintWriter printWriter = context.tryCreate(logger, packageName, typeImp);
        if (printWriter != null) {
            GString source = (GString) script.run();
            try {
                source.writeTo(printWriter);
            } catch (IOException e) {
                logger.log(TreeLogger.Type.ERROR, "error", e);
            }
            context.commit(logger, printWriter);
        }
        return packageName + "." + typeImp;
    }
}
