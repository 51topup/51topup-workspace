package shop.topup.workspace.ui.security

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class JwtServiceTest {
    private val jwtService = JwtService("c92cb7e8-9781-4350-8d75-7c775d525534", "shop.51topup", 604800L)

    @Test
    fun testGenerateJwt() {
        val jwt = jwtService.generate("linux_china", "ADMIN")
        val jwtClaimsSet = jwtService.verify(jwt)
        assertThat(jwtClaimsSet).isNotNull
    }

    @Test
    fun testVerify() {
        val token =
            "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzaG9wLjUxdG9wdXAiLCJzdWIiOiIxODYxMTExMTExMSIsImV4cCI6MTczMzAzMDI5OSwiaWF0IjoxNzMyNDI1NDk5LCJyb2xlcyI6WyJTRUxMRVIiXX0.2FvsVJbFvVCgdIpuEgEl1S5C6_CSVTOFRhC_WGWmf4Q"
        val jwtClaimsSet = jwtService.verify(token)
        assertThat(jwtClaimsSet).isNotNull
    }
}