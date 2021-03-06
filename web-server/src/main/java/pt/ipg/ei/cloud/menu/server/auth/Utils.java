/*
 * Copyright (c) 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package pt.ipg.ei.cloud.menu.server.auth;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.appengine.datastore.AppEngineDataStoreFactory;
import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.services.plus.Plus;
import com.google.api.services.plus.PlusScopes;
import pt.ipg.ei.cloud.menu.templates.OauthResponseTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Collections;
import java.util.Set;

import static pt.ipg.ei.cloud.menu.shared.Constants.OAUTH_NAME_KEY;
import static pt.ipg.ei.cloud.menu.shared.Constants.OAUTH_STATE_KEY;

public class Utils {

    /**
     * Global instance of the {@link DataStoreFactory}. The best practice is to make it a single
     * globally shared instance across your application.
     */
    private static final AppEngineDataStoreFactory DATA_STORE_FACTORY =
            AppEngineDataStoreFactory.getDefaultInstance();

    private static GoogleClientSecrets clientSecrets = null;
    private static final Set<String> SCOPES = Collections.singleton(PlusScopes.PLUS_ME);
    static final String MAIN_SERVLET_PATH = "/";
    static final String AUTH_CALLBACK_SERVLET_PATH = "/oauth2callback";
    public static final UrlFetchTransport HTTP_TRANSPORT = new UrlFetchTransport();
    public static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    public static GoogleClientSecrets getClientSecrets() throws IOException {
        if (clientSecrets == null) {
            final InputStreamReader secretsIS = getSecretsIS();
            if (secretsIS == null) {
                System.out.println("sis is null");
            }
            clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
                    secretsIS);
            Preconditions.checkArgument(!clientSecrets.getDetails().getClientId().startsWith("Enter ")
                            && !clientSecrets.getDetails().getClientSecret().startsWith("Enter "),
                    "Download client_secrets.json file from https://code.google.com/apis/console/?api=plus "
                            + "into plus-appengine-sample/src/main/resources/client_secrets.json");
        }
        return clientSecrets;
    }

    private static InputStreamReader getSecretsIS() {
        return new InputStreamReader(Utils.class.getResourceAsStream("/client_secret.json"));
    }

    static GoogleAuthorizationCodeFlow initializeFlow() throws IOException {
        return new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, getClientSecrets(), SCOPES).setDataStoreFactory(
                DATA_STORE_FACTORY).setAccessType("offline").build();
    }

    static String formatUri(HttpServletRequest req, String rawPath) {
        GenericUrl requestUrl = new GenericUrl(req.getRequestURL().toString());
        requestUrl.setRawPath(rawPath);
        return requestUrl.build();
    }

    static String getRedirectUri(HttpServletRequest req) {
        return formatUri(req, AUTH_CALLBACK_SERVLET_PATH);
    }


    static void configTemplate(String state, Writer writer) throws IOException {
        OauthResponseTemplate template = new OauthResponseTemplate();
        template.setOauthStateKey(OAUTH_STATE_KEY);
        template.setOauthStateValue(state);
        template.render().writeTo(writer);
    }

    public static String loginUrl(HttpServletRequest req) throws IOException {
        final String takeIdUrl = initializeFlow()
                .newAuthorizationUrl()
                .setClientId(Utils.getClientSecrets().getDetails().getClientId())
                .setApprovalPrompt("force")
                .setRedirectUri(formatUri(req, AUTH_CALLBACK_SERVLET_PATH))
                .build();
        return takeIdUrl;
    }


    public static void inToOutStream(InputStream inputStream, OutputStream outputStream) throws IOException {
        ReadableByteChannel inputChannel = Channels.newChannel(inputStream);
        WritableByteChannel outputChannel = Channels.newChannel(outputStream);

        ByteBuffer buffer = ByteBuffer.allocate(10240);

        while (inputChannel.read(buffer) != -1) {
            buffer.flip();
            outputChannel.write(buffer);
            buffer.clear();
        }
    }

    public static void renderAutoCloseablePage(HttpServletResponse response) throws IOException {
        inToOutStream(Utils.class.getResourceAsStream("/pages/oauthResponse.html"), response.getOutputStream());
    }

    public Plus getPlusService(Credential credential) {
        return new Plus.Builder(Utils.HTTP_TRANSPORT, Utils.JSON_FACTORY, credential)
                .setApplicationName("cloud-menu")
                .build();
    }

}
