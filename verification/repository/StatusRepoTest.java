package com.gen.eChannel.verification.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.gen.eChannel.verification.entities.Status;
import com.gen.eChannel.verification.repositories.StatusRepo;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class StatusRepoTest {

    @Autowired
    private StatusRepo statusRepo;

    private Status status;

    @BeforeEach
    public void setUp() {
        status = new Status();
        status.setName("testStatus");

        statusRepo.save(status);
    }

    @Test
    @DisplayName("Find by name test")
    public void shouldFindByName() {
        // when - action and behaviour that we are going to test
        Optional<Status> foundStatus = statusRepo.findByName(status.getName());

        // then - verify the result and output using assert statements
        assertThat(foundStatus).isPresent();
        assertThat(foundStatus.get().getName()).isEqualTo(status.getName());
    }
}
