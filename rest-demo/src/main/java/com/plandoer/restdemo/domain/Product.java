package com.plandoer.restdemo.domain;

import lombok.Data;
import lombok.Getter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
@Data
public class Product {
    @Getter(onMethod_ = @DynamoDbPartitionKey)
    private String id;
    private String name;
    private Double price;
}