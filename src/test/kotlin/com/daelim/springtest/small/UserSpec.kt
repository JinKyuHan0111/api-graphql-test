package com.daelim.springtest.small

import com.daelim.springtest.main.api.model.dto.TestDto
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import net.datafaker.Faker
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.yaml.snakeyaml.internal.Logger
import kotlin.random.Random

@SpringBootTest
class UserSpec {
    private val faker = Faker()

    private lateinit var randomUser: TestDto

    @BeforeEach
    fun setup() {
        //아이디 랜덤생성하는걸 못찾았음
        val userid = faker.internet().password();
        val username = faker.internet().username()
        val password = faker.internet().password()
        randomUser = TestDto(
            id = userid,
            name = username,
            hp = Random.nextFloat() * (10 - 1),
            atk = Random.nextFloat() * (10 - 1),
            job = "",
            speed = Random.nextFloat() * (10 - 1),
            score = java.util.Random().nextFloat(500F),
        )
    }

    @Test
    @DisplayName("유저 아이디가 영어와 숫자만 사용되었는지 확인")
    fun `유저 아이디가 영어와 숫자만 사용되었는지 확인`() {
        // Given - setup()에서 이미 처리됨
        val userid = randomUser.id

        // Then
        for (c in userid) {
            assertTrue(c in 'A'..'Z' || c in 'a'..'z' || c in '0'..'9',  userid)
        }
    }

}