package pt.ipg.ei.cloud.menu.entities;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import pt.ipg.ei.cloud.menu.shared.model.restaurant.RestaurantState;

@Entity
public class RestaurantEntity {

    @Id
    private Long id;
    private String name;
    private String outIp;
    private String description;
    private String address;
    private String ownerId;
    private String ownerDisplayName;
    private RestaurantState state;
    private String photo;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOutIp() {
        return outIp;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerDisplayName() {
        return ownerDisplayName;
    }

    public void setOwnerDisplayName(String ownerDisplayName) {
        this.ownerDisplayName = ownerDisplayName;
    }

    public RestaurantState getState() {
        return state;
    }

    public void setState(RestaurantState state) {
        this.state = state;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
