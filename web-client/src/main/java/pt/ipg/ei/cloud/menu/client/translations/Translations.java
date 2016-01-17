package pt.ipg.ei.cloud.menu.client.translations;

import com.google.gwt.i18n.client.Messages;

public interface Translations extends Messages {
    String empty(String field);

    String authenticateWith();

    @Key("entry.text")
    String entryText();

    @Key("restaurant.menu.register")
    String restaurantMenuRegister();

    @Key("restaurant.menu.edit")
    String restaurantMenuEdit();

    @Key("restaurant.label.out.ip")
    String restaurantLabelOutIp();

    @Key("restaurant.label.description")
    String restaurantLabelDescription();

    @Key("restaurant.out.request.whitelist")
    String restaurantOutRequestWhitelist();

    @Key("administration.list.restaurants.waiting.confirmation")
    String administrationListRestaurantsWaitingConfirmation();

    @Key("button.ok")
    String buttonOk();

    @Key("button.cancel")
    String buttonCancel();

    @Key("administration.confirm.restaurant")
    String administrationConfirmRestaurant();

    @Key("products.add.to.order")
    String productsAddToOrder();

    @Key("products.add.to.restaurant")
    String productsAddToRestaurant();

    @Key("client.order")
    String clientOrder();

    @Key("restaurant.label.name")
    String restaurantLabelName();

    @Key("restaurants.list.title")
    String restaurantsListTitle();

    @Key("restaurant.location.label")
    String restaurantLocationLabel();

    @Key("restaurant.location.adress")
    String restaurantLocationAdress();

    @Key("administration.confirm.restaurant.list")
    String administrationConfirmRestaurantList();

    @Key("restaurant.waiters")
    String restaurantWaiters();

    @Key("restaurant.waiters.register")
    String restaurantWaitersRegister();

    @Key("restaurant.waiters.create")
    String restaurantWaitersCreate();

    @Key("restaurant.kitchen.staff")
    String restaurantKitchenStaff();

    @Key("restaurant.kitchen.staff.create")
    String restaurantKitchenStaffCreate();

    @Key("restaurant.menu")
    String restaurantMenu();

    @Key("products.restaurant.management")
    String productsRestaurantManagement();

    @Key("navbar.profile")
    String navbarProfile();

    @Key("profile.administrator")
    String profileAdministrator();

    @Key("profile.restaurant.owner")
    String profileRestaurantOwner();

    @Key("profile.client")
    String profileClient();

    @Key("profile.waiter")
    String profileWaiter();

    @Key("restaurant.register")
    String restaurantRegister();

    @Key("select.restaurant")
    String selectRestaurant();

    @Key("message.restaurant.registrarion.done")
    String messageRegistrarionDone();

    @Key("message.connection.problem")
    String messageConnectionProblem();
}
