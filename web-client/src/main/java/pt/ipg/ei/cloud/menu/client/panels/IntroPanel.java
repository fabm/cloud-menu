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

public class IntroPanel extends Composite {

    interface MyUiBinder extends UiBinder<Widget, IntroPanel> {
    }

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);


    public IntroPanel() {
        initWidget(uiBinder.createAndBindUi(this));

    }

}
