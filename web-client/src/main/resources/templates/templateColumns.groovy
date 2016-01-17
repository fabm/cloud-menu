def applyCollection(ident='',Collection collection, Closure<?> closure) {
    StringBuilder sb = new StringBuilder()
    int i = 0
    for (item in collection) {
        if (i!=0){
            sb<<ident
        }
        sb << closure.call(item, i++)
    }
    sb.toString()
}

def applyEntity(name, label, index) {
     """TextColumn<${type}> ${name} = new TextColumn<${type}>() {
            @Override
            public String getValue(${type} ${typeVar}) {
                return ${typeVar}.get${name.capitalize()}();
            }
        };
        ${name}.setSortable(true);
        dataGrid.addColumn(${name}, "${label}");
        dataGrid.getHeader($index).setHeaderStyleNames("table-header");
"""
}

def applySorter(name){
     """sortHandler.setComparator(${name}, new Comparator<${type}>() {
            @Override
            public int compare(${type} ${typeVar}1, ${type} ${typeVar}2) {
                return ${typeVar}1.get${name.capitalize()}().compareTo(${typeVar}2.get${name.capitalize()}());
            }
        });
"""
}

def applyPaginationImports(){
    if (!initializePager){
        return ''
    }
     '''
import org.gwtbootstrap3.client.ui.Pagination;
import com.google.gwt.user.cellview.client.SimplePager;'''
}

def applyPaginationFields(){
    if (!initializePager){
        return ''
    }
     """
    private SimplePager dataGridPager = new SimplePager();
    private DataGrid<${type}> dataGrid;
    private ListDataProvider<${type}> listDataProvider;
"""
}

def applyPaginationLocal(){
    if (!initializePager){
        return ''
    }
     """
        this.dataGrid = dataGrid;
        this.listDataProvider = listDataProvider;
"""
}

def applyPaginationMethod(){
    if (!initializePager){
        return ''
    }
    '''
    @Override
    public void initializePager(Pagination pagination){
        dataGridPager.setDisplay(dataGrid);
        pagination.clear();
        listDataProvider.addDataDisplay(dataGrid);
        listDataProvider.flush();
        pagination.rebuild(dataGridPager);
    }
'''
}



"""\
package ${packageInit};

import pt.ipg.ei.cloud.menu.client.columns.ColumnInitializer;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.DefaultHeaderOrFooterBuilder;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.ListDataProvider;
import org.gwtbootstrap3.client.ui.gwt.DataGrid;${applyPaginationImports()}
import ${typeImported};

import java.util.Comparator;

public class ${typeImp} implements ${subType} {
    ${applyPaginationFields()}

    @Override
    public void initializeColumns(DataGrid<${type}> dataGrid, ListDataProvider<${type}> listDataProvider) {
        ${applyPaginationLocal()}
        dataGrid.setHeaderBuilder(new DefaultHeaderOrFooterBuilder<${type}>(dataGrid,false) {
            {{
                setSortIconStartOfLine(false);
            }}
        });

        ${applyCollection '\t\t',entities,{m,i-> applyEntity m.name,m.label, i}}

        ColumnSortEvent.ListHandler<${type}> sortHandler = new ColumnSortEvent.ListHandler<${type}>(listDataProvider.getList());

        ${applyCollection '\t\t',entities,{m,i-> applySorter m.name}}

        dataGrid.addColumnSortHandler(sortHandler);
    }
    ${applyPaginationMethod()}

}
"""