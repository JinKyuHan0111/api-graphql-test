package com.daelim.springtest.main.resolver

import com.daelim.springtest.main.api.model.dto.MonsterDto
import com.daelim.springtest.main.api.model.dto.TestDto
import graphql.kickstart.tools.GraphQLMutationResolver
import graphql.kickstart.tools.GraphQLQueryResolver
import net.datafaker.Faker
import org.springframework.stereotype.Component
import java.util.*

@Component
class MonsterResolve: GraphQLQueryResolver, GraphQLMutationResolver {

    private val moster = mutableListOf<MonsterDto>()

    val faker = Faker(Locale.KOREA)

    fun findAllMoster(): List<MonsterDto> {
        return moster
    }

    fun findMosterByName(name: String): MonsterDto? {
        return moster.find { it.Mname == name }
    }

    fun createMoster(ChaName: String): MonsterDto {
        val mon = MonsterDto(
            Mname = ChaName,
            Mhp = Random().nextInt(1000).toFloat(),
            Mspeed = Random().nextInt(10).toFloat(),
            Matk = Random().nextInt(200).toFloat(),
            Mint = Random().nextInt(10).toFloat(),
        )
        moster.add(mon)
        return mon
    }
}