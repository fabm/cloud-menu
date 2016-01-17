package pt.ipg.ei.cloud.menu.client.columns;

import com.google.gwt.view.client.ListDataProvider;
import org.gwtbootstrap3.client.ui.gwt.DataGrid;

public interface ColumnInitializer<T> {
    void initializeColumns(DataGrid<T> dataGrid, ListDataProvider<T> listDataProvider);
}
