package jetbrains.kotlin.course.words.generator.results

import jetbrains.kotlin.course.words.generator.team.Team
import jetbrains.kotlin.course.words.generator.team.TeamService.Companion.teamsStorage
import org.springframework.stereotype.Service


typealias  GameResult = List<Team>

@Service
class GameResultsService {

    companion object {
        val gameHistory: MutableList<GameResult> = mutableListOf()
    }

    fun saveGameResults(result: GameResult): Unit {
        require(result.isNotEmpty())
        require(result.all { it.id in teamsStorage })
        gameHistory.add(result)
    }

    fun getAllGameResults(): List<GameResult> =
        gameHistory.reversed()
}
