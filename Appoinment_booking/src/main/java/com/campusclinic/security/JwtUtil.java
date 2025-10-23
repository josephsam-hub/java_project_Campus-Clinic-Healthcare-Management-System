package com.campusclinic.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
	
	// Secret key for signing JWT (In production, use environment variable)
	private static final String SECRET_KEY = "campusclinic2025secretkeyforsigningJWTtokens12345678901234567890";
	
	// Token validity: 24 hours
	private static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60 * 1000;
	
	// Generate signing key
	private Key getSigningKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}
	
	// Extract username (email) from token
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	// Extract expiration date from token
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	// Extract specific claim from token
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	// Extract all claims from token
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	// Check if token is expired
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	// Generate token for user
	public String generateToken(String email, String role) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", role);
		return createToken(claims, email);
	}
	
	// Create JWT token
	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	// Validate token
	public Boolean validateToken(String token, String username) {
		final String extractedUsername = extractUsername(token);
		return (extractedUsername.equals(username) && !isTokenExpired(token));
	}
	
	// Extract role from token
	public String extractRole(String token) {
		Claims claims = extractAllClaims(token);
		return claims.get("role", String.class);
	}
}
