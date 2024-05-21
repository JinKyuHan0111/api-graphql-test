package com.daelim.springtest.medium

import kotlin.random.Random
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class UserRankingService {

    fun calculateTotalScore(playTime: Float, oldScore: Float): Float {
        val newScore = calculateScore(playTime)
        return newScore + oldScore
    }

    fun calculateScore(playTime: Float): Float {
        val maxScore = 2000
        val maxPlayTime = 15
        val adjustedPlayTime = playTime.coerceAtMost(maxPlayTime.toFloat())
        return maxScore * (1 - adjustedPlayTime / maxPlayTime.toFloat())
    }

    fun rankUsersWithScore(scores: List<Pair<Float, Float>>): List<Pair<Float, Float>> {
        return scores.sortedByDescending { it.first }
    }

    @Test
    @DisplayName("점수를 올바르게 계산하고 랭킹화하는지 확인")
    fun `calculateTotalScore calculates and ranks correctly`() {
        val testScores = listOf(
            Pair(5.0f, 1000.0f),
            Pair(10.0f, 1500.0f),
            Pair(2.0f, 800.0f)
        )

        val calculatedScores = testScores.map { (playTime, oldScore) ->
            calculateTotalScore(playTime, oldScore)
        }

        val rankedScores = rankUsersWithScore(testScores)

        for ((index, calculatedScore) in calculatedScores.withIndex()) {
            assertEquals(rankedScores[index].first, calculatedScore)
        }
    }
}