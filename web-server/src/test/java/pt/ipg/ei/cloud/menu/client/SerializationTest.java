package pt.ipg.ei.cloud.menu.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import pt.ipg.ei.cloud.menu.server.auth.ServerUserData;

import java.io.IOException;

public class SerializationTest {
    @Test
    public void serializationTest() throws IOException {
        ServerUserData serverUserData = new ServerUserDataTest();

        ObjectMapper mapper = new ObjectMapper();
        String serialized = mapper.writeValueAsString(serverUserData);

        JsonNode node = mapper.readTree(serialized);
        Assert.assertTrue(node.has("displayName"));
        Assert.assertTrue(node.has("authenticated"));
        Assert.assertTrue(node.has("urlAuth"));
        Assert.assertFalse(node.has("id"));
        Assert.assertEquals(3,node.size());

    }


}
