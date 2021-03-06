package com.small.main.util

import com.small.main.data.local.entity.MatchEntity
import com.small.main.data.remote.response.MatchResponse
import com.small.main.data.local.entity.TeamEntity
import com.small.main.data.remote.response.TeamResponse

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

        fun teamResponseToEntity(teamResponse: TeamResponse): TeamEntity {
            return TeamEntity(
                    teamResponse.idTeam,
                    teamResponse.intStadiumCapacity,
                    teamResponse.strTeamShort,
                    teamResponse.strSport,
                    teamResponse.strDescriptionCN,
                    teamResponse.strTeamJersey,
                    teamResponse.strTeamFanart2,
                    teamResponse.strTeamFanart3,
                    teamResponse.strTeamFanart4,
                    teamResponse.strStadiumDescription,
                    teamResponse.strTeamFanart1,
                    teamResponse.intLoved,
                    teamResponse.idLeague,
                    teamResponse.idSoccerXML,
                    teamResponse.strTeamLogo,
                    teamResponse.strDescriptionSE,
                    teamResponse.strDescriptionJP,
                    teamResponse.strDescriptionFR,
                    teamResponse.strStadiumLocation,
                    teamResponse.strDescriptionNL,
                    teamResponse.strCountry,
                    teamResponse.strRSS,
                    teamResponse.strDescriptionRU,
                    teamResponse.strTeamBanner,
                    teamResponse.strDescriptionNO,
                    teamResponse.strStadiumThumb,
                    teamResponse.strDescriptionES,
                    teamResponse.intFormedYear,
                    teamResponse.strInstagram,
                    teamResponse.strDescriptionIT,
                    teamResponse.strDescriptionEN,
                    teamResponse.strWebsite,
                    teamResponse.strYoutube,
                    teamResponse.strDescriptionIL,
                    teamResponse.strLocked,
                    teamResponse.strAlternate,
                    teamResponse.strTeam,
                    teamResponse.strTwitter,
                    teamResponse.strDescriptionHU,
                    teamResponse.strGender,
                    teamResponse.strDivision,
                    teamResponse.strStadium,
                    teamResponse.strFacebook,
                    teamResponse.strTeamBadge,
                    teamResponse.strDescriptionPT,
                    teamResponse.strDescriptionDE,
                    teamResponse.strLeague,
                    teamResponse.strManager,
                    teamResponse.strKeywords,
                    teamResponse.strDescriptionPL
            )
        }

        fun teamEntityToResponse(teamEntity: TeamEntity): TeamResponse {
            return TeamResponse(
                    teamEntity.idTeam,
                    teamEntity.intStadiumCapacity,
                    teamEntity.strTeamShort,
                    teamEntity.strSport,
                    teamEntity.strDescriptionCN,
                    teamEntity.strTeamJersey,
                    teamEntity.strTeamFanart2,
                    teamEntity.strTeamFanart3,
                    teamEntity.strTeamFanart4,
                    teamEntity.strStadiumDescription,
                    teamEntity.strTeamFanart1,
                    teamEntity.intLoved,
                    teamEntity.idLeague,
                    teamEntity.idSoccerXML,
                    teamEntity.strTeamLogo,
                    teamEntity.strDescriptionSE,
                    teamEntity.strDescriptionJP,
                    teamEntity.strDescriptionFR,
                    teamEntity.strStadiumLocation,
                    teamEntity.strDescriptionNL,
                    teamEntity.strCountry,
                    teamEntity.strRSS,
                    teamEntity.strDescriptionRU,
                    teamEntity.strTeamBanner,
                    teamEntity.strDescriptionNO,
                    teamEntity.strStadiumThumb,
                    teamEntity.strDescriptionES,
                    teamEntity.intFormedYear,
                    teamEntity.strInstagram,
                    teamEntity.strDescriptionIT,
                    teamEntity.strDescriptionEN,
                    teamEntity.strWebsite,
                    teamEntity.strYoutube,
                    teamEntity.strDescriptionIL,
                    teamEntity.strLocked,
                    teamEntity.strAlternate,
                    teamEntity.strTeam,
                    teamEntity.strTwitter,
                    teamEntity.strDescriptionHU,
                    teamEntity.strGender,
                    teamEntity.strDivision,
                    teamEntity.strStadium,
                    teamEntity.strFacebook,
                    teamEntity.strTeamBadge,
                    teamEntity.strDescriptionPT,
                    teamEntity.strDescriptionDE,
                    teamEntity.strLeague,
                    teamEntity.strManager,
                    teamEntity.strKeywords,
                    teamEntity.strDescriptionPL
            )
        }
    }
}