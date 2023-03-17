package com.cj.cga101g1.util.jwt;


import io.jsonwebtoken.*;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;

import javax.security.auth.message.AuthException;
import java.util.Date;
import java.util.HashMap;
/**
 * JWT參數設定
 */
@Component
public class JwtTokenUtils {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    //密鑰
    private static final String SECRET = "jwtSECRET";
    //簽發人
    private static final String ISS = "CGA101Server";
    // 角色的key
    private static final String ROLE_CLAIMS = "rol";
    // 過期時間3600秒
    private static final long EXPIRATION = 3600L;
    // 過期時間是604800秒，相當於7天
    private static final long EXPIRATION_REMEMBER = 604800L;
    /**
     * 創立JWT Token
     */
    public static String createToken(String username, String role, boolean isRememberMe) {
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        HashMap<String, Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, role);
        return Jwts.builder().signWith(SignatureAlgorithm.HS512, SECRET)
                .setClaims(map)
                .setIssuer(ISS)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }

    // 从token中获取用户名
    public static String getUsername(String token) {
        return getTokenBody(token).getSubject();
    }

    // 获取用户角色
    public static String getUserRole(String token) {
        return (String) getTokenBody(token).get(ROLE_CLAIMS);
    }

    // 是否已过期
    public static boolean isExpiration(String token) {
        return getTokenBody(token).getExpiration().before(new Date());
    }

    static Claims getTokenBody(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    /**
     * 驗證Token
     */
    public boolean validateToken(String token) throws AuthException {
        try {
            Jwts.parser()
                    .setSigningKey( SECRET )
                    .parseClaimsJws( token );
            return true;
        } catch (SignatureException e) {
            return false;
        }
        catch (MalformedJwtException e) {
            return false;
        }
        catch (ExpiredJwtException e) {
            return false;
        }
        catch (UnsupportedJwtException e) {
            return false;
        }
        catch (IllegalArgumentException e) {
            return false;
        }
    }
}
