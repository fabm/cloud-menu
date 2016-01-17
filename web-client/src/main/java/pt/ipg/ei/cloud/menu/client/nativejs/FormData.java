package pt.ipg.ei.cloud.menu.client.nativejs;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.InputElement;

public class FormData extends JavaScriptObject {
    public static native FormData create() /*-{
        return new $wnd.FormData();
    }-*/;

    protected FormData() {
    }

    public final native void addFileInput(InputElement inputElement) /*-{
        this.append(inputElement.name ,inputElement.files[0]);
    }-*/;

    public final native void addFileRef(String name,FileRef fileRef) /*-{
        var binary_string = $wnd.atob(fileRef.file);
        var len = binary_string.length;
        var bytes = new Uint8Array( len );
        for (var i = 0; i < len; i++)        {
            bytes[i] = binary_string.charCodeAt(i);
        }

        var blob = new Blob([bytes.buffer],{type:fileRef.type});
        this.append(name ,blob);
    }-*/;



}
