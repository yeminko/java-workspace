package com.plandoer.restdemo.repository;

import com.plandoer.restdemo.domain.Order;
import com.plandoer.restdemo.utils.DynamoDbUtils;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrderRepository {

    private final DynamoDbTable<Order> orderTable;

    public OrderRepository(DynamoDbEnhancedClient enhancedClient, DynamoDbClient dynamoDbClient) {
        this.orderTable = enhancedClient.table("Order", TableSchema.fromBean(Order.class));

        if (!DynamoDbUtils.tableExists(dynamoDbClient, "Order")) {
            orderTable.createTable();
        }
    }

    public void save(Order order) {
        orderTable.putItem(order);
    }

    public List<Order> getAll() {
        return orderTable.scan().items().stream().collect(Collectors.toList());
    }

    public Order getByIdAndTimestamp(String orderId, String createdAt) {
        return orderTable.getItem(
                Key.builder()
                        .partitionValue(orderId)
                        .sortValue(createdAt)
                        .build()
        );
    }

    public List<Order> getByOrderId(String orderId) {
        return orderTable.query(
                QueryConditional.keyEqualTo(Key.builder().partitionValue(orderId).build())
        ).items().stream().collect(Collectors.toList());
    }

    public void delete(String orderId, String createdAt) {
        orderTable.deleteItem(
                Key.builder()
                        .partitionValue(orderId)
                        .sortValue(createdAt)
                        .build()
        );
    }
}