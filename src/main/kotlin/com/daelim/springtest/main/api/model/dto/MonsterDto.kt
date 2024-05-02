package com.daelim.springtest.main.api.model.dto

data class  MonsterDto (
    val Mname: String,
    val Mhp: Float,
    val Mspeed: Float,
    val Matk: Float
)
data class MonsterDtoRequest(
    val name : String,
    val job : String
)
