package com.brandon.brandonConnect.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String imgUrlBase64;


    // Getters y Setters
}
