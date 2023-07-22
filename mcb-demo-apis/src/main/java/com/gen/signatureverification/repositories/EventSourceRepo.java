package com.gen.signatureverification.repositories;

import com.gen.signatureverification.entities.EventSource;
import com.gen.signatureverification.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventSourceRepo extends JpaRepository<EventSource, Long> {

    List<EventSource> findByStatus(Status status);

    @Query("SELECT COUNT(es) FROM EventSource es WHERE es.status.name = :name")
    long countByStatusName(@Param("name") String name);

    List<EventSource> findByUserIsNotNull();

    List<EventSource> findByStatusName(String name);

}
