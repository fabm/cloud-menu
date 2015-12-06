package pt.ipg.ei.cloud.menu.client.panels;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.RangeChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent;
import org.gwtbootstrap3.client.ui.Pagination;
import org.gwtbootstrap3.client.ui.gwt.DataGrid;
import pt.ipg.ei.cloud.menu.client.module.Pessoa;

import java.util.Comparator;
import java.util.Iterator;
import java.util.logging.Logger;

public class Panel1 extends Composite {
    private final ColumnSortEvent.ListHandler<Pessoa> sortHandler;
    private final ListDataProvider<Pessoa> listDataProvider;

    interface MyUiBinder extends UiBinder<Widget, Panel1> {
    }

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
    @UiField(provided = true)
    DataGrid<Pessoa> pessoaDataGrid = new DataGrid<Pessoa>(10);

    @UiField
    Pagination dataGridPagination;

    private SimplePager dataGridPager = new SimplePager();

    public Panel1() {
        initWidget(uiBinder.createAndBindUi(this));

        TextColumn<Pessoa> nome = new TextColumn<Pessoa>() {
            @Override
            public String getValue(Pessoa pessoa) {
                return pessoa.getNome();
            }
        };
        nome.setSortable(true);
        pessoaDataGrid.addColumn(nome, "t-nome");

        TextColumn<Pessoa> sobreNome = new TextColumn<Pessoa>() {
            @Override
            public String getValue(Pessoa pessoa) {
                return pessoa.getSobrenome();
            }
        };
        sobreNome.setSortable(true);
        pessoaDataGrid.addColumn(sobreNome, "t-sobrenome");

        pessoaDataGrid.addRangeChangeHandler(new RangeChangeEvent.Handler() {

            @Override
            public void onRangeChange(final RangeChangeEvent event) {
                dataGridPagination.rebuild(dataGridPager);
            }
        });


        listDataProvider = new ListDataProvider<Pessoa>();
        listDataProvider.getList().add(new Pessoa("aa", "aa"));
        listDataProvider.getList().add(new Pessoa("bb", "bb"));
        listDataProvider.getList().add(new Pessoa("cc", "cc"));
        listDataProvider.getList().add(new Pessoa("dd", "dd"));

        final MultiSelectionModel<Pessoa> selectionModel = new MultiSelectionModel<Pessoa>();
        pessoaDataGrid.setSelectionModel(selectionModel);

        sortHandler = new ColumnSortEvent.ListHandler<Pessoa>(listDataProvider.getList());
        sortHandler.setComparator(nome, new Comparator<Pessoa>() {
            @Override
            public int compare(Pessoa o1, Pessoa o2) {
                return o1.getNome().compareTo(o2.getNome());
            }
        });
        sortHandler.setComparator(sobreNome, new Comparator<Pessoa>() {
            @Override
            public int compare(Pessoa o1, Pessoa o2) {
                return o1.getSobrenome().compareTo(o2.getSobrenome());
            }
        });

        pessoaDataGrid.addColumnSortHandler(sortHandler);
        dataGridPager.setDisplay(pessoaDataGrid);
        if(dataGridPagination == null){
            throw new IllegalStateException("why is null");
        }
        dataGridPagination.clear();
        listDataProvider.addDataDisplay(pessoaDataGrid);

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

        listDataProvider.flush();
        dataGridPagination.rebuild(dataGridPager);

    }

    private void changeHistory(ValueChangeEvent<String> event) {
        Logger.getLogger("current").info("hisory value:"+event.getValue());
    }


}
