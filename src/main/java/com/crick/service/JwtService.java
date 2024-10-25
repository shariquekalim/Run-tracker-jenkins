
package com.crick.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

	private String SECRET = "/vLbHo0qY2BJNQFeHYKA+1Iwp3cyOKPaQ/i4ngAaC30=";

	
	
	public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

	public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
	
	private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
	
	 private Boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }

	    public Boolean validateToken(String token, UserDetails userDetails) {
	        final String username = extractUsername(token);
	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }
	
	public String generateToken(String name) {

		Map<String, Object> claim = new HashMap<>();
		return createToken(name, claim);

	}

	private String createToken(String name, Map<String, Object> claim) {

		return Jwts.builder().setClaims(claim).setSubject(name).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	private Key getSignKey() {
		byte[] keybytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keybytes);
	}
}



/*
 * package com.crick.service; import java.security.Key; import java.util.Base64;
 * 
 * import io.jsonwebtoken.SignatureAlgorithm; import
 * io.jsonwebtoken.security.Keys;
 * 
 * public class JwtService { public static void main(String[] args) { Key key =
 * Keys.secretKeyFor(SignatureAlgorithm.HS256); String base64Key =
 * Base64.getEncoder().encodeToString(key.getEncoded());
 * System.out.println(base64Key); } }
 */
