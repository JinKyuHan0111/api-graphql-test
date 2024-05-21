package com.daelim.springtest.main.resolver

import com.daelim.springtest.main.api.model.dto.TestDto
import graphql.kickstart.tools.GraphQLMutationResolver
import graphql.kickstart.tools.GraphQLQueryResolver
import kotlinx.coroutines.Job
import net.datafaker.Faker
import org.springframework.stereotype.Component
import java.util.*

@Component
class PostResolver : GraphQLQueryResolver, GraphQLMutationResolver {
    private val tests = mutableListOf<TestDto>()

    val faker = Faker(Locale.KOREA)

    fun findAllTests(): List<TestDto> {
        return tests
    }


    fun findTestByName(name: String): TestDto? {
        return tests.find { it.name == name }
    }
    fun findTestByJob(job: String): TestDto? {
        return tests.find { it.job == job }
    }

    fun findTestById(id: String): TestDto? {
        return tests.find { it.id == id }
    }

    fun updateScore(testDto: TestDto, newScore: Float): TestDto {
        testDto.score = newScore
        return testDto
    }

    fun getUserScore(id: String): Float? {
        val user = tests.find { it.name == id }
        return user?.score
    }


    fun createTest(ChaName: String, ChaJob: String): TestDto {
        val test = TestDto(
            id = "",
            name = ChaName,
            hp = Random().nextInt(1000).toFloat(),
            speed = Random().nextInt(10).toFloat(),
            atk = Random().nextInt(200).toFloat(),
            job = ChaJob,
            score = Random().nextFloat(500F),
        )
        tests.add(test)
        return test
    }
}