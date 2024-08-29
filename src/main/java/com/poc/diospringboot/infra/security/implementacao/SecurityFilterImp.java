package com.poc.diospringboot.infra.security.implementacao;

import com.poc.diospringboot.domain.user.User;
import com.poc.diospringboot.service.FindByEmailService;
import com.poc.diospringboot.service.exception.ServiceException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilterImp extends OncePerRequestFilter {
    private final TokenServiceImp tokenServiceImp;
    private final FindByEmailService findByEmailService;

    public SecurityFilterImp(TokenServiceImp tokenServiceImp, FindByEmailService findByEmailService) {
        this.tokenServiceImp = tokenServiceImp;
        this.findByEmailService = findByEmailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = recoverToken(request);
        var authorizedEmail = validateTokenAndReturnUserEmail(recoverToken(request));
        ifTokenNotPresent(request, response, filterChain, token);
        securityContext(authorizedEmail);

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }

    private String validateTokenAndReturnUserEmail(String token) {
        return tokenServiceImp.validateToken(token);
    }

    private void ifTokenNotPresent(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, String token) throws ServletException, IOException {
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }
    }

    private User findByUserWithAuthorizedEmail(String authorizedEmail) throws ServiceException {
        return findByEmailService.execute(authorizedEmail);
    }

    private void securityContext(String authorizedEmail) {
        if (authorizedEmail != null) {
            try {
                User user = findByUserWithAuthorizedEmail(authorizedEmail);

                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getGrantedAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
