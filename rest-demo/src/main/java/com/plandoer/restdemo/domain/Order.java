package com.plandoer.restdemo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
@Data
public class Order {

    @Getter(onMethod_ = @DynamoDbPartitionKey)
    private String orderId;

    @Getter(onMethod_ = @DynamoDbSortKey)
    private String createdAt; // ISO 8601 format recommended

    private String customerName;
    private Double totalAmount;
}