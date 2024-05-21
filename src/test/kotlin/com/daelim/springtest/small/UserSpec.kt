package com.daelim.springtest.small

import com.daelim.springtest.main.api.model.dto.TestDto
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import net.datafaker.Faker
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import kotlin.random.Random

@SpringBootTest
class UserSpec(s: String, s1: String, fl: Float) {
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
            score = java.util.Random().nextInt(100, 10001),
        )
    }

    @Test
    @DisplayName("유저 아이디가 영어와 숫자만 사용되었는지 확인")
    fun `유저 아이디가 영어와 숫자만 사용되었는지 확인`() {
        // Given - setup()에서 이미 처리됨
        val userid = randomUser.id

        // Then
        for (c in userid) {
            assertTrue(c in 'A'..'Z' || c in 'a'..'z' || c in '0'..'9', userid)
        }
    }

    @Test
    @DisplayName("유저아이디의 최대 글자수 안넘는지 파악하는 알고리즘Test")
    fun `유저 아이디의 최대 글자수 안넘는지 파악`() {
        val userid = randomUser.id

        // When
        val maxLength = 15 // 최대 글자 수 설정

        // Then
        assertTrue(userid.length <= maxLength, "User ID exceeds maximum length of $maxLength characters: $userid")
    }

    @Test
    @DisplayName("최저, 최고점 설정후 그사이만 점수 생성되는지 확인")
    fun `최저, 최고점 설정후 그사이만 점수 생성되는지 확인`() {

        val minScore = 100
        val maxScore = 10000
        val userscore = randomUser.score

        //Then
        assertTrue(userscore in minScore..maxScore, userscore.toString())
    }
}
