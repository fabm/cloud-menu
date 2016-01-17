package pt.ipg.ei.cloud.menu.client.nativejs;

public class XHRInternetExplorer implements XHR{

    @Override
    public final native void post(String url, FormData data, OnPostListener listener)/*-{
        var urlParameter = url;
        var formData = data;
        var xhr = new XMLHttpRequest();

        xhr.responseType = "text";
        xhr.open("POST", urlParameter,true);

        xhr.onreadystatechange = function () {
            if (xhr.readyState == xhr.DONE) {
                console.log(xhr.response);
                listener.@pt.ipg.ei.cloud.menu.client.nativejs.OnPostListener::posted(*)(xhr.response);
            }
        }

        xhr.send(data);
    }-*/;
}
