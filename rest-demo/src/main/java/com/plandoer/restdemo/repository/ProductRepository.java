package com.plandoer.restdemo.repository;

import com.plandoer.restdemo.domain.Product;
import com.plandoer.restdemo.utils.DynamoDbUtils;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.List;

@Repository
public class ProductRepository {
    private final DynamoDbTable<Product> productTable;

    public ProductRepository(DynamoDbEnhancedClient enhancedClient, DynamoDbClient dynamoDbClient) {
        this.productTable = enhancedClient.table("Product", TableSchema.fromBean(Product.class));

        // Check if the table exists, if not create it
        if (!DynamoDbUtils.tableExists(dynamoDbClient, "Product")) {
            productTable.createTable();
        }
    }

    public void save(Product product) {
        productTable.putItem(product);
    }

    public Product getById(String id) {
        return productTable.getItem(Key.builder().partitionValue(id).build());
    }

    public void deleteById(String id) {
        productTable.deleteItem(Key.builder().partitionValue(id).build());
    }

    public List<Product> getAll() {
        return productTable.scan().items().stream().toList();
    }
}