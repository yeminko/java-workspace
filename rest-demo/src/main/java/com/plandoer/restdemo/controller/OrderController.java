package com.plandoer.restdemo.controller;

import com.plandoer.restdemo.domain.Order;
import com.plandoer.restdemo.domain.Product;
import com.plandoer.restdemo.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository repository;

    public OrderController(OrderRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        repository.save(order);
        return ResponseEntity.ok("Order saved");
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(repository.getAll());
    }

    @GetMapping("/{orderId}/{createdAt}")
    public ResponseEntity<Order> getOrder(@PathVariable String orderId, @PathVariable String createdAt) {
        return ResponseEntity.ok(repository.getByIdAndTimestamp(orderId, createdAt));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<List<Order>> getOrdersByOrderId(@PathVariable String orderId) {
        return ResponseEntity.ok(repository.getByOrderId(orderId));
    }

    @DeleteMapping("/{orderId}/{createdAt}")
    public ResponseEntity<String> deleteOrder(@PathVariable String orderId, @PathVariable String createdAt) {
        repository.delete(orderId, createdAt);
        return ResponseEntity.ok("Deleted");
    }
}