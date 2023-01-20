package com.lc.tacos.data;

import com.lc.tacos.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author DELL
 * @date 2022/4/23 17:55
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);
}
