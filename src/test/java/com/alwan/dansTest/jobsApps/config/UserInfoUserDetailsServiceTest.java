package com.alwan.dansTest.jobsApps.config;

import com.alwan.dansTest.jobsApps.model.Users;
import com.alwan.dansTest.jobsApps.repository.UsersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserInfoUserDetailsServiceTest {

    @MockBean
    private UsersRepository usersRepository;

    @MockBean
    private UserInfoUserDetailsService userDetailsService;

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
    public void testLoadUserByUsername() {
        Mockito.when(usersRepository.findByUsername("alwanhafidzin")).thenReturn(Optional.of(user));
        var userInfo = usersRepository.findByUsername("alwanhafidzin");
        var userDetails = userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " +"alwanhafidzin"));
        Assertions.assertEquals("alwanhafidzin", userDetails.getUsername());
        Assertions.assertEquals(1, userDetails.getAuthorities().size());
        Assertions.assertEquals("ROLE_USER", userDetails.getAuthorities().iterator().next().getAuthority());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameNotFound() {
        Mockito.when(usersRepository.findByUsername("testuser")).thenReturn(Optional.empty());
        Optional<Users> userInfo = usersRepository.findByUsername("testuser");
        userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " +"testuser"));
    }
}

