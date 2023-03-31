package com.alwan.dansTest.jobsApps.service;

import com.alwan.dansTest.jobsApps.constant.RoleConstant;
import com.alwan.dansTest.jobsApps.dto.LoginRequest;
import com.alwan.dansTest.jobsApps.dto.RegistrationRequest;
import com.alwan.dansTest.jobsApps.dto.RegistrationResponse;
import com.alwan.dansTest.jobsApps.exception.EmailAlreadyRegistredException;
import com.alwan.dansTest.jobsApps.exception.UsernameAlreadyRegistredException;
import com.alwan.dansTest.jobsApps.model.Users;
import com.alwan.dansTest.jobsApps.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class AuthService {
    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    public RegistrationResponse register(RegistrationRequest request) {
        log.info("Start register new user");
        try{
            var getByUsername = userRepository.findByUsername(request.getUsername());
            if(getByUsername.isPresent()){
                throw new UsernameAlreadyRegistredException("Username already registred");
            }
            var getByEmail = userRepository.findByEmailAndRoles(request.getEmail(),RoleConstant.ROLE_ADMIN);
            if(getByEmail.isPresent()){
                throw new EmailAlreadyRegistredException("Email already registred");
            }
            Users user = new Users();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
            user.setUsername(request.getUsername());
            user.setRoles(RoleConstant.ROLE_USER);
            user.setIsActive(true);
            String token = jwtService.generateToken(request.getUsername());
            userRepository.save(user);
            return RegistrationResponse
                    .builder()
                    .status(200L)
                    .message("Success Register New Account")
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .name(user.getName())
                    .token(token)
                    .build();
        }catch (Exception e){
            throw e;
        }finally {
            log.info("Success Register New User");
        }
    }

    public RegistrationResponse registerAdmin(RegistrationRequest request) {
        log.info("Start register new user");
        try{
            var getByUsername = userRepository.findByUsername(request.getUsername());
            if(getByUsername.isPresent()){
                throw new UsernameAlreadyRegistredException("Username already registred");
            }
            var getByEmail = userRepository.findByEmailAndRoles(request.getEmail(),RoleConstant.ROLE_ADMIN);
            if(getByEmail.isPresent()){
                throw new EmailAlreadyRegistredException("Email already registred");
            }
            Users user = new Users();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
            user.setUsername(request.getUsername());
            user.setRoles(RoleConstant.ROLE_ADMIN);
            user.setIsActive(true);
            String token = jwtService.generateToken(request.getUsername());
            userRepository.save(user);
            return RegistrationResponse
                    .builder()
                    .status(200L)
                    .message("Success Register New Admin Account")
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .name(user.getName())
                    .token(token)
                    .build();
        }catch (Exception e){
            throw e;
        }finally {
            log.info("Success Register New User");
        }
    }

    public ResponseEntity<?> login(LoginRequest request) {
        log.info("Start login");
        Map<String, Object> responseMap = new HashMap<>();
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            if (authentication.isAuthenticated()) {
                log.info("Logged In");
                Optional<Users> optionalUsers = userRepository.findByUsername(request.getUsername());
                var getData = optionalUsers.get();
                String token = jwtService.generateToken(request.getUsername());
                responseMap.put("status", 200);
                responseMap.put("success", true);
                responseMap.put("message", "Logged In Success");
                responseMap.put("name", getData.getName());
                responseMap.put("email", getData.getEmail());
                responseMap.put("roles", getData.getRoles());
                responseMap.put("token", token);
                return ResponseEntity.ok(responseMap);
            } else {
                responseMap.put("status", 401);
                responseMap.put("success", false);
                responseMap.put("message", "Invalid Credentials");
                responseMap.put("idnMessage", "Credensial tidak valid");
                responseMap.put("engMessage", "Invalid Credentials");
                return ResponseEntity.status(401).body(responseMap);
            }
        } catch (DisabledException e) {
            e.printStackTrace();
            responseMap.put("status", 500);
            responseMap.put("success", false);
            responseMap.put("message", "User is disabled");
            responseMap.put("idnMessage", "User tidak aktif");
            responseMap.put("engMessage", "User is disabled");
            return ResponseEntity.status(500).body(responseMap);
        } catch (BadCredentialsException e) {
            responseMap.put("status", 401);
            responseMap.put("success", false);
            responseMap.put("message", "Invalid Credentials");
            responseMap.put("idnMessage", "Credensial tidak valid");
            responseMap.put("engMessage", "Invalid Credentials");
            return ResponseEntity.status(401).body(responseMap);
        } catch (Exception e) {
            e.printStackTrace();
            responseMap.put("status", 500);
            responseMap.put("success", false);
            responseMap.put("message", "Something went wrong");
            responseMap.put("idnMessage", "Terjadi kesalahan");
            responseMap.put("engMessage", "Something went wrong");
            return ResponseEntity.status(500).body(responseMap);
        }finally {
            log.info("Success login");
        }
    }
}
