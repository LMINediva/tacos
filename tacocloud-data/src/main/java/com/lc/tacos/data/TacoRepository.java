package com.lc.tacos.data;

import com.lc.tacos.domain.Taco;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author DELL
 * @date 2022/4/19 17:15
 */
public interface TacoRepository extends
        PagingAndSortingRepository<Taco, Long> {

}
