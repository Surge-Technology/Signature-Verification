package com.gen.eChannel.verification.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.gen.eChannel.verification.entities.Address;
import com.gen.eChannel.verification.entities.User;
import com.gen.eChannel.verification.repositories.UserRepo;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class UserRepoTest {

    @Autowired
    private UserRepo userRepo;

    private User user;

    @BeforeEach
    public void setUp() {
        Address address = new Address();
        address.setAddressLine1("123 Test Street");
        address.setCity("Test City");
        address.setPinCode("12345");
        address.setState("Test State");

        user = new User();
        user.setUserName("testUser");
        user.setEmail("testUser@test.com");
        user.setPassword("password");
        user.setDob(LocalDate.now());
        user.setPhone("1234567890");
        user.setAddress(address);

        userRepo.save(user);
    }

    @Test
    @DisplayName("Find by Email Test")
    public void findByEmailTest() {
        // when - action and behaviour that we are going to test
        Optional<User> optionalUser = userRepo.findByEmail(user.getEmail());

        // then - verify the result and output using assert statements
        assertTrue(optionalUser.isPresent());
        assertEquals(user.getEmail(), optionalUser.get().getEmail());
    }

    @Test
    @DisplayName("Find by Email and Password Test")
    public void findByEmailAndPasswordTest() {
        // when - action and behaviour that we are going to test
        Optional<User> optionalUser = userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());
        
        // then - verify the result and output using assert statements
        assertTrue(optionalUser.isPresent());
        assertEquals(user.getEmail(), optionalUser.get().getEmail());
        assertEquals(user.getPassword(), optionalUser.get().getPassword());
    }
}
