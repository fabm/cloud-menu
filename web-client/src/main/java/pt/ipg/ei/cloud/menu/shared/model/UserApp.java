package pt.ipg.ei.cloud.menu.shared.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pt.ipg.ei.cloud.menu.shared.model.profile.Profile;
import pt.ipg.ei.cloud.menu.shared.util.enums.EnumCombination;

public class UserApp {
    protected String displayName;
    protected boolean authenticated;
    protected String urlAuth;
    protected int profileSet;

    @JsonIgnore
    protected EnumCombination<Profile> profilesCombination = new EnumCombination<Profile>() {
        @Override
        protected Profile[] getAllEnumerations() {
            return Profile.values();
        }

        @Override
        protected int getIdSet() {
            return getProfileSet();
        }

    };

    public UserApp() {
    }

    protected int getProfileSet(){
        return profileSet;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public String getUrlAuth() {
        return urlAuth;
    }
    public boolean hasProfile(Profile profile){
        return profilesCombination.hasEnum(profile);
    }
    public boolean hasOnlyTheProfile(Profile profile){
        return profilesCombination.hasOnlyThisEnum(profile);
    }

    public Iterable<Profile> profiles(){
        return profilesCombination;
    }
}
