package com.vstumpf.lolmanager.repository;

import com.vstumpf.lolmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Vincent on 7/24/2017.
 */

//@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String email);
}
