package pt.ipg.ei.cloud.menu.client.columns;

import org.gwtbootstrap3.client.ui.Pagination;
import pt.ipg.ei.cloud.menu.rebind.Column;
import pt.ipg.ei.cloud.menu.rebind.ColumnDefinition;
import pt.ipg.ei.cloud.menu.rebind.SelectionModelType;
import pt.ipg.ei.cloud.menu.shared.model.Pessoa;

@ColumnDefinition(definitionFor = Pessoa.class, columns = {
        @Column(name = "nome", headerName = "Nome"),
        @Column(name = "sobrenome", headerName = "Sobre Nome")
}, selectionModel = SelectionModelType.SINGLE)
public interface PessoaDatagridInitializer extends ColumnInitializer<Pessoa> {
    void initializePager(Pagination pagination);
}
