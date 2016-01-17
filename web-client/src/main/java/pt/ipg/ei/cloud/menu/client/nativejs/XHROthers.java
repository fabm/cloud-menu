package pt.ipg.ei.cloud.menu.client.nativejs;

public class XHROthers implements XHR{

    public final native void post(String url, FormData data, OnPostListener listener)/*-{
        var urlParameter = url;
        var formData = data;
        var xhr = new XMLHttpRequest();
        xhr.open("POST", urlParameter);
        xhr.onload = function (e) {
            console.log(e);
            console.log(this);
            listener.@pt.ipg.ei.cloud.menu.client.nativejs.OnPostListener::posted(*)(this.response);
        }
        xhr.send(data);
    }-*/;

}
