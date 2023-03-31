package com.alwan.dansTest.jobsApps.service;

import com.alwan.dansTest.jobsApps.config.UserInfoUserDetails;
import com.alwan.dansTest.jobsApps.model.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtServiceTest {

    private JwtService jwtService;

    private Users user;
    @BeforeEach
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

        jwtService = new JwtService();
    }

    @Test
    public void testExtractUsername() {
        String username = "alwanhafidzin";
        String token = jwtService.generateToken(username);

        String extractedUsername = jwtService.extractUsername(token);

        Assertions.assertEquals(username, extractedUsername);
    }

    @Test
    public void testExtractExpiration() {
        String username = "alwanhafidzin";
        String token = jwtService.generateToken(username);

        Date expirationDate = jwtService.extractExpiration(token);

        Assertions.assertNotNull(expirationDate);
    }

    @Test
    public void testExtractClaim() {
        String username = "alwanhafidzin";
        String token = jwtService.generateToken(username);

        Claims claims = jwtService.extractAllClaims(token);
        String subject = jwtService.extractClaim(token, Claims::getSubject);

        Assertions.assertEquals(username, subject);
        Assertions.assertEquals(claims.getSubject(), subject);
    }

    @Test
    public void testIsTokenExpired() throws InterruptedException {
        String username = "alwanhafidzin";
        String token = jwtService.generateToken(username);

        Boolean isExpired = jwtService.isTokenExpired(token);

        Assertions.assertFalse(isExpired);
    }

    @Test
    public void testValidateToken() {
        String username = "alwanhafidzin";
        String token = jwtService.generateToken(username);

        UserDetails userDetails = new UserInfoUserDetails(user);
        Boolean isValid = jwtService.validateToken(token, userDetails);

        Assertions.assertTrue(isValid);
    }

    @Test
    public void testCreateToken() {
        String username = "alwanhafidzin";
        Map<String, Object> claims = new HashMap<>();

        String token = jwtService.createToken(claims, username);

        Assertions.assertNotNull(token);
    }

    @Test
    public void testGetSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(JwtService.SECRET);
        var keys = Keys.hmacShaKeyFor(keyBytes);
        Assertions.assertNotNull(keys);
    }
}

