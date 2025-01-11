package org.example.shoestorejava.services;

import org.example.shoestorejava.models.Shoe;
import org.example.shoestorejava.repositories.ShoeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoeService {

    @Autowired
    private ShoeRepository shoeRepository;

    public List<Shoe> getAllShoes() {
        return shoeRepository.findAll();
    }

    public Optional<Shoe> getShoeById(Long id) {
        return shoeRepository.findById(id);
    }

    public List<Shoe> getShoesByCategory(String categoryName) {
        return shoeRepository.findByCategoryName(categoryName);
    }

    public Shoe addShoe(Shoe shoe) {
        return shoeRepository.save(shoe);
    }

    public Shoe updateShoe(Long id, Shoe updatedShoe) {
        return shoeRepository.findById(id)
                .map(shoe -> {
                    shoe.setName(updatedShoe.getName());
                    shoe.setBrand(updatedShoe.getBrand());
                    shoe.setPrice(updatedShoe.getPrice());
                    shoe.setStockQuantity(updatedShoe.getStockQuantity());
                    shoe.setCategory(updatedShoe.getCategory());
                    return shoeRepository.save(shoe);
                }).orElseThrow(() -> new RuntimeException("Shoe not found with id " + id));
    }

    public void deleteShoe(Long id) {
        shoeRepository.deleteById(id);
    }
}
