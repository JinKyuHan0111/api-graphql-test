package com.daelim.springtest.main.api.model.dto

data class  MonsterDto (
    val Mname: String,
    val Mhp: Float,
    val Mspeed: Float,
    val Matk: Float,
    val Mint: Float
)
data class MonsterDtoRequest(
    val Mname : String,
)
