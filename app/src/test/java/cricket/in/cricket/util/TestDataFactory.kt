package cricket.`in`.cricket.util

import cricket.`in`.cricket.data.entity.Matches
import cricket.`in`.cricket.data.remote.CricketModel

/**
 * Created by vivek on 26/06/17.
 */
class TestDataFactory {

    companion object {

        fun matchList(teamOne: String, teamTwo: String): List<Matches> {
            return (1..10).map { createMatch(teamOne + it, teamTwo + it) }
        }

        fun createMatch(teamOne: String, teamTwo: String): Matches {
            return Matches(0, teamOne, teamTwo)
        }

        fun createMatchModel(): CricketModel {
            return CricketModel(matchList("ind", "eng"))
        }
    }
}