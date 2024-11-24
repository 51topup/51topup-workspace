package shop.topup.workspace.ui.security

import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.JWSHeader
import com.nimbusds.jose.JWSSigner
import com.nimbusds.jose.JWSVerifier
import com.nimbusds.jose.crypto.MACSigner
import com.nimbusds.jose.crypto.MACVerifier
import com.nimbusds.jwt.JWTClaimsSet
import com.nimbusds.jwt.SignedJWT
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*


@Component
class JwtService(
    @Value("\${jwt.auth.secret}") val jwtAuthSecret: String,
    @Value("\${jwt.issuer}") val issuer: String,
    @Value("\${jwt.expiration}") val expirationSeconds: Long
) {

    fun generate(nick: String, role: String): String {
        val signer: JWSSigner = MACSigner(jwtAuthSecret.toByteArray())
        // Prepare JWT with claims set
        val claimsSet = JWTClaimsSet.Builder()
            .subject(nick)
            .issuer(issuer)
            .issueTime(Date())
            .expirationTime(Date(Date().time + expirationSeconds * 1000))
            .claim("roles", listOf(role))
            .build()
        val signedJWT = SignedJWT(JWSHeader(JWSAlgorithm.HS256), claimsSet)
        signedJWT.sign(signer)
        return signedJWT.serialize()
    }

    fun verify(jwtToken: String): JWTClaimsSet? {
        val signedJWT = SignedJWT.parse(jwtToken)
        val verifier: JWSVerifier = MACVerifier(jwtAuthSecret.toByteArray())
        if (signedJWT.verify(verifier) && signedJWT.jwtClaimsSet.issuer == issuer) {
            return signedJWT.jwtClaimsSet
        }
        return null
    }
}