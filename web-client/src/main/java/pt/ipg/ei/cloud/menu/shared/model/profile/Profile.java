package pt.ipg.ei.cloud.menu.shared.model.profile;

import pt.ipg.ei.cloud.menu.shared.util.enums.Identifiable;

public enum Profile implements Identifiable{
    ADMINISTRATOR(0b0001),
    OWNER(0b0010),
    WAITER(0b0100),
    CLIENT(0b1000);

    private int id;

    Profile(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
