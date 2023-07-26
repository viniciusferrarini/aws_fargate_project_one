package br.com.vncsferrarini.aws_project_one.controller;

import br.com.vncsferrarini.aws_project_one.model.Product;
import br.com.vncsferrarini.aws_project_one.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable final Long id) {
        final Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody final Product product) {
        return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@RequestBody final Product product, @PathVariable Long id) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            return ResponseEntity.ok(productRepository.save(product));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id) {
        final Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct
                .map(product -> {
                    productRepository.delete(product);
                    return ResponseEntity.ok(product);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Product> findByCode(@PathVariable final String code) {
        final Optional<Product> optionalProduct = productRepository.findByCode(code);
        return optionalProduct
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}