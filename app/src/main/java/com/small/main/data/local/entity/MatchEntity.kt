package com.small.main.data.local.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "match_entity")
data class MatchEntity(
        @ColumnInfo(name = "id_event")
        var idEvent: Int,
        var idSoccerXML: Int,
        var strEvent: String?,
        var strFilename: String?,
        var strSport: String?,
        var idLeague: Int,
        var strLeague: String?,
        var strSeason: String?,
        var strDescriptionEN: String?,
        var strHomeTeam: String?,
        var strAwayTeam: String?,
        var intHomeScore: Int?,
        var intRound: Int?,
        var intAwayScore: Int?,
        var intSpectators: Int?,
        var strHomeGoalDetails: String?,
        var strHomeRedCards: String?,
        var strHomeYellowCards: String?,
        var strHomeLineupGoalkeeper: String?,
        var strHomeLineupDefense: String?,
        var strHomeLineupMidfield: String?,
        var strHomeLineupForward: String?,
        var strHomeLineupSubstitutes: String?,
        var strHomeFormation: String?,
        var strAwayRedCards: String?,
        var strAwayYellowCards: String?,
        var strAwayGoalDetails: String?,
        var strAwayLineupGoalkeeper: String?,
        var strAwayLineupDefense: String?,
        var strAwayLineupMidfield: String?,
        var strAwayLineupForward: String?,
        var strAwayLineupSubstitutes: String?,
        var strAwayFormation: String?,
        var intHomeShots: Int?,
        var intAwayShots: Int?,
        var dateEvent: String?,
        var strDate: String?,
        var strTime: String?,
        var strTVStation: String?,
        var idHomeTeam: Int,
        var idAwayTeam: Int,
        var strResult: String?,
        var strCircuit: String?,
        var strCountry: String?,
        var strCity: String?,
        var strPoster: String?,
        var strFanart: String?,
        var strThumb: String?,
        var strBanner: String?,
        var strMap: String?,
        var strLocked: String?
) : Serializable {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}