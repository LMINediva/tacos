package com.lc.tacos.data;

import com.lc.tacos.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author DELL
 * @date 2022/4/19 17:14
 */
@RepositoryRestResource(collectionResourceRel = "ingredients",
        path = "ingredients")
public interface IngredientRepository extends
        CrudRepository<Ingredient, String> {

}
