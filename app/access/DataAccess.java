package access;

import com.fasterxml.jackson.databind.JsonNode;
import models.Data;

/**
 * Created by girishpandit on 10/8/14.
 */
public interface DataAccess {
    Data get(String id);
    void set(JsonNode json);

}
