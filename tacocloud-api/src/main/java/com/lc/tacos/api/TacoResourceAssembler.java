package com.lc.tacos.api;

import com.lc.tacos.domain.Taco;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

/**
 * 装配taco资源的资源装配器
 *
 * @author DELL
 * @date 2022/8/15 20:48
 */
public class TacoResourceAssembler extends
        RepresentationModelAssemblerSupport<Taco, TacoResource> {
    /**
     * Creates a new {@link RepresentationModelAssemblerSupport} using the given controller class and resource type.
     *
     * @param controllerClass must not be {@literal null}.
     * @param resourceType    must not be {@literal null}.
     */
    public TacoResourceAssembler(Class<?> controllerClass, Class<TacoResource> resourceType) {
        super(controllerClass, resourceType);
    }

    @Override
    protected TacoResource instantiateModel(Taco taco) {
        return new TacoResource(taco);
    }

    @Override
    public TacoResource toModel(Taco taco) {
        return createModelWithId(taco.getId(), taco);
    }
}
