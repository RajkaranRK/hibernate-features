package com.rk.hibernate.features.two.repository;

import com.rk.hibernate.features.two.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

 //url : http://localhost:8080/user - POST (create)
// GET http://localhost:8080/user/{id}
 @Repository
 @RepositoryRestResource(path = "user")
public interface UserRepository extends JpaRepository<User, Long> {
}
