package pt.ipg.ei.cloud.menu.client.panels;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;
import org.gwtbootstrap3.client.ui.Pagination;
import org.gwtbootstrap3.client.ui.gwt.DataGrid;
import pt.ipg.ei.cloud.menu.client.columns.PessoaDatagridInitializer;
import pt.ipg.ei.cloud.menu.shared.model.Pessoa;

import java.util.Iterator;
import java.util.logging.Logger;

public class Panel1 extends Composite {
    private final ListDataProvider<Pessoa> listDataProvider;

    interface MyUiBinder extends UiBinder<Widget, Panel1> {
    }

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
    @UiField(provided = true)
    DataGrid<Pessoa> pessoaDataGrid = new DataGrid<Pessoa>(10);

    @UiField
    Pagination dataGridPagination;

    public Panel1() {
        initWidget(uiBinder.createAndBindUi(this));

        listDataProvider = new ListDataProvider<Pessoa>();
        for (int i = 0; i < 30; i++) {
            listDataProvider.getList().add(new Pessoa(""+('a'+i)+('a'+i), ""+('z'-i)+('z'-i)));
        }

        final MultiSelectionModel<Pessoa> selectionModel = new MultiSelectionModel<Pessoa>();
        pessoaDataGrid.setSelectionModel(selectionModel);
        PessoaDatagridInitializer cip = GWT.create(PessoaDatagridInitializer.class);

        cip.initializeColumns(pessoaDataGrid,listDataProvider);
        cip.initializePager(dataGridPagination);

        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                StringBuilder sb = new StringBuilder();
                Iterator<Pessoa> it = selectionModel.getSelectedSet().iterator();
                while (it.hasNext()) {
                    sb.append("Nome:"+it.next().getNome());
                    if (it.hasNext()) {
                        sb.append(',');
                    }
                }
                Logger.getLogger("current").info("is selec:" + sb.toString());
            }
        });

    }

    private void changeHistory(ValueChangeEvent<String> event) {
        Logger.getLogger("current").info("hisory value:"+event.getValue());
    }


}
