package pt.ipg.ei.cloud.menu.client.panels;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.validation.client.impl.Validation;
import org.fusesource.restygwt.client.*;
import org.gwtbootstrap3.client.ui.Form;
import org.gwtbootstrap3.client.ui.TextArea;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.base.form.AbstractForm;
import pt.ipg.ei.cloud.menu.client.nativejs.*;
import pt.ipg.ei.cloud.menu.client.service.RestaurantService;
import pt.ipg.ei.cloud.menu.client.translations.Translations;
import pt.ipg.ei.cloud.menu.shared.model.Pessoa;
import pt.ipg.ei.cloud.menu.shared.model.restaurant.RestaurantRegisterRequest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Logger;

public class RestaurantRegisterPanel extends Composite implements Editor<RestaurantRegisterRequest> {

    interface MyUiBinder extends UiBinder<Widget, RestaurantRegisterPanel> {
    }

    interface EditorDriver extends SimpleBeanEditorDriver<RestaurantRegisterRequest, RestaurantRegisterPanel> {
    }

    interface RestaurantRegisterRequestEnc extends JsonEncoderDecoder<RestaurantRegisterRequest> {
    }


    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField
    TextBox name;

    @UiField
    TextBox outIp;

    @UiField
    TextArea description;

    @UiField
    TextArea address;

    @UiField
    InputElement cPhoto;

    @UiField
    Form form;


    private EditorDriver driver = GWT.create(EditorDriver.class);
    private Translations translations = GWT.create(Translations.class);


    public RestaurantRegisterPanel() {
        initWidget(uiBinder.createAndBindUi(this));
        InitComponents.upload();
        driver.initialize(this);

        Button bt = new Button();
        bt.setText("photo button");
        bt.ensureDebugId("ph");
        form.add(bt);

    }

    @Override
    protected void onAttach() {
        super.onAttach();
        driver.edit(new RestaurantRegisterRequest());
    }

    @UiHandler(value = "btRestRegister")
    void registerClick(ClickEvent clickEvent) {
        Logger.getLogger("--").info(cPhoto.getValue());

        final Resource resource = new Resource(GWT.getHostPageBaseURL() + "upload");
        try {
            resource.get().send(new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {

                }

                @Override
                public void onError(Request request, Throwable exception) {

                }
            });
            if (true) {
                authorizationFailure();
                return;
            }
            resource.get().send(new JsonCallback() {
                @Override
                public void onFailure(Method method, Throwable exception) {

                }

                @Override
                public void onSuccess(Method method, JSONValue response) {
                    FormData formData = FormData.create();
                    formData.addFileInput(cPhoto);

                    final String url = response.isObject().get("url").isString().stringValue();
                    GWT.log(url);
                    XHR xhr = GWT.create(XHR.class);
                    xhr.post(url, formData, new OnPostListener() {
                        @Override
                        public void posted(String result) {
                            Logger.getLogger("-->").info(result);
                        }
                    });

                }
            });
        } catch (RequestException e) {
            e.printStackTrace();
        }
        RestaurantRegisterRequest restaurantRegisterRequest = driver.flush();


        final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<RestaurantRegisterRequest>> constraints = validator.validate(restaurantRegisterRequest);
        if (!constraints.isEmpty()) {
            driver.setConstraintViolations(new ArrayList<ConstraintViolation<?>>(constraints));
        } else {
            save(restaurantRegisterRequest);
        }

    }

    private void authorizationFailure() {
        FileRef.create(cPhoto, new FileRefCreator() {
            @Override
            public void create(FileRef fileRef) {
                final Storage storage = Storage.getSessionStorageIfSupported();
                storage.setItem("file", JsonUtils.stringify(fileRef));
                final RestaurantRegisterRequestEnc encoder = GWT.create(RestaurantRegisterRequestEnc.class);
                storage.setItem("restaurantRegister", encoder.encode(driver.flush()).toString());
                storage.setItem("action-server", "register-restaurant");
                GWT.log(fileRef.createBase64Url());
            }
        });
    }


    private void save(RestaurantRegisterRequest restaurantRegisterRequest) {

        RestaurantService service = GWT.create(RestaurantService.class);
        final Resource resource = new Resource(GWT.getHostPageBaseURL() + "gtest");
        ((RestServiceProxy) service).setResource(resource);

        form.addSubmitCompleteHandler(new AbstractForm.SubmitCompleteHandler() {
            @Override
            public void onSubmitComplete(AbstractForm.SubmitCompleteEvent event) {
                Logger.getLogger("complete").info(event.getResults());
            }
        });


    }

}
