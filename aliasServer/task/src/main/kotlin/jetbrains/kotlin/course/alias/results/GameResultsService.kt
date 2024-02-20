package jetbrains.kotlin.course.alias.results

import jetbrains.kotlin.course.alias.team.TeamService
import jetbrains.kotlin.course.alias.team.Team
import org.springframework.stereotype.Service

typealias GameResult = List<Team>

@Service
class GameResultsService {

    companion object {
        private val gameHistory = mutableListOf<GameResult>()
    }

    fun saveGameResults(result: GameResult) {
        require(result.isNotEmpty()) { "Game result should not be empty" }
        require(result.all { it.id in TeamService.teamsStorage }) { "Unknown team in game result" }
        gameHistory.add(result)
    }

    fun getAllGameResults(): List<GameResult> = gameHistory.reversed()
}
