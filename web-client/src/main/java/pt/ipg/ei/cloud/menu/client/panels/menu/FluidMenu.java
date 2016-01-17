package pt.ipg.ei.cloud.menu.client.panels.menu;

import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.*;
import org.gwtbootstrap3.client.ui.constants.Toggle;

import java.util.List;

public class FluidMenu {
    private List<Widget> list;
    private DropDownMenu dropDownMenu;
    private NavbarNav navbarNav;

    public FluidMenu(List<Widget> list, NavbarNav navbarNav) {
        this.list = list;
        this.navbarNav = navbarNav;
    }

    public FluidMenu createAnchorItemInNavBar(String text, String token) {
        final AnchorListItem anchorItem = createAnchorItem(text, token);
        list.add(anchorItem);
        navbarNav.add(anchorItem);
        return this;
    }

    public FluidMenu createAnchorInDropDown(String text, String token) {
        dropDownMenu.add(createAnchorItem(text, token));
        return this;
    }

    private AnchorListItem createAnchorItem(String text, String token) {
        AnchorListItem anchorListItem = new AnchorListItem();
        anchorListItem.setText(text);
        anchorListItem.setTargetHistoryToken(token);
        return anchorListItem;
    }

    public FluidMenu createDropDownList(String title){
        ListDropDown listDropDown = new ListDropDown();
        list.add(listDropDown);
        AnchorButton anchorButton = new AnchorButton();
        anchorButton.setText(title);
        anchorButton.setDataToggle(Toggle.DROPDOWN);
        listDropDown.add(anchorButton);
        dropDownMenu = new DropDownMenu();
        listDropDown.add(dropDownMenu);
        navbarNav.add(listDropDown);
        return this;
    }

}
