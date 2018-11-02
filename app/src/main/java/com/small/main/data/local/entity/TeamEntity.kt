package com.small.main.data.local.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "team_entity")
class TeamEntity(
        @ColumnInfo(name = "id_team")
        val idTeam: Int,
        val intStadiumCapacity: String?,
        val strTeamShort: String?,
        val strSport: String?,
        val strDescriptionCN: String?,
        val strTeamJersey: String?,
        val strTeamFanart2: String?,
        val strTeamFanart3: String?,
        val strTeamFanart4: String?,
        val strStadiumDescription: String?,
        val strTeamFanart1: String?,
        val intLoved: String?,
        val idLeague: Int,
        val idSoccerXML: Int,
        val strTeamLogo: String?,
        val strDescriptionSE: String?,
        val strDescriptionJP: String?,
        val strDescriptionFR: String?,
        val strStadiumLocation: String?,
        val strDescriptionNL: String?,
        val strCountry: String?,
        val strRSS: String?,
        val strDescriptionRU: String?,
        val strTeamBanner: String?,
        val strDescriptionNO: String?,
        val strStadiumThumb: String?,
        val strDescriptionES: String?,
        val intFormedYear: String?,
        val strInstagram: String?,
        val strDescriptionIT: String?,
        val strDescriptionEN: String?,
        val strWebsite: String?,
        val strYoutube: String?,
        val strDescriptionIL: String?,
        val strLocked: String?,
        val strAlternate: String?,
        val strTeam: String?,
        val strTwitter: String?,
        val strDescriptionHU: String?,
        val strGender: String?,
        val strDivision: String?,
        val strStadium: String?,
        val strFacebook: String?,
        val strTeamBadge: String?,
        val strDescriptionPT: String?,
        val strDescriptionDE: String?,
        val strLeague: String?,
        val strManager: String?,
        val strKeywords: String?,
        val strDescriptionPL: String?
) : Serializable {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}