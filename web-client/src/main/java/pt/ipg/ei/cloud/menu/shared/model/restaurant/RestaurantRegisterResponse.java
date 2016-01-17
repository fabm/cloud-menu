package pt.ipg.ei.cloud.menu.shared.model.restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantRegisterResponse {

    private List<String> errors;

    public RestaurantRegisterResponse() {
        errors = new ArrayList<String>();
    }

    public RestaurantRegisterResponse(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

}
