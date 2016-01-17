package pt.ipg.ei.cloud.menu.shared.model.restaurant;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class RestaurantRegisterRequest implements Serializable{
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String description;
    @NotNull
    @Pattern(regexp = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$", message = "{javax.validation.constraints.Pattern.outIp.message}")
    private String outIp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOutIp() {
        return outIp;
    }

    public void setOutIp(String outIp) {
        this.outIp = outIp;
    }
}
