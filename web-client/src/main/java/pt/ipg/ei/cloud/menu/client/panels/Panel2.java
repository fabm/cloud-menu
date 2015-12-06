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
    private final TestService service;

    interface Binder extends UiBinder<Widget, Panel2> {
    }

    private static Binder uiBinder = GWT.create(Binder.class);

    interface EditorDriver extends SimpleBeanEditorDriver<MyObject, Panel2> {
    }
    private EditorDriver driver = GWT.create(EditorDriver.class);


    @UiField
    TextBox tbName;
    @UiField
    Form form;



    public Panel2() {
        initWidget(uiBinder.createAndBindUi(this));
        driver.initialize(this);
        final Resource resource = new Resource(GWT.getHostPageBaseURL()+"_ah/spi");
        service = GWT.create(TestService.class);
        ((RestServiceProxy)service).setResource(resource);

        service.myServiceGet(new MethodCallback<MyObject>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Logger.getLogger("current").log(Level.SEVERE,"problem",exception);
            }

            @Override
            public void onSuccess(Method method, MyObject response) {
                driver.edit(response);
            }
        });



    }


    @UiHandler("btSend")
    public void onSendClick(ClickEvent event) {
        MyObject teste = driver.flush();

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<MyObject>> violations = validator.validate(teste);
        if (violations.size() > 0) {
            driver.setConstraintViolations(new ArrayList<ConstraintViolation<?>>(violations));
        }else{
            MyObject m = new MyObject();
            m.setTbName("aaa");

            service.myServicePost(m, new MethodCallback<MyObject>() {
                @Override
                public void onFailure(Method method, Throwable exception) {

                }

                @Override
                public void onSuccess(Method method, MyObject response) {
                    driver.edit(response);
                }
            });

        }

    }


}
