package com.lc.tacos.api;

import com.lc.tacos.domain.Taco;
import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Date;

/**
 * 能够携带领域数据和超链接列表taco资源
 *
 * @author DELL
 * @date 2022/8/14 23:01
 */
@Relation(value = "taco", collectionRelation = "tacos")
public class TacoResource extends RepresentationModel<TacoResource> {

    private static final IngredientResourceAssembler
            ingredientAssembler = new IngredientResourceAssembler(
            IngredientController.class, IngredientResource.class);
    @Getter
    private final String name;

    @Getter
    private final Date createdAt;

    @Getter
    private final CollectionModel<IngredientResource> ingredients;

    public TacoResource(Taco taco) {
        this.name = taco.getName();
        this.createdAt = taco.getCreatedAt();
        this.ingredients =
                ingredientAssembler.toCollectionModel(
                        taco.getIngredients());
    }
}
