package com.rk.hibernate.features.one.repository;

import com.rk.hibernate.features.one.domain.onetoone.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressWriteRepository extends JpaRepository<Address,Long> {
}
