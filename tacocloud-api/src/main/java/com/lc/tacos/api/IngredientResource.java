package com.lc.tacos.api;

import com.lc.tacos.domain.Ingredient;
import com.lc.tacos.domain.Ingredient.Type;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

/**
 * @author DELL
 * @date 2022/8/15 22:03
 */
public class IngredientResource extends
        RepresentationModel<IngredientResource> {
    @Getter
    private final String name;

    @Getter
    private final Type type;

    public IngredientResource(Ingredient ingredient) {
        this.name = ingredient.getName();
        this.type = ingredient.getType();
    }
}
