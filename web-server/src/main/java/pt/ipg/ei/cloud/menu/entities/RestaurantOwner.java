package pt.ipg.ei.cloud.menu.entities;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.List;

@Entity
public class RestaurantOwner {
    @Id
    private Long id;
    private List<RestaurantEntity> restaurants;


}
