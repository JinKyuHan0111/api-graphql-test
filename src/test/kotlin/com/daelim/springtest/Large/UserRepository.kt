import com.daelim.springtest.medium.UserRankingService

data class User(val id: String, val name: String, val score: Float)

class UserRepository {
    private val users = mutableListOf<User>()

    // 유저 추가
    fun addUser(user: User) {
        users.add(user)
    }

    // 아이디로 유저 찾기
    fun findUserById(userId: String): User? {
        return users.find { it.id == userId }
    }

    // 랭킹에 따라 유저 검색
    fun searchUsersByRanking(): List<User> {
        return users.sortedByDescending { it.score }
    }
}

class UserRankingManager(private val userRepository: UserRepository) {
    // 아이디로 랭킹과 점수 검색
    fun searchRankAndScoreById(userId: String): Pair<Int?, Float?> {
        val user = userRepository.findUserById(userId)
        val ranking = userRepository.searchUsersByRanking().indexOf(user) + 1
        val score = user?.score
        return Pair(ranking.takeIf { it != 0 }, score)
    }
}

fun main() {
    // 테스트용 사용자 데이터 생성
    val users = listOf(
        User("user1", "User One", 1500.0f),
        User("user2", "User Two", 2000.0f),
        User("user3", "User Three", 1000.0f)
    )

    // UserRepository 생성 및 사용자 추가
    val userRepository = UserRepository()
    users.forEach { userRepository.addUser(it) }

    // UserRankingManager 생성
    val userRankingManager = UserRankingManager(userRepository)

    // 유저 아이디로 랭킹과 점수 검색
    val userId = "user2"
    val (ranking, score) = userRankingManager.searchRankAndScoreById(userId)

    // 결과 출력
    if (ranking != null && score != null) {
        println("User ID: $userId")
        println("Ranking: $ranking")
        println("Score: $score")
    } else {
        println("User not found.")
    }
}
