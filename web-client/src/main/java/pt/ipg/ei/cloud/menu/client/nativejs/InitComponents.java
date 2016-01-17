package pt.ipg.ei.cloud.menu.client.nativejs;

import com.google.gwt.core.client.ScriptInjector;

public class InitComponents {
    private static boolean IS_UPLOAD_COMPONENTS_INITIALIZED = false;
    public static void upload(){
        if(IS_UPLOAD_COMPONENTS_INITIALIZED){
            return;
        }
        IS_UPLOAD_COMPONENTS_INITIALIZED = true;
        ScriptInjector.fromUrl("http://cdnjs.cloudflare.com/ajax/libs/jasny-bootstrap/3.1.3/js/jasny-bootstrap.min.js").setWindow(ScriptInjector.TOP_WINDOW).inject();
    }
}
