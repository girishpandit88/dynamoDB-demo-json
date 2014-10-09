package access.dynamo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by girishpandit on 10/8/14.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "jsonTable")
public class DynamoDataDTO {
    String id;
    String document;
    @DynamoDBHashKey
    public String getId(){
        return this.id;
    }
}
