package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
