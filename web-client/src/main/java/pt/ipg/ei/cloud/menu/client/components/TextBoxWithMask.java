package pt.ipg.ei.cloud.menu.client.components;

import com.google.gwt.dom.client.Element;
import org.gwtbootstrap3.client.ui.TextBox;

public class TextBoxWithMask extends TextBox{
    public TextBoxWithMask() {
        super();
    }

    public TextBoxWithMask(Element element) {
        super(element);
    }

    public void setMask(String mask){
        this.getElement().setAttribute("data-mask",mask);
    }
}
