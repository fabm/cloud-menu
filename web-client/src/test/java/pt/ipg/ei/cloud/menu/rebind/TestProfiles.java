package pt.ipg.ei.cloud.menu.rebind;

import org.junit.Assert;
import org.junit.Test;
import pt.ipg.ei.cloud.menu.shared.model.UserApp;
import pt.ipg.ei.cloud.menu.shared.model.profile.Profile;

import java.util.HashSet;
import java.util.Set;

public class TestProfiles {
    @Test
    public void multipleProfiles(){
        UserApp userApp = new UserApp(){
            {
                profileSet = Profile.ADMINISTRATOR.getId()|Profile.CLIENT.getId();
            }
        };

        Assert.assertTrue(userApp.hasProfile(Profile.CLIENT));
        Assert.assertTrue(userApp.hasProfile(Profile.ADMINISTRATOR));
        Assert.assertFalse(userApp.hasOnlyTheProfile(Profile.CLIENT));
        Assert.assertFalse(userApp.hasOnlyTheProfile(Profile.ADMINISTRATOR));
        Assert.assertFalse(userApp.hasOnlyTheProfile(Profile.OWNER));
        Assert.assertFalse(userApp.hasOnlyTheProfile(Profile.WAITER));
        Assert.assertFalse(userApp.hasProfile(Profile.OWNER));
        Assert.assertFalse(userApp.hasProfile(Profile.WAITER));
    }

    @Test
    public void singleProfiles(){
        UserApp userApp = new UserApp(){
            {
                profileSet = Profile.WAITER.getId();
            }
        };

        Assert.assertTrue(userApp.hasProfile(Profile.WAITER));
        Assert.assertTrue(userApp.hasOnlyTheProfile(Profile.WAITER));
        Assert.assertFalse(userApp.hasOnlyTheProfile(Profile.CLIENT));
        Assert.assertFalse(userApp.hasOnlyTheProfile(Profile.ADMINISTRATOR));
        Assert.assertFalse(userApp.hasOnlyTheProfile(Profile.OWNER));
        Assert.assertFalse(userApp.hasProfile(Profile.OWNER));
        Assert.assertFalse(userApp.hasProfile(Profile.ADMINISTRATOR));
    }


    @Test
    public void iteratingProfiles(){
        UserApp userApp = new UserApp(){
            {
                profileSet = Profile.CLIENT.getId()|Profile.OWNER.getId();
            }
        };

        Set<Profile> profileSet = new HashSet<>();
        for(Profile profile:userApp.profiles()){
            profileSet.add(profile);
        }

        Assert.assertTrue(profileSet.contains(Profile.CLIENT));
        Assert.assertTrue(profileSet.contains(Profile.OWNER));
        Assert.assertEquals(2,profileSet.size());
    }
}
