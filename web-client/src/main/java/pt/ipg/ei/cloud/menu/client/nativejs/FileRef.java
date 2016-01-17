package pt.ipg.ei.cloud.menu.client.nativejs;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.InputElement;

public class FileRef extends JavaScriptObject {

    public static native String arrayBufferToBase64(JavaScriptObject buffer)/*-{
        var binary = '';
        var bytes = new Uint8Array(buffer);
        var len = bytes.byteLength;
        for (var i = 0; i < len; i++) {
            binary += String.fromCharCode(bytes[i]);
        }
        return $wnd.btoa(binary);
    }-*/;

    public static native JavaScriptObject base64ToarrayBuffer(String base64)/*-{
        var binary_string = $wnd.atob(base64);
        var len = binary_string.length;
        var bytes = new Uint8Array(len);
        for (var i = 0; i < len; i++) {
            bytes[i] = binary_string.charCodeAt(i);
        }
        return bytes.buffer;
    }-*/;

    public static native void create(InputElement inputElement, FileRefCreator fileRefCreator) /*-{
        var arrayBufferToBase64 = @pt.ipg.ei.cloud.menu.client.nativejs.FileRef::arrayBufferToBase64(*);
        var file = inputElement.files[0];
        var reader = new FileReader();
        reader.onload = function (e) {

            var fileObj = {
                file: arrayBufferToBase64(e.target.result),
                type: file.type
            };
            fileRefCreator.@pt.ipg.ei.cloud.menu.client.nativejs.FileRefCreator::create(*)(fileObj);
        }
        if (typeof(file) !== 'undefined') {
            reader.readAsArrayBuffer(file);
        }
    }-*/;

    protected FileRef() {
    }

    public final native String getContent() /*-{
        return this.file;
    }-*/;

    public final native String getType() /*-{
        return this.type;
    }-*/;

    public final native String createBlobUrl() /*-{
        var base64ToarrayBuffer = @pt.ipg.ei.cloud.menu.client.nativejs.FileRef::base64ToarrayBuffer(*);
        var blob = new Blob([base64ToarrayBuffer(this.file)], {type: this.type});
        return URL.createObjectURL(blob);
    }-*/;

    public final String createBase64Url() {
        return "data:"+getType()+";base64,"+getContent();
    };

}
