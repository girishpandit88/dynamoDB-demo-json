package services;

import access.DataAccess;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import controllers.models.DataResponse;

/**
 * Created by girishpandit on 10/8/14.
 */
public class DBServiceImpl implements DBService {
    private final DataAccess access;
    @Inject
    public DBServiceImpl(DataAccess access) {
        this.access = access;
    }

    @Override
    public DataResponse set(JsonNode jsonNode) {
        this.access.set(jsonNode);
        DataResponse response = new DataResponse();
        response.setDocument(this.access.get(jsonNode.get("id").toString()).getDocument());
        return response;
    }

}
