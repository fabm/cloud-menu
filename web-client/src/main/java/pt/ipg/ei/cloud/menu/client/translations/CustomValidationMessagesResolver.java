package pt.ipg.ei.cloud.menu.client.translations;

import com.google.gwt.validation.client.UserValidationMessagesResolver;

public class CustomValidationMessagesResolver implements UserValidationMessagesResolver {
    private ValidationMessagesMapper messagesMapper = ValidationMessagesMapper.get();

    @Override
    public String get(String key) {
        return messagesMapper.getMessage(key);
    }
}
