package swag.rest.aster.todo.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import swag.rest.aster.todo.entity.CustomUserDetailsService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = null;
        if(request.getServletPath().equals("/login") || request.getServletPath().equals("/refreshToken") || request.getServletPath().equals("/authenticate")) {
            filterChain.doFilter(request, response);
        } else {
                try {
                    if (request.getHeader("token") != null) {
                                token = request.getHeader("token");
                                UsernamePasswordAuthenticationToken authenticationToken = JwtUtil.parseToken(token);
                                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                                System.out.println(SecurityContextHolder.getContext().getAuthentication());
                                filterChain.doFilter(request, response);
                        }
                       else  {
                        System.out.println(token + " - token");
                        filterChain.doFilter(request, response);
                    }

                }
                catch (Exception e) {
                    log.error(String.format("Error auth token: %s", token), e);
                    response.setStatus(FORBIDDEN.value());
                    Map<String, String> error = new HashMap<>();
                    error.put("errorMessage", e.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
            }
        }
    }

