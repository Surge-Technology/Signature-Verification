package com.gen.eChannel.verification.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.gen.eChannel.verification.entities.EventSource;
import com.gen.eChannel.verification.entities.Status;
import com.gen.eChannel.verification.entities.User;
import com.gen.eChannel.verification.repositories.EventSourceRepo;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class EventSourceRepoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EventSourceRepo eventSourceRepo;

    private EventSource eventSource;

    @BeforeEach
    public void setUp() {
        User user = new User();
        user.setUserName("testuser");
        user.setPassword("password123");

        Status status = new Status();
        status.setName("testStatus");

        eventSource = new EventSource();
        eventSource.setBusinessKey("businessKey1");
        eventSource.setPriority("High");
        eventSource.setSourceBu("BU1");
        eventSource.setApplication("application1");
        eventSource.setTransactionAmount(600.0);
        eventSource.setTransactionCurrency("USD");
        eventSource.setAmountInMur(5000.0);
        eventSource.setAccountShortName("shortName1");
        eventSource.setDebitAccountNumber("1234567890");
        eventSource.setDebitAccountCurrency("USD");
        eventSource.setPaymentDetails1("detail1");
        eventSource.setPaymentDetails2("detail2");
        eventSource.setPaymentDetails3("detail3");
        eventSource.setPaymentDetails4("detail4");
        eventSource.setVerified("Yes");
        eventSource.setDiscrepancyReason("None");
        eventSource.setDocumentCaptureReference("docRef1");
        eventSource.setComments("comment1");
        eventSource.setStatus(status);
        eventSource.setUser(user);

        entityManager.persist(user);
        entityManager.persist(status);
        entityManager.persist(eventSource);
        entityManager.flush();
    }

    @Test
    @DisplayName("Find by status test")
    public void shouldFindByStatus() {
        // when - action and behaviour that we are going to test
        List<EventSource> found = eventSourceRepo.findByStatus(eventSource.getStatus());

        // then - verify the result and output using assert statements
        assertThat(found.size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Count by status name test")
    public void shouldCountByStatusName() {
        // when - action and behaviour that we are going to test
        long count = eventSourceRepo.countByStatusName(eventSource.getStatus().getName());

        // then - verify the result and output using assert statements
        assertThat(count).isGreaterThan(0);
    }

    @Test
    @DisplayName("Find by user is not null test")
    public void shouldFindByUserIsNotNull() {
        // when - action and behaviour that we are going to test
        List<EventSource> found = eventSourceRepo.findByUserIsNotNull();

        // then - verify the result and output using assert statements
        assertThat(found.size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Find by status name test")
    public void shouldFindByStatusName() {
        // when - action and behaviour that we are going to test
        List<EventSource> found = eventSourceRepo.findByStatusName(eventSource.getStatus().getName());

        // then - verify the result and output using assert statements
        assertThat(found.size()).isGreaterThan(0);
    }
}
