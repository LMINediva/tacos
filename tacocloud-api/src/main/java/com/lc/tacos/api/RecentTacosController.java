package com.lc.tacos.api;

import com.lc.tacos.data.TacoRepository;
import com.lc.tacos.domain.Taco;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author DELL
 * @date 2022/8/23 22:52
 */
//@RepositoryRestController
@BasePathAwareController
public class RecentTacosController {
    private TacoRepository tacoRepo;

    public RecentTacosController(TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }

    @GetMapping(path = "/tacosx/recent", produces = "application/hal+json")
    @ResponseBody
    public CollectionModel<TacoResource> recentTacos() {
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());
        List<Taco> tacos = tacoRepo.findAll(page).getContent();
        CollectionModel<TacoResource> recentResources =
                new TacoResourceAssembler(
                        DesignTacoController.class, TacoResource.class)
                        .toCollectionModel(tacos);
        recentResources.add(
                linkTo(methodOn(DesignTacoController.class)
                        .recentTacos())
                        .withRel("recents"));
        return recentResources;
    }
}
