package org.example.shoestorejava.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.engine.jdbc.Size;

@Data
@Entity
@Table(name = "shoes")
public class Shoe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String brand;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private Integer stockQuantity;
}
