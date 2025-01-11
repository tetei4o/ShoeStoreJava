package org.example.shoestorejava.models;

import lombok.Data;
import jakarta.persistence.*;

import java.util.List;

@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Shoe> shoes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
