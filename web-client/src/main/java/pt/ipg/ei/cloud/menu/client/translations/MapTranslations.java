package pt.ipg.ei.cloud.menu.client.translations;

import com.google.gwt.core.client.GWT;
import pt.ipg.ei.cloud.menu.shared.model.profile.Profile;

public class MapTranslations {
    private Translations translations = GWT.create(Translations.class);

    public String getProfile(Profile profile) {

        switch (profile) {
            case ADMINISTRATOR:
                return translations.profileAdministrator();
            case WAITER:
                return translations.profileWaiter();
            case OWNER:
                return translations.profileRestaurantOwner();
            case CLIENT:
                return translations.profileClient();
            default:
                throw new IllegalStateException("No correspondence");
        }
    }
}
