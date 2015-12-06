package pt.ipg.ei.cloud.menu.client.storage;

import com.google.gwt.core.client.JavaScriptObject;

public class StorageEvent extends JavaScriptObject{

    protected StorageEvent() {
    }

    public native final String key() /*-{
        return this.key;
    }-*/;          // name of the property set, changed etc.
    public native final String oldValue() /*-{
        return this.oldValue;
    }-*/;     // old value of property before change
    public native final String newValue() /*-{
        return this.newValue;
    }-*/;     // new value of property after change
    public native final String url() /*-{
        return this.url;
    }-*/;          // url of page that made the change
    public native final String storageArea() /*-{
        return this.storageArea;
    }-*/;  // localStorage or sessionStorage,

}
