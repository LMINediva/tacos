package com.lc.tacos.data;

import com.lc.tacos.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

/**
 * @author DELL
 * @date 2022/4/19 17:14
 */
public interface IngredientRepository extends
        CrudRepository<Ingredient, String> {

}
