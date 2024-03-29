package swag.rest.aster.todo.controller;


import io.swagger.v3.oas.annotations.Operation;
import java.net.URI;
import java.time.Duration;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import swag.rest.aster.todo.entity.AdminCredentials;
import swag.rest.aster.todo.entity.Users;
import swag.rest.aster.todo.jwt.JwtUtil;
import swag.rest.aster.todo.service.UserService;


@RestController("/user")
public class AccountRestController  {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    JwtUtil jwtUtil;


    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Users> save(@RequestBody Users user) {
        Users userEntity = userService.save(user);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{username}")
                .buildAndExpand(userEntity.getUsername()).toUriString());
        return ResponseEntity.created(uri).build();
    }

    @Operation(description = "Login")
    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:3000")
    public void fakeLogin(@RequestBody Users user) {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }

    @Operation(description = "Password renewal")
    @PostMapping("/password")
    public Users changePassword(@RequestBody String newPassword) {
        return userService.changePassword(newPassword);
    }


    @PostMapping("/authenticate")
    @CrossOrigin(origins = "http://localhost:3000")
    public String authenticateUser(@Valid @RequestBody Users user, HttpServletRequest request, HttpServletResponse response) {
        user.setRole("ROLE_USER");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(),user.getAuthorities()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.createAccessToken(user.getUsername(), request.getRequestURL().toString(), user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        ResponseCookie cookie = ResponseCookie.from("token", jwt) // key & value
                .httpOnly(true)
                .secure(true)
                .maxAge(Duration.ofHours(100))
                .sameSite("None")  // sameSite
                .build();
        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return jwt;
    }


    @Operation(description = "Logout")
    @CrossOrigin("http://localhost:3000")
    @PostMapping("/logout")
    public void fakeLogout() {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }

    @Operation(description = "List")
    @GetMapping("/admin")
    @CrossOrigin(origins = "http://localhost:3000")
    public AdminCredentials aboutAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AdminCredentials adminCredentials = new AdminCredentials(
                auth.getPrincipal().toString(),
                auth.getAuthorities().toString().equals("[ROLE_ADMIN]"));
            return adminCredentials;
    }



}
