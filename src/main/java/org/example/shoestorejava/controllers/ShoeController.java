package org.example.shoestorejava.controllers;

import org.example.shoestorejava.models.Shoe;
import org.example.shoestorejava.services.ShoeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shoes")
public class ShoeController {

    @Autowired
    private ShoeService shoeService;

    @GetMapping
    public List<Shoe> getAllShoes() {
        return shoeService.getAllShoes();
    }

    @GetMapping("/{id}")
    public Shoe getShoeById(@PathVariable Long id) {
        return shoeService.getShoeById(id).orElseThrow(() -> new RuntimeException("Shoe not found with id " + id));
    }

    @GetMapping("/category/{categoryName}")
    public List<Shoe> getShoesByCategory(@PathVariable String categoryName) {
        return shoeService.getShoesByCategory(categoryName);
    }

    @PostMapping
    public Shoe addShoe(@RequestBody Shoe shoe) {
        return shoeService.addShoe(shoe);
    }

    @PutMapping("/{id}")
    public Shoe updateShoe(@PathVariable Long id, @RequestBody Shoe updatedShoe) {
        return shoeService.updateShoe(id, updatedShoe);
    }

    @DeleteMapping("/{id}")
    public void deleteShoe(@PathVariable Long id) {
        shoeService.deleteShoe(id);
    }
}
