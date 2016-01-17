def applyCollection(ident='',Collection collection, Closure<?> closure) {
    StringBuilder sb = new StringBuilder()
    boolean isFirst = true
    for (item in collection) {
        if (!isFirst){
            sb<<ident
        }
        isFirst = false
        sb << closure.call(item)
    }
    sb.toString()
}

def applyEntity(name, label) {
     """TextColumn<${type}> ${name} = new TextColumn<${type}>() {
            @Override
            public String getValue(${type} ${typeVar}) {
                return ${typeVar}.get${name.capitalize()}();
            }
        };
        ${name}.setSortable(true);
        dataGrid.addColumn(${name}, "${label}");
"""
}

def applySorter(name){
     """sortHandler.setComparator(${type}, new Comparator<${type}>() {
            @Override
            public int compare(${type} ${typeVar}1, ${type} ${typeVar}2) {
                return ${typeVar}1.get${name.capitalize()}().compareTo(${typeVar}2.get${name.capitalize()}());
            }
        });
"""
}

"""\
package ${packageVar};

import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.DefaultHeaderOrFooterBuilder;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.ListDataProvider;
import org.gwtbootstrap3.client.ui.gwt.DataGrid;
import ${typeImported};

import java.util.Comparator;

public class ${type}DatagridInitializer implements ColumnInitializer<${type}> {

    @Override
    public void initializeColumns(DataGrid<${type}> dataGrid, ListDataProvider<${type}> listDataProvider) {
        dataGrid.setHeaderBuilder(new DefaultHeaderOrFooterBuilder<${type}>(dataGrid,false) {
            {{
                setSortIconStartOfLine(false);
            }}
        });

        ${applyCollection '\t\t',entities,{applyEntity it.name,it.label}}

        ColumnSortEvent.ListHandler<${type}> sortHandler = new ColumnSortEvent.ListHandler<${type}>(listDataProvider.getList());

        ${applyCollection '\t\t',entities,{applySorter it.name}}

        dataGrid.addColumnSortHandler(sortHandler);

    }
}
"""