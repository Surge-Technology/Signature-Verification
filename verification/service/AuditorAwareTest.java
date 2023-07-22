package com.gen.eChannel.verification.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.gen.eChannel.verification.services.impl.AuditorAwareImpl;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuditorAwareTest {

    @InjectMocks
    private AuditorAwareImpl auditorAware;

    @Test
    @DisplayName("Get Current Auditor Test")
    public void shouldGetCurrentAuditor() {
        // given - precondition or setup

        // when - action and behaviour that we are going to test
        Optional<String> currentAuditor = auditorAware.getCurrentAuditor();

        // then - verify the result and output using assert statements
        assertTrue(currentAuditor.isEmpty(), "Expected current auditor to be empty");
    }
}
