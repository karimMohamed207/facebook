package com.karim.facebookapplication.repository;

import com.karim.facebookapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {
    User findByUsername(String username);
    User findByUsernameAndPassword(String username , String Password);

}
