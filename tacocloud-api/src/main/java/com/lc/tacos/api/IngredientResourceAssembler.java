package com.lc.tacos.api;

import com.lc.tacos.domain.Ingredient;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

/**
 * @author DELL
 * @date 2022/8/15 22:07
 */
public class IngredientResourceAssembler extends
        RepresentationModelAssemblerSupport<Ingredient, IngredientResource> {
    /**
     * Creates a new {@link RepresentationModelAssemblerSupport} using the given controller class and resource type.
     *
     * @param controllerClass must not be {@literal null}.
     * @param resourceType    must not be {@literal null}.
     */
    public IngredientResourceAssembler(Class<?> controllerClass, Class<IngredientResource> resourceType) {
        super(controllerClass, resourceType);
    }

    @Override
    protected IngredientResource instantiateModel(Ingredient ingredient) {
        return new IngredientResource(ingredient);
    }

    @Override
    public IngredientResource toModel(Ingredient ingredient) {
        return createModelWithId(ingredient.getId(), ingredient);
    }
}
