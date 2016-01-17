package pt.ipg.ei.cloud.menu.client.translations;

import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.i18n.client.LocalizableResource;
import com.google.gwt.i18n.client.Messages;

@LocalizableResource.DefaultLocale("pt")
public interface ValidationMessages extends ConstantsWithLookup {

    String NULL_MESSAGE = "javax.validation.constraints.NotNull.message";
    String OUT_IP = "javax.validation.constraints.Pattern.outIp.message";

    @Key(NULL_MESSAGE)
    String nullMessage();

    @Key(OUT_IP)
    String outIpMessage();
}
