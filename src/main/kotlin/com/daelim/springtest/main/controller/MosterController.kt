package com.daelim.springtest.main.controller

import com.daelim.springtest.main.api.model.dto.MonsterDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/monsters")
class MonsterController {
    private val monsters = mutableListOf<MonsterDto>()

    @PostMapping("/random/{monsterName}")
    fun postMonster(
        @PathVariable monsterName: String
    ): ResponseEntity<MonsterDto> {
        val monster = MonsterDto(
            Mname = monsterName,
            Mhp = Random().nextFloat() * 100F,
            Mspeed = Random().nextFloat() * 200F,
            Matk = Random().nextFloat() * 500F
        )
        monsters.add(monster)
        return ResponseEntity.ok().body(monster)
    }

    @GetMapping("/random")
    fun getAllMonsters(): ResponseEntity<List<MonsterDto>> {
        return ResponseEntity.ok().body(monsters)
    }

    @GetMapping("/random/{monsterName}")
    fun getMonsterByName(
        @PathVariable monsterName: String
    ): ResponseEntity<MonsterDto> {
        val monster = monsters.firstOrNull { it.Mname == monsterName }
        return monster?.let {
            ResponseEntity.ok().body(it)
        } ?: ResponseEntity.notFound().build()
    }
    //
}
