package com.example.dogsAndOwners.repository;
import java.util.List;
import com.example.dogsAndOwners.entity.Owner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    @Query("SELECT o FROM Owner o WHERE o.id IN (SELECT d.owner.id FROM Dog d)")
    List<Owner> findOwnersWithDogs();
    @Query("SELECT o FROM Owner o WHERE SIZE(o.dogs) = 0")
    List<Owner> ownerWithoutDogs();

}
