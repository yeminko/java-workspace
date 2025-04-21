package com.plandoer.restdemo.controller;

import com.plandoer.restdemo.repository.ProductRepository;
import com.plandoer.restdemo.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Product product) {
        repository.save(product);
        return ResponseEntity.ok("Product saved");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable String id) {
        return ResponseEntity.ok(repository.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        repository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping
    public ResponseEntity<List<Product>> list() {
        return ResponseEntity.ok(repository.getAll());
    }
}