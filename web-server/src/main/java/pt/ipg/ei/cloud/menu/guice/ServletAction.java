package pt.ipg.ei.cloud.menu.guice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletAction{
    private static ThreadLocal<ServletAction> tlServletAction = new ThreadLocal<ServletAction>();
    private HttpServletRequest request;
    private HttpServletResponse response;

    public static ServletAction create(HttpServletRequest request, HttpServletResponse response){
        ServletAction servletAction = new ServletAction();
        servletAction.request = request;
        servletAction.response = response;
        tlServletAction.set(servletAction);
        return servletAction;
    }

    public static ServletAction get(){
        return tlServletAction.get();
    }

    public static void remove(){
        tlServletAction.remove();
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }
}
