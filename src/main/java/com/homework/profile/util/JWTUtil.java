package com.homework.profile.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTUtil {

    public static DecodedJWT getDecodedJwt(String jwt) {
        DecodedJWT decodedJWT = null;
        try {
            decodedJWT = JWT.decode(jwt);
        } catch (JWTDecodeException exception) {
            //Invalid token
        }
        return decodedJWT;
    }
}
