package com.muhammet.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.muhammet.exception.ErrorType;
import com.muhammet.exception.ElasticServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenManager {

    @Value("${myjwt.secretkey}")
    private String secretKey;
    @Value("${myjwt.audience}")
    private String audience;
    @Value("${myjwt.issuer}")
    private String issuer;

    public String createToken(Long authid){
        String token=null;
        try{
            /**
             * Token yayımcısı -> BilgeAdamAuth
             * Token Üretim tarihi
             * Token ne kadar bir süre ile geçerli olacak
             * Token içerisine tekrar kullanabilmek üzere Claim nesneleri koyabilirsiniz.
             * bu nesneler Key-Value şeklinde bilgi tutarlar ve public olarak görüntülenebilirler.
             * Token bilgisinin şifrelenmesi gereklidir. bu nedenle imzalama yöntemi seçilmeli ve belirlenen
             * algoritma ile sign işlemi yapılmalıdır.
             */
            Algorithm algorithm=Algorithm.HMAC256(secretKey);
            token = JWT.create()
                    .withAudience(audience) // Kitle
                    .withIssuer(issuer) // Yayımcı
                    .withIssuedAt(new Date()) // oluşturma zamanı
                    .withExpiresAt(new Date(System.currentTimeMillis()+(1000*60))) // geçersi kılınma zamanı (60sn sonra)
                    .withClaim("authid",authid) // kullanılacak bilgiler.
                    .sign(algorithm); // şifremele- imzalama işlemi yapılır.
           return token;
        }catch (Exception e){
            return null;
        }
    }

    public Optional<Long> getByIdFromToken(String token){
        try{
            Algorithm algorithm=Algorithm.HMAC256(secretKey);
            JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withAudience(audience) // Kitle
                    .withIssuer(issuer) // Yayımcı
                    .build(); // kod oluşturulur.
            DecodedJWT decodedToken = jwtVerifier.verify(token); // tokenın geçerliliği doğrulur.
            if(decodedToken==null) throw new ElasticServiceException(ErrorType.GECERSIZ_TOKEN);
            Long authid = decodedToken.getClaim("authid").asLong();
            return  Optional.of(authid);
        }catch (Exception e){
            throw new ElasticServiceException(ErrorType.GECERSIZ_TOKEN);
        }
    }

}
