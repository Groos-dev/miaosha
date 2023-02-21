package com.cat.miaosha.utils;

import cn.hutool.jwt.JWTUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Mr.xin
 */
@Slf4j
public class JwtUtils {

    private static final String TOKEN_SECRET = "pdafdasfdasdasfd";

    /**
     * generate token by userid
     *
     * @param userId     userid
     * @param expireTime the timeunit is minute
     * @return token
     */
    public static String createToken(Long userId, int expireTime) {
        try {
            Date date = new Date(System.currentTimeMillis() + (expireTime * 1000L));
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            return JWT.create()
                    .withClaim("userId", userId)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            log.error("JwtUtils: token创建失败");
            return null;
        }

    }


    public static Long verifyToken(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token)
                .getClaim("userId")
                .asLong();
    }

}
