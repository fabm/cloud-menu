package pt.ipg.ei.cloud.menu.dao;

import com.google.inject.Inject;

public class BlobReferenceSetterProvider implements BlobReferenceSetter{

    @Inject
    private RestaurantDAO restaurantDAO;

    @Override
    public void setReference(String type, String id, String ref) {
        if("restaurant".equals(type)){
            restaurantDAO.setReference(type, id, ref);
        }else{
            throw new IllegalStateException("BlobReferenceSetter unknown");
        }

    }
}
