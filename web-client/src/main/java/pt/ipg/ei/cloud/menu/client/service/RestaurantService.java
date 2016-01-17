package pt.ipg.ei.cloud.menu.client.service;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import pt.ipg.ei.cloud.menu.shared.model.restaurant.RestaurantRegisterRequest;
import pt.ipg.ei.cloud.menu.shared.model.restaurant.RestaurantRegisterResponse;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

public interface RestaurantService extends RestService{
    @POST
    @Path("restaurant-register")
    void restaurantRegister(RestaurantRegisterRequest restaurantRegisterRequest, MethodCallback<RestaurantRegisterResponse> callback);

}
