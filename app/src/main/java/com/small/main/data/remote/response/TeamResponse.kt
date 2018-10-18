package com.small.main.data.remote.response

import android.os.Parcel
import android.os.Parcelable

class FootballTeamItem(
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
        val idTeam: Int,
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
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as Int,
            parcel.readValue(Int::class.java.classLoader) as Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(intStadiumCapacity)
        parcel.writeString(strTeamShort)
        parcel.writeString(strSport)
        parcel.writeString(strDescriptionCN)
        parcel.writeString(strTeamJersey)
        parcel.writeString(strTeamFanart2)
        parcel.writeString(strTeamFanart3)
        parcel.writeString(strTeamFanart4)
        parcel.writeString(strStadiumDescription)
        parcel.writeString(strTeamFanart1)
        parcel.writeString(intLoved)
        parcel.writeInt(idLeague)
        parcel.writeInt(idSoccerXML)
        parcel.writeString(strTeamLogo)
        parcel.writeString(strDescriptionSE)
        parcel.writeString(strDescriptionJP)
        parcel.writeString(strDescriptionFR)
        parcel.writeString(strStadiumLocation)
        parcel.writeString(strDescriptionNL)
        parcel.writeString(strCountry)
        parcel.writeString(strRSS)
        parcel.writeString(strDescriptionRU)
        parcel.writeString(strTeamBanner)
        parcel.writeString(strDescriptionNO)
        parcel.writeString(strStadiumThumb)
        parcel.writeString(strDescriptionES)
        parcel.writeString(intFormedYear)
        parcel.writeString(strInstagram)
        parcel.writeString(strDescriptionIT)
        parcel.writeInt(idTeam)
        parcel.writeString(strDescriptionEN)
        parcel.writeString(strWebsite)
        parcel.writeString(strYoutube)
        parcel.writeString(strDescriptionIL)
        parcel.writeString(strLocked)
        parcel.writeString(strAlternate)
        parcel.writeString(strTeam)
        parcel.writeString(strTwitter)
        parcel.writeString(strDescriptionHU)
        parcel.writeString(strGender)
        parcel.writeString(strDivision)
        parcel.writeString(strStadium)
        parcel.writeString(strFacebook)
        parcel.writeString(strTeamBadge)
        parcel.writeString(strDescriptionPT)
        parcel.writeString(strDescriptionDE)
        parcel.writeString(strLeague)
        parcel.writeString(strManager)
        parcel.writeString(strKeywords)
        parcel.writeString(strDescriptionPL)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FootballTeamItem> {
        override fun createFromParcel(parcel: Parcel): FootballTeamItem {
            return FootballTeamItem(parcel)
        }

        override fun newArray(size: Int): Array<FootballTeamItem?> {
            return arrayOfNulls(size)
        }
    }
}