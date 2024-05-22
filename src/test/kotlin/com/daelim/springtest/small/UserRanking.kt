package com.daelim.springtest.small

class UserRanking {
    companion object {
        fun calculateScore(playTime: Float): Float {
            val maxScore = 2000 // 최대 점수
            val maxPlayTime = 15
            val adjustedPlayTime = playTime.coerceAtMost(maxPlayTime.toFloat()) // 플레이 타임을 최대값인 1분으로 조정

            // 클리어 시간이 1분 안에일 경우 최대 점수 부여
            val score = maxScore * (1 - adjustedPlayTime / maxPlayTime)
            return score
        }
    }
}
