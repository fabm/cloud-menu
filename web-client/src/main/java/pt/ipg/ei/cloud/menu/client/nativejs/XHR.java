package pt.ipg.ei.cloud.menu.client.nativejs;

public interface XHR {
    void post(String url, FormData data, OnPostListener listener);
}
