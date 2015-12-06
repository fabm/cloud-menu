package pt.ipg.ei.cloud.menu.client.res;

import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.HasText;

public class TxtResource implements HasText, SafeHtml{



    @Override
    public String getText() {
        return "my string";
    }

    @Override
    public void setText(String text) {

    }

    @Override
    public String asString() {
        return "safe "+getText();
    }
}
