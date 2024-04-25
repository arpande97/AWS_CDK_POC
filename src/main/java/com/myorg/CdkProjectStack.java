package com.myorg;

import software.amazon.awscdk.services.dynamodb.Attribute;
import software.amazon.awscdk.services.dynamodb.AttributeType;
import software.amazon.awscdk.services.dynamodb.Table;
import software.amazon.awscdk.services.elasticache.CfnCacheCluster;
import software.amazon.awscdk.services.elasticache.CfnCacheClusterProps;
import software.constructs.Construct;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;

import java.util.ArrayList;
import java.util.List;
// import software.amazon.awscdk.Duration;
// import software.amazon.awscdk.services.sqs.Queue;

public class CdkProjectStack extends Stack {
    public CdkProjectStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public CdkProjectStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        Table dynamoDBTable = Table.Builder.create(this, "MyDynamoDBTable")
                .tableName("emailTable")
                .partitionKey(Attribute.builder()
                        .name("username")
                        .type(AttributeType.STRING)
                        .build())
                .build();

        List<String> vpcGroupId = new ArrayList<>();
        //vpcGroupId.add("<your_vpc_group_id>");
        CfnCacheCluster cacheCluster = CfnCacheCluster.Builder.create(this, "memcachecluster7")
                .clusterName("memcacheclusterCREATEDFROMCDK")
                .cacheNodeType("cache.t4g.micro")
                .engine("memcached")
                .numCacheNodes(1)
                .vpcSecurityGroupIds(vpcGroupId)
                .build();
    }
}
