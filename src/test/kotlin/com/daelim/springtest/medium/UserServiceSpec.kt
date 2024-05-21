package com.daelim.springtest.medium

import com.daelim.springtest.main.api.model.dto.TestDto
import com.daelim.springtest.main.resolver.PostResolver
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import net.datafaker.Faker
import org.junit.jupiter.api.Assertions.*
import kotlin.random.Random

@SpringBootTest
class UserServiceSpec {

    private val faker = Faker()
    private lateinit var randomUser: TestDto

    @Autowired
    private lateinit var postResolver: PostResolver

    @BeforeEach
    fun setup() {
        val username = faker.internet().username()
        randomUser = TestDto(
            id = "user123",
            name = username,
            hp = Random.nextFloat() * (10 - 1),
            atk = Random.nextFloat() * (10 - 1),
            job = "",
            speed = Random.nextFloat() * (10 - 1),
            score = Random.nextInt(),
        )
        // 유저 생성
        postResolver.createTest("user123", randomUser.job)
    }

    @Test
    fun `유저아이디의 중복체크하고 점수 업데이트하기`() {
        // Given
        val userId = "user123"

        // When
        val initialScore = postResolver.getUserScore(userId)
        val updatedScore = 100f // 업데이트할 점수

        // Given - userId에 해당하는 사용자를 찾기
        val userToUpdate = postResolver.findTestByName(userId)
        assertNotNull(userToUpdate) // userId에 해당하는 사용자가 있는지 확인

        // When - userId에 해당하는 사용자의 점수 업데이트
        if (userToUpdate != null) {
            postResolver.updateScore(userToUpdate, updatedScore)
        } else {
            fail("해당 사용자를 찾을 수 없습니다.")
        }

        // Then
        val userScore = postResolver.getUserScore(userId)
        assertNotNull(userScore) // 유저 점수를 가져오는 것이 성공했는지 확인
        assertEquals(updatedScore, userScore) // 점수가 올바르게 업데이트 되었는지 확인
    }
}