package pt.ipg.ei.cloud.menu.client.storage;

public class StorageEventUtilsNotIE implements StorageEventUtils {
    @Override
    public native void createEventListener(StorageEventHandler storageEventHandler) /*-{
        $wnd.jQuery(window).bind('storage', function (evt) {
            storageEventHandler.@pt.ipg.ei.cloud.menu.client.storage.StorageEventHandler::fireStorageEvent(*)(evt.originalEvent);
        });
    }-*/;


}
