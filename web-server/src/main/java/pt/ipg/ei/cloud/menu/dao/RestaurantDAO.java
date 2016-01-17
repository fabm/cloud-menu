package pt.ipg.ei.cloud.menu.dao;

import com.google.inject.Singleton;
import com.googlecode.objectify.Key;
import pt.ipg.ei.cloud.menu.entities.RestaurantEntity;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Singleton
public class RestaurantDAO implements BlobReferenceSetter {


    @Override
    public void setReference(String type, String id, String ref) {
        RestaurantEntity restaurantEntity = ofy()
                .load()
                .key(Key.create(RestaurantEntity.class, Long.parseLong(id)))
                .now();
        restaurantEntity.setPhoto(ref);
        ofy().save().entity(restaurantEntity).now();
    }
}
