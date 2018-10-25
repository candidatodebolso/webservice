package br.com.candidatodebolso.webservice.security.filter;

import br.com.candidatodebolso.webservice.exception.ExpiredTokenException;
import br.com.candidatodebolso.webservice.persistence.model.user.ApplicationUser;
import br.com.candidatodebolso.webservice.service.impl.CustomUserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static br.com.candidatodebolso.webservice.security.filter.SecurityConstants.*;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private final CustomUserDetailsServiceImpl customUserDetailsServiceImpl;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, CustomUserDetailsServiceImpl customUserDetailsServiceImpl) {
        super(authenticationManager);
        this.customUserDetailsServiceImpl = customUserDetailsServiceImpl;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(HEADER_STRING);
        boolean tokenIsInvalid = token == null || !token.startsWith(TOKEN_PREFIX);
        if (tokenIsInvalid) {
            chain.doFilter(request, response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(getAuthenticationToken(token));
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
        try {
            String username = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody().getSubject();
            UserDetails userDetails = customUserDetailsServiceImpl.loadUserByUsername(username);
            ApplicationUser applicationUser = customUserDetailsServiceImpl.loadApplicationUserByUsername(username);
            return userDetails != null ? new UsernamePasswordAuthenticationToken(applicationUser, null, userDetails.getAuthorities()) : null;
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException("Seu login expirou! Faça-o novamente.");
        }

    }
}
