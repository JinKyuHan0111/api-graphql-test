package com.daelim.springtest.medium

import com.daelim.springtest.main.api.model.dto.TestDto
import graphql.Assert.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest
import kotlin.random.Random
import net.datafaker.Faker
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.stereotype.Service

@Service
class UserService {

    private val faker = Faker()

    // 유저 리스트 생성
    fun generateUserList(numUsers: Int): List<TestDto> {
        val userList = mutableListOf<TestDto>()
        repeat(numUsers) {
            val user = TestDto(
                id = faker.internet().uuid(),
                name = faker.name().fullName(),
                hp = Random.nextFloat() * (10 - 1),
                atk = Random.nextFloat() * (10 - 1),
                job = "",
                speed = Random.nextFloat() * (10 - 1),
                score = Random.nextInt(100, 10001)
            )
            userList.add(user)
        }
        return userList
    }

    // 유저 리스트 정렬
    fun sortUserList(users: List<TestDto>): List<TestDto> {
        return users.sortedWith(compareBy({ it.score }, { it.id }))
    }
}

@SpringBootTest
class UserName_Score_RankingService {

    private val faker = Faker()
    private lateinit var userService: UserService

    private lateinit var userList: List<TestDto>

    @BeforeEach
    fun setUp() {
        userList = userService.generateUserList(5) // 5개의 랜덤 유저 생성
    }


    @Test
    @DisplayName("유저 아이디가 다르고 점수가 같으면 아이디 순서대로 정렬되는지 확인")
    fun `유저 아이디가 다르고 점수가 같으면 아이디 순서대로 정렬되는지 확인`() {
        // Given - 이미 생성된 랜덤 유저 리스트

        // When - 유저 리스트를 점수와 아이디에 따라 정렬
        val sortedUsers = userService.sortUserList(userList)

        // Then - 정렬된 유저 리스트가 유저 아이디 순서대로 정렬되었는지 확인
        assertTrue(isSorted(sortedUsers)) { "유저 아이디가 다르고 점수가 같으면 아이디 순서대로 정렬되어야 합니다." }
    }

    // 유저 아이디가 다르고 점수가 같은지 확인하는 함수
    private fun isSorted(users: List<TestDto>): Boolean {
        for (i in 0 until users.size - 1) {
            if (users[i].score == users[i + 1].score) {
                if (users[i].id > users[i + 1].id) {
                    return false
                }
            }
        }
        return true
    }
}
