package com.lc.tacos.data;

import com.lc.tacos.domain.Taco;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author DELL
 * @date 2022/4/19 17:15
 */
@RepositoryRestResource(collectionResourceRel = "tacos",
        path = "tacos")
public interface TacoRepository extends
        PagingAndSortingRepository<Taco, Long> {

}
