package com.opencode.practice.controller;

import com.opencode.practice.security.jwts.JwtTokenProwider.JwtTokenProvider;
import com.opencode.practice.model.User;
import com.opencode.practice.repos.UserRepo;
import com.opencode.practice.security.jwts.JwtTokenProwider.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Artem
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/auth")
public class AuthenticationRestControllerV1 {

    private final AuthenticationManager authenticationManager;
    private UserRepo userRepo;
    private JwtTokenProvider jwtTokenProvider;


    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, UserRepo userRepo, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepo = userRepo;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Регистрация пользователя
     *
     * @param user
     * @author Artem
     */
    @PostMapping("/signup")
    public void create(@RequestBody User user) {
        user.setPassword(String.valueOf(new BCryptPasswordEncoder(12).encode(user.getPassword())));
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        userRepo.save(user);
        throw new NoSuchCountExeption("Успешная регистрация");

    }


    /**
     * Авторизация пользователя
     *
     * @param request
     * @return
     * @author Artem
     */
    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            User user = userRepo.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
            String token = jwtTokenProvider.createToken(request.getEmail(), user.getRole().name(), user.getUsername());
            Map<Object, Object> response = new HashMap<>();
            response.put("email", request.getEmail());
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new NoSuchCountExeption("Invalid email/password combination");
        }
    }
  @PostMapping("/singin")
  public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO request) {
    logger.info("Работа метода signIn");
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
      User user = userRepositorySecurity.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
      String token = jwtTokenProvider.createToken(request.getEmail(), user.getRole().name());
      Map<Object, Object> response = new HashMap<>();
      response.put("email", request.getEmail());
      response.put("token", token);
      return ResponseEntity.ok(response);
    } catch (AuthenticationException e) {
      return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
    }
  }

    /**
     * Выход пользователя
     *
     * @param request
     * @param response
     * @author Artem
     */
    @PostMapping("/signout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
        throw new NoSuchCountExeption("logout");

    }
  @PostMapping("/singOut")
  public void logout(HttpServletRequest request, HttpServletResponse response) {
    logger.info("Работа метода signOut");
    SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
    securityContextLogoutHandler.logout(request, response, null);
  }
  @PostMapping("/regis")
  public void create(@RequestBody User user) {
    User user1 = user;
    logger.info("Работа метода create");
//        if (userRepositorySecurity.findByEmail(user1.getEmail()).get) {
    user.setPassword(String.valueOf(new BCryptPasswordEncoder(12).encode(user.getPassword())));
    user.setRole(Role.USER);
    user.setStatus(Status.ACTIVE);
    userRepositorySecurity.save(user);
//        } else
//            throw new NoSuchCountExeption("user with such email already exists\n");
  }

  @ExceptionHandler
  public ResponseEntity<ExceptionData> handleExeption(NoSuchCountExeption exeption) {
    ExceptionData exceptionData = new ExceptionData();
    exceptionData.setInfo(exeption.getMessage());
    return new ResponseEntity<>(exceptionData, HttpStatus.FORBIDDEN);
  }
}