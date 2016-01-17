package pt.ipg.ei.cloud.menu.client.panels.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.*;
import pt.ipg.ei.cloud.menu.client.translations.Translations;

import java.util.ArrayList;
import java.util.List;

public class NavbarFiller {
    private final Translations translations;
    private NavbarNav navbarNav;
    private List<Widget> widgets;

    public NavbarFiller(NavbarNav navbarNav) {
        this.navbarNav = navbarNav;
        widgets = new ArrayList<Widget>();
        translations = GWT.create(Translations.class);
    }

    public void clean() {
        for (Widget widget : widgets) {
            widget.removeFromParent();
        }
    }

    public void profileClient(){
        clean();
        new FluidMenu(widgets,navbarNav)
                .createAnchorItemInNavBar(translations.restaurantRegister(),"res-reg")
                .createAnchorItemInNavBar(translations.selectRestaurant(),"sel-rest");
    }


    public void profileAdmin() {
        clean();
        new FluidMenu(widgets, navbarNav)
                .createAnchorItemInNavBar(translations.administrationConfirmRestaurantList(), "cnf-rest-list");
    }
    public void profileRestOwner(){
        clean();
        new FluidMenu(widgets, navbarNav)
                .createAnchorItemInNavBar(translations.restaurantWaiters(), "mng-waiters")
                .createAnchorItemInNavBar(translations.restaurantKitchenStaff(), "mng-kit-staff")
                .createDropDownList(translations.restaurantsListTitle())
                .createAnchorInDropDown(translations.restaurantMenuEdit(),"mng-rest")
                .createAnchorInDropDown(translations.productsRestaurantManagement(),"prod-rest-mng");
    }

}
