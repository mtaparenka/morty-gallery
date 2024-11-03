package com.mtaparenka.mortygallery.security;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.stream.Collectors;

public class JwtUtil {
    private static final String HMAC_256_SECRET = "vnYwV4ULtYFeKRiYNvg54E68D0vLZgFq7QTOAoodpII=";
    private static final JwtParser parser = Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(HMAC_256_SECRET.getBytes())).build();

    public static String createToken(Authentication authentication) {
        String username = authentication.getName();
        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .subject(username)
                .claim("roles", roles)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 600000)) // 1 min
                .signWith(Keys.hmacShaKeyFor(HMAC_256_SECRET.getBytes()))
                .compact();
    }

    public static String getSubject(String jwt) {
        return parser.parseSignedClaims(jwt).getPayload().getSubject();
    }

    public static boolean isExpired(String jwt) {
        return parser.parseSignedClaims(jwt).getPayload().getExpiration().before(new Date());
    }

    public static boolean isValid(String jwt) {
        return parser.isSigned(jwt);
    }
}
