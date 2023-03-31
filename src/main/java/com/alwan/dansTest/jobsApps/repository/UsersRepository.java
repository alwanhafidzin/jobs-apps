package com.alwan.dansTest.jobsApps.repository;

import com.alwan.dansTest.jobsApps.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUsername(String username);
    Optional<Users> findByUsernameAndRoles(String username,String roles);
    Optional<Users> findByEmailAndRoles(String email, String roles);
    Optional<Users> findByEmail(String email, String roles);

}
