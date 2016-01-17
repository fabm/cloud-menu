package pt.ipg.ei.cloud.menu.client.translations;

import com.google.gwt.core.client.GWT;

import java.util.HashMap;
import java.util.Map;

public class ValidationMessagesMapper {
    private static ValidationMessagesMapper validationMessagesMapper;
    public static ValidationMessagesMapper get(){
        if(validationMessagesMapper == null){
            validationMessagesMapper = new ValidationMessagesMapper();
        }
        return validationMessagesMapper;
    }
    private ValidationMessages messages = GWT.create(ValidationMessages.class);
    private Map<String,String> map;

    public ValidationMessagesMapper(){
        map = new HashMap<String, String>();
        map.put(ValidationMessages.NULL_MESSAGE,messages.nullMessage());
        map.put(ValidationMessages.OUT_IP,messages.outIpMessage());
    }

    public String getMessage(String key) {
        return map.get(key);
    }
}
