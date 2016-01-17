package pt.ipg.ei.cloud.menu.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import pt.ipg.ei.cloud.menu.server.auth.ServerUserData;
import pt.ipg.ei.cloud.menu.shared.model.generic.AuthResponse;
import pt.ipg.ei.cloud.menu.shared.model.generic.UploadUrl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class UploadServlet extends HttpServlet{
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
    @Inject
    @Named("me")
    private Provider<ServerUserData> userDataProvider;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
        final List<BlobKey> blobKeys = blobs.get("file");


        res.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String,String> map = new HashMap<>();
        map.put("key",blobKeys.get(0).getKeyString());

        AuthResponse<?> response = new AuthResponse<>(map);
        response.setAuthorized(true);

        mapper.writeValue(res.getWriter(),response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UploadUrl uploadUrl = new UploadUrl();
        uploadUrl.setUrl(blobstoreService.createUploadUrl("/upload"));
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json");
        mapper.writeValue(resp.getWriter(),uploadUrl);
    }
}
