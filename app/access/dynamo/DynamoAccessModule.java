package access.dynamo;

import access.DataAccess;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.google.inject.AbstractModule;
import com.typesafe.config.Config;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * Created by girishpandit on 10/8/14.
 */
public class DynamoAccessModule extends AbstractModule {
    private final Config config;
    public DynamoAccessModule(Config config){this.config=config;}
    @Override
    protected void configure() {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient
                (new ProfileCredentialsProvider("default"));
        client.setEndpoint("http://localhost:7777");
        bind(MapperFactory.class).toInstance(new DefaultMapperFactory.Builder().build());
        bind(Config.class).toInstance(this.config);
        bind(DynamoDB.class).toInstance(new DynamoDB(client));
        bind(DataAccess.class).to(DynamoDataAccess.class).asEagerSingleton();
    }
}
