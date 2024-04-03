package com.daelim.springtest.main.controller

import com.daelim.springtest.main.api.model.dto.TestDto
import com.daelim.springtest.main.api.model.dto.TestDtoRequest
import io.swagger.v3.oas.annotations.Parameter
import jakarta.validation.Valid
import net.datafaker.Faker
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class Controller {
    private val tests = mutableListOf<TestDto>()

    @PostMapping("/test")
    fun postTestDto(
        @RequestBody testDtoRequest: TestDtoRequest
    ): ResponseEntity<TestDto> {
        val faker = Faker(Locale.KOREA)
        val test = TestDto(
            name = testDtoRequest.name,
            job = testDtoRequest.job,
            hp = Random().nextFloat(100F),
            speed = Random().nextFloat(200F),
            atk = Random().nextFloat(500F),
        )
        tests.add(test)
        return ResponseEntity.ok().body(test)
    }
    @GetMapping("/test")
    fun getAllTestDto(
    ): ResponseEntity<List<TestDto>> {
        val response = tests
        return ResponseEntity.ok().body(response)
    }

    @GetMapping("/test/{id}")
    fun getTestDto(
        @PathVariable("id") userId: String
    ): ResponseEntity<TestDto> {
        val response = tests.firstOrNull{it.id == userId}
        return ResponseEntity.ok().body(response)
    }
}