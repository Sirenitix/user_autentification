package swag.rest.bank_app_delivery.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import swag.rest.bank_app_delivery.entity.*;
import swag.rest.bank_app_delivery.jwt.JwtUtil;
import swag.rest.bank_app_delivery.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.stream.Collectors;


@RestController("/")
@CrossOrigin(origins = "*")
public class AccountRestController  {

    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;

    JwtUtil jwtUtil;


    @PostMapping("/register")
    public ResponseEntity<Users> save(@RequestBody Users user) {
        Users userEntity = userService.save(user);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{username}")
                .buildAndExpand(userEntity.getUsername()).toUriString());
        return ResponseEntity.created(uri).build();
    }


    @Operation(description = "Login")
    @PostMapping("/login")
    public void fakeLogin(@RequestBody Users user) {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticateUser(@Valid @RequestBody Users user, HttpServletRequest request, HttpServletResponse response) {
        user.setRole("ROLE_USER");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(),user.getAuthorities()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.createAccessToken(user.getUsername(), request.getRequestURL().toString(), user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        Cookie cookie = new Cookie("token", jwt);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return ResponseEntity.ok(jwt);
    }

    @Operation(description = "Logout")
    @PostMapping("/logout")
    public void fakeLogout() {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }

    @Operation(description = "Get Admin Credentials")
    @PostMapping("/admin")
    public Admin getAdminCredentials() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return new Admin(auth.getPrincipal().toString(), auth.getAuthorities().toString() == "[ROLE_ADMIN]" ? true : false);
    }

}
