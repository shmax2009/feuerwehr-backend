package com.feuerwehr.gateway.services.Auth;

import com.feuerwehr.gateway.domain.entity.Type;
import com.feuerwehr.gateway.domain.extra.AuthData;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@AllArgsConstructor
@Data
public class JwtService {

    private final AuthData authData;

    private final TokenService tokenService;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    public String generateAuthToken(UserDetails userDetails) {
        String nextToken = generateToken(new HashMap<>(), userDetails, authData.getAuth_token_expiration());
        tokenService.updateTokenRepository(userDetails.getUsername(), nextToken, Type.AuthToken);
        return nextToken;
    }

    public String generateRefreshToken(UserDetails userDetails) {
        String nextToken = generateToken(new HashMap<>(), userDetails, authData.getRefresh_token_expiration());
        tokenService.updateTokenRepository(userDetails.getUsername(), nextToken, Type.RefreshToken);
        return nextToken;
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            Long expirationTime
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        if (tokenService.invalidToken(token))
            return false;
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        if (tokenService.invalidToken(token))
            return true;
        if (token == null)
            return false;
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        try {
            return extractClaim(token, Claims::getExpiration);
        } catch (Exception exception) {
            return new Date(0);
        }

    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(authData.getSecret_key());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}