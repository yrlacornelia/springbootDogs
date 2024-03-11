package com.example.dogsAndOwners.repository;

import com.example.dogsAndOwners.entity.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

}
