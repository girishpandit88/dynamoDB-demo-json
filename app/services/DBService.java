package services;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.models.DataResponse;

/**
 * Created by girishpandit on 10/8/14.
 */
public interface DBService {
    public DataResponse set(JsonNode jsonNode);

}
