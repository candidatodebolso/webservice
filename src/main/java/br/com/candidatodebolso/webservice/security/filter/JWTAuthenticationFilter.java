package br.com.candidatodebolso.webservice.security.filter;

import br.com.candidatodebolso.webservice.persistence.model.user.ApplicationUser;
import br.com.candidatodebolso.webservice.util.Token;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Date;

import static br.com.candidatodebolso.webservice.security.filter.SecurityConstants.*;
import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static java.time.ZoneOffset.UTC;
import static java.time.temporal.ChronoUnit.MILLIS;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ApplicationUser applicationUser = new ObjectMapper().readValue(request.getInputStream(), ApplicationUser.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(applicationUser.getUsername(), applicationUser.getPassword()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        ZonedDateTime expirationTimeUTC = ZonedDateTime.now(UTC).plus(EXPIRATION_TIME, MILLIS);
        ApplicationUser applicationUser = (ApplicationUser) authResult.getPrincipal();
        String token;
        String json;
        boolean isVoter = applicationUser.getVoter() != null;
        if (isVoter) {
            token = buildToken(applicationUser.getUsername()).compact();
            json = new Token(TOKEN_PREFIX + token).toJson();
        } else {
            token = buildTokenWithExpirationTime(applicationUser.getUsername(), Date.from(expirationTimeUTC.toInstant())).compact();
            json = new Token(TOKEN_PREFIX + token, expirationTimeUTC).toJson();
        }
        addTokenToHeader(response, token);
        response.getWriter().write(json);
    }

    private JwtBuilder buildToken(String username) {
        return Jwts.builder().setSubject(username).signWith(HS256, SECRET);
    }

    private JwtBuilder buildTokenWithExpirationTime(String username, Date expirationTime) {
        return buildToken(username).setExpiration(expirationTime);
    }

    private void addTokenToHeader(HttpServletResponse response, String token) {
        response.addHeader("Content-Type", APPLICATION_JSON_UTF8_VALUE);
        response.addHeader(HEADER_STRING, token);
    }
}
