package pt.ipg.ei.cloud.menu.shared.model.generic;

public class AuthResponse<T> {
    private boolean authorized;
    private T response;

    public AuthResponse() {
    }

    public AuthResponse(T response) {
        this.response = response;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }
}
