package com.lc.tacos.api;

import com.lc.tacos.data.IngredientRepository;
import com.lc.tacos.domain.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

/**
 * @author DELL
 * @date 2022/8/6 13:52
 */
@RestController
@RequestMapping(path = "/ingredientsx", produces = "application/json")
@CrossOrigin(origins = "*")
public class IngredientController {
    private IngredientRepository repo;

    @Autowired
    public IngredientController(IngredientRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public Iterable<Ingredient> allIngredients() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Ingredient> byId(@PathVariable String id) {
        return repo.findById(id);
    }

    @PutMapping("/{id}")
    public void updateIngredient(@PathVariable String id,
                                 @RequestBody Ingredient ingredient) {
        if (!ingredient.getId().equals(id)) {
            throw new IllegalStateException("给定成分的ID与路径中的ID不匹配。");
        }
        repo.save(ingredient);
    }

    @PostMapping
    public ResponseEntity<Ingredient> postIngredient(@RequestBody Ingredient ingredient) {
        Ingredient saved = repo.save(ingredient);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("http://localhost:8080/ingredientsx/" + ingredient.getId()));
        return new ResponseEntity<>(saved, headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteIngredient(@PathVariable String id) {
        repo.deleteById(id);
    }
}
