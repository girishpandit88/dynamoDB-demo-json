package access.dynamo;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.*;

/**
 * Created by girishpandit on 10/9/14.
 */
public class TableCreator {
    public static void createTable(String tableName){
        AmazonDynamoDBClient client = new AmazonDynamoDBClient(new ProfileCredentialsProvider("default"));
        client.setEndpoint("http://localhost:7777");
        AmazonDynamoDB dynamo =client;
        CreateTableRequest request = new CreateTableRequest().withTableName("tableName");

        request.withKeySchema(new KeySchemaElement()
                .withAttributeName("id")
                .withKeyType(KeyType.HASH));

        request.withAttributeDefinitions(new AttributeDefinition()
                .withAttributeName("document")
                .withAttributeType(ScalarAttributeType.S));

        request.setProvisionedThroughput(new ProvisionedThroughput()
                .withReadCapacityUnits(5L)
                .withWriteCapacityUnits(2L));

        dynamo.createTable(request);
    }
    protected static void waitForTableToBecomeAvailable(String tableName) throws InterruptedException {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient(new ProfileCredentialsProvider("default"));
        client.setEndpoint("http://localhost:7777");
        AmazonDynamoDB dynamo =client;
        System.out.println("Waiting for " + tableName + " to become ACTIVE...");

        long startTime = System.currentTimeMillis();
        long endTime = startTime + (10 * 60 * 1000);
        while ( System.currentTimeMillis() < endTime ) {
            Thread.sleep(1000 * 20);
            try {
                DescribeTableRequest request = new DescribeTableRequest()
                        .withTableName(tableName);
                TableDescription table = dynamo.describeTable(request).getTable();
                if ( table == null ) continue;

                String tableStatus = table.getTableStatus();
                System.out.println("  - current state: " + tableStatus);
                if ( tableStatus.equals(TableStatus.ACTIVE.toString()) )
                    return;
            } catch ( AmazonServiceException ase ) {
                if (!ase.getErrorCode().equalsIgnoreCase("ResourceNotFoundException"))
                    throw ase;
            }
        }

        throw new RuntimeException("Table " + tableName + " never went active");
    }

    public static void main(String args[]) throws InterruptedException {
        TableCreator.createTable("jsonTable");
        TableCreator.waitForTableToBecomeAvailable("jsonTable");
    }
}
