package com.daelim.springtest.main.api.model.dto

data class TestDto(
    val id : String,
    val name : String,
    val hp : Float,
    val speed : Float,
    val atk : Float,
    val job : String,
    var score : Float,
)
//sss
data class TestDtoRequest(
    val name : String,
    val job : String
)
