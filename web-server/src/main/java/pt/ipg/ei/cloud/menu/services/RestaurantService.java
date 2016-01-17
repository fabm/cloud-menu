package pt.ipg.ei.cloud.menu.services;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import com.googlecode.objectify.VoidWork;
import pt.ipg.ei.cloud.menu.entities.HistoryType;
import pt.ipg.ei.cloud.menu.entities.RestaurantEntity;
import pt.ipg.ei.cloud.menu.server.auth.ServerUserData;
import pt.ipg.ei.cloud.menu.shared.model.restaurant.RestaurantRegisterRequest;
import pt.ipg.ei.cloud.menu.shared.model.restaurant.RestaurantRegisterResponse;
import pt.ipg.ei.cloud.menu.shared.model.restaurant.RestaurantState;
import pt.ipg.ei.cloud.menu.validation.BeanValidation;
import pt.ipg.ei.cloud.menu.validation.BeanValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;

import static com.googlecode.objectify.ObjectifyService.ofy;
import static pt.ipg.ei.cloud.menu.conversion.DefaultConversion.convert;
import static pt.ipg.ei.cloud.menu.conversion.DefaultConversion.createHistory;

@Api(version = "v1", name = "restaurant")
public class RestaurantService {
    @Inject
    @Named("me")
    private Provider<ServerUserData> me;

    @Inject
    private BeanValidator validator;


    @ApiMethod(httpMethod = ApiMethod.HttpMethod.POST, path = "restaurant-register")
    public RestaurantRegisterResponse restaurantRegister(RestaurantRegisterRequest restaurantRegisterRequest, HttpServletRequest request) {
        BeanValidation<RestaurantRegisterRequest> validation = validator.getValidation(restaurantRegisterRequest);
        if(validation.isValid()){
            String photoPar = request.getParameter("photo");
            try {
                Part photo = request.getPart("photo");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            }
            final RestaurantEntity restaurantEntity = convert(restaurantRegisterRequest, new RestaurantEntity());
            restaurantEntity.setOwnerDisplayName(me.get().getDisplayName());
            restaurantEntity.setOwnerId(me.get().getId());
            restaurantEntity.setState(RestaurantState.REGISTER_REQUEST);
            ofy().transact(new VoidWork() {
                @Override
                public void vrun() {
                    ofy().save().entity(restaurantEntity).now();
                    ofy().save().entity(createHistory(restaurantEntity, HistoryType.CREATE)).now();
                }
            });

        }
        return new RestaurantRegisterResponse(validator.getValidation(restaurantRegisterRequest).getErrors());
    }

}
