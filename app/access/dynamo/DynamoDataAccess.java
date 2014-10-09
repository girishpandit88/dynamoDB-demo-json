package access.dynamo;

import access.DataAccess;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import com.typesafe.config.Config;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import models.Data;

import java.util.UUID;

/**
 * Created by girishpandit on 10/8/14.
 */
public class DynamoDataAccess implements DataAccess {
    private final DynamoDB dynamoDB;
    private final MapperFacade mapper;
    private final Config config;
    @Inject
    public DynamoDataAccess(DynamoDB dynamoDB,
                            MapperFactory mapperFactory, Config config) {
        this.dynamoDB = dynamoDB;
        this.config = config;
        this.mapper = mapperFactory.getMapperFacade();
    }

    @Override
    public Data get(String id) {
        Table table = dynamoDB.getTable("jsonTable");
        Item documentItem =
                table.getItem(new GetItemSpec()
                        .withPrimaryKey("id", 123)
                        .withAttributesToGet("document"));
        return this.mapper.map(documentItem.getJSONPretty("document"),Data.class);
    }

    @Override
    public void set(JsonNode json) {
        Table table = dynamoDB.getTable("jsonTable");
        Item item =
                new Item()
                        .withPrimaryKey("id",UUID.randomUUID().toString().replaceAll("-",""))
                        .withJSON("document", json.toString());
        table.putItem(item);
    }
}
