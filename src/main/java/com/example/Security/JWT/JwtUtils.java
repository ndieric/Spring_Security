package com.example.Security.JWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.Security.Service.UtilDetailsImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import java.util.Date;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    @Value("${com.example.Security.JWT.jwtSecret}")
	private String jwtSecret;
	@Value("${com.example.Security.JWT.jwtExpirationMs}")
	private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication){
        UtilDetailsImpl utilPrincipal = (UtilDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
        .setSubject((utilPrincipal.getUsername()))
        .setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime()+jwtExpirationMs))
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    public <T> T extractClaim(String token , Function<Claims, T> ClaimsResolver){
        final Claims claims = extractAllClaims(token);
        return ClaimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return null;
    }

    private String extractAClaims(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token , UserDetails userDetails){
        final String username = extractUsername(token);
        try{
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        }catch(SignatureException e){
            logger.error("arg0",e.getMessage());
            System.out.println("Invalid signature {}");
        }catch(MalformedJwtException e){
            logger.error("arg0",e.getMessage());
            System.out.println("Invalid signature {}");
    }catch(ExpiredJwtException e){
        logger.error("arg0",e.getMessage());
        System.out.println("Invalid signature {}");
    
}catch(UnsupportedJwtException e){
    logger.error("arg0",e.getMessage());
    System.out.println("Invalid signature {}");
}catch(IllegalArgumentException e){
    logger.error("arg0",e.getMessage());
    System.out.println("Invalid signature {}");

}
return false;

    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());

    }
    public Date extractExpiration(String token ){
        return extractClaim(token, Claims::getExpiration);


    }

    public boolean validateJwtToken(String authToken){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        }catch(SignatureException e){
            logger.error("arg0",e.getMessage());
        }catch(MalformedJwtException e){
            logger.error("arg0",e.getMessage());
        }catch(ExpiredJwtException e){
            logger.error("arg0",e.getMessage());
        }catch(UnsupportedJwtException e){
            logger.error("arg0",e.getMessage());
        }catch(IllegalArgumentException e){
            logger.error("arg0",e.getMessage());
        }
        return false;
    }
}

