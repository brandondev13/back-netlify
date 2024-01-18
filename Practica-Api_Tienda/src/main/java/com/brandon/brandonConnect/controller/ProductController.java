package com.brandon.brandonConnect.controller;

import com.brandon.brandonConnect.model.Product;
import com.brandon.brandonConnect.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/products")
public class ProductController {


    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@RequestParam("file") MultipartFile file,
                                               @RequestParam("name") String name,
                                               @RequestParam("description") String description,
                                               @RequestParam("price") Double price) {
        try {
            if (file == null || file.isEmpty()) {
                throw new IllegalArgumentException("La imagen no puede ser nula o vacía");
            }

            String base64Image = Base64.getEncoder().encodeToString(file.getBytes());

            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setImgUrlBase64(base64Image);

            Product savedProduct = productRepository.save(product);

            return ResponseEntity.ok(savedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Map<String, Object>>> getAllProducts() {
        List<Product> products = productRepository.findAll();

        List<Map<String, Object>> productMaps = products.stream()
                .map(this::mapProductToMap)
                .collect(Collectors.toList());

        return ResponseEntity.ok(productMaps);
    }

    private Map<String, Object> mapProductToMap(Product product) {
        Map<String, Object> productMap = new HashMap<>();
        productMap.put("id", product.getId());
        productMap.put("name", product.getName());
        productMap.put("description", product.getDescription());
        productMap.put("price", product.getPrice());
        productMap.put("imgUrlBase64", product.getImgUrlBase64());
        return productMap;
    }


}
