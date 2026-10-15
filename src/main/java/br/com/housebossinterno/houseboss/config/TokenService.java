package br.com.housebossinterno.houseboss.config;

import br.com.housebossinterno.houseboss.entity.FuncionarioEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenService {

    @Value("${houseboss.security.secret}")
    private String secret;

    public String generateToken(FuncionarioEntity funcionarioEntity) {

        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withSubject(funcionarioEntity.getEmail())
                .withClaim("funcionarioId", funcionarioEntity.getId())
                .withClaim("name", funcionarioEntity.getNome())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .withIssuer("API House Boss")
                .sign(algorithm);
    }

    public Optional<JwtUserData> verifyToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT jwt = JWT.require(algorithm)
                    .build()
                    .verify(token);

            return Optional.of(JwtUserData.builder()
                    .id(jwt.getClaim("funcionarioId").asLong())
                    .email(jwt.getSubject())
                    .build());

        } catch (JWTVerificationException ex) {
            return Optional.empty();
        }

    }
}
