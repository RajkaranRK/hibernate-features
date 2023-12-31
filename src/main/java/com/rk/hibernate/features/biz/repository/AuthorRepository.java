package com.rk.hibernate.features.biz.repository;

import com.rk.hibernate.features.biz.domain.onetomany.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
}
