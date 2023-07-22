package com.gen.signatureverification.repositories;

import com.gen.signatureverification.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepo extends JpaRepository<Status, Long> {

    Optional<Status> findByName(String name);

    //Status findByStatusName(String statusName);

}
