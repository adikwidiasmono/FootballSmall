package com.small.main.util

import android.annotation.SuppressLint
import com.small.main.data.local.entity.MatchEntity
import com.small.main.data.remote.response.MatchResponse
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ParseUtils {
    companion object {
        fun matchResponseToEntity(matchResponse: MatchResponse): MatchEntity {
            return MatchEntity(
                    matchResponse.idEvent,
                    matchResponse.idSoccerXML,
                    matchResponse.strEvent,
                    matchResponse.strFilename,
                    matchResponse.strSport,
                    matchResponse.idLeague,
                    matchResponse.strLeague,
                    matchResponse.strSeason,
                    matchResponse.strDescriptionEN,
                    matchResponse.strHomeTeam,
                    matchResponse.strAwayTeam,
                    matchResponse.intHomeScore,
                    matchResponse.intRound,
                    matchResponse.intAwayScore,
                    matchResponse.intSpectators,
                    matchResponse.strHomeGoalDetails,
                    matchResponse.strHomeRedCards,
                    matchResponse.strHomeYellowCards,
                    matchResponse.strHomeLineupGoalkeeper,
                    matchResponse.strHomeLineupDefense,
                    matchResponse.strHomeLineupMidfield,
                    matchResponse.strHomeLineupForward,
                    matchResponse.strHomeLineupSubstitutes,
                    matchResponse.strHomeFormation,
                    matchResponse.strAwayRedCards,
                    matchResponse.strAwayYellowCards,
                    matchResponse.strAwayGoalDetails,
                    matchResponse.strAwayLineupGoalkeeper,
                    matchResponse.strAwayLineupDefense,
                    matchResponse.strAwayLineupMidfield,
                    matchResponse.strAwayLineupForward,
                    matchResponse.strAwayLineupSubstitutes,
                    matchResponse.strAwayFormation,
                    matchResponse.intHomeShots,
                    matchResponse.intAwayShots,
                    matchResponse.dateEvent,
                    matchResponse.strDate,
                    matchResponse.strTime,
                    matchResponse.strTVStation,
                    matchResponse.idHomeTeam,
                    matchResponse.idAwayTeam,
                    matchResponse.strResult,
                    matchResponse.strCircuit,
                    matchResponse.strCountry,
                    matchResponse.strCity,
                    matchResponse.strPoster,
                    matchResponse.strFanart,
                    matchResponse.strThumb,
                    matchResponse.strBanner,
                    matchResponse.strMap,
                    matchResponse.strLocked
            )
        }

        fun matchEntityToResponse(matchEntity: MatchEntity): MatchResponse {
            return MatchResponse(
                    matchEntity.idEvent,
                    matchEntity.idSoccerXML,
                    matchEntity.strEvent,
                    matchEntity.strFilename,
                    matchEntity.strSport,
                    matchEntity.idLeague,
                    matchEntity.strLeague,
                    matchEntity.strSeason,
                    matchEntity.strDescriptionEN,
                    matchEntity.strHomeTeam,
                    matchEntity.strAwayTeam,
                    matchEntity.intHomeScore,
                    matchEntity.intRound,
                    matchEntity.intAwayScore,
                    matchEntity.intSpectators,
                    matchEntity.strHomeGoalDetails,
                    matchEntity.strHomeRedCards,
                    matchEntity.strHomeYellowCards,
                    matchEntity.strHomeLineupGoalkeeper,
                    matchEntity.strHomeLineupDefense,
                    matchEntity.strHomeLineupMidfield,
                    matchEntity.strHomeLineupForward,
                    matchEntity.strHomeLineupSubstitutes,
                    matchEntity.strHomeFormation,
                    matchEntity.strAwayRedCards,
                    matchEntity.strAwayYellowCards,
                    matchEntity.strAwayGoalDetails,
                    matchEntity.strAwayLineupGoalkeeper,
                    matchEntity.strAwayLineupDefense,
                    matchEntity.strAwayLineupMidfield,
                    matchEntity.strAwayLineupForward,
                    matchEntity.strAwayLineupSubstitutes,
                    matchEntity.strAwayFormation,
                    matchEntity.intHomeShots,
                    matchEntity.intAwayShots,
                    matchEntity.dateEvent,
                    matchEntity.strDate,
                    matchEntity.strTime,
                    matchEntity.strTVStation,
                    matchEntity.idHomeTeam,
                    matchEntity.idAwayTeam,
                    matchEntity.strResult,
                    matchEntity.strCircuit,
                    matchEntity.strCountry,
                    matchEntity.strCity,
                    matchEntity.strPoster,
                    matchEntity.strFanart,
                    matchEntity.strThumb,
                    matchEntity.strBanner,
                    matchEntity.strMap,
                    matchEntity.strLocked
            )
        }
    }
}