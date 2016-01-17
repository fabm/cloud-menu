package pt.ipg.ei.cloud.menu.client.panels;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.validation.client.impl.Validation;
import org.fusesource.restygwt.client.*;
import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.Form;
import org.gwtbootstrap3.client.ui.TextBox;
import pt.ipg.ei.cloud.menu.client.model.MyObject;
import pt.ipg.ei.cloud.menu.client.service.TestService;
import pt.ipg.ei.cloud.menu.client.storage.StorageEvent;
import pt.ipg.ei.cloud.menu.client.storage.StorageEventHandler;
import pt.ipg.ei.cloud.menu.client.storage.StorageEventUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Panel2 extends Composite implements Editor<MyObject> {
    interface Binder extends UiBinder<Widget, Panel2> {
    }

    private static Binder uiBinder = GWT.create(Binder.class);

    public Panel2() {
        initWidget(uiBinder.createAndBindUi(this));


    }


    @UiHandler("btSend")
    public void onSendClick(ClickEvent event) {

    }


}
