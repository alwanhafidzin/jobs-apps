package com.alwan.dansTest.jobsApps.repository;

import com.alwan.dansTest.jobsApps.model.Users;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UsersRepositoryTest {

    @MockBean
    private UsersRepository usersRepository;

    private Users user;
    @Before
    public void setUp() {
        user = Users
                .builder()
                .id(1L)
                .roles("ROLE_USER")
                .email("alwanhafidzin@gmail.com")
                .name("Alwan Hafidzin Firdaus")
                .password("alwan123")
                .username("alwanhafidzin")
                .isActive(true)
                .build();

    }

    @Test
    public void testFindByUsername() {
        Mockito.when(usersRepository.findByUsername("alwanhafidzin")).thenReturn(Optional.of(user));
        //Call findByName
        Optional<Users> result = usersRepository.findByUsername("alwanhafidzin");
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("alwanhafidzin", result.get().getUsername());
        Assertions.assertEquals("alwan123", result.get().getPassword());
    }
}
