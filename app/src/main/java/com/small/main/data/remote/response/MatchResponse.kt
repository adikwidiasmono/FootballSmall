package com.small.main.data.remote.response

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class MatchResponse(
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
        var dateEvent: Date?,
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
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readValue(Int::class.java.classLoader) as Int,
            parcel.readValue(Int::class.java.classLoader) as Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
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
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Date::class.java.classLoader) as? Date,
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
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(idEvent)
        parcel.writeValue(idSoccerXML)
        parcel.writeString(strEvent)
        parcel.writeString(strFilename)
        parcel.writeString(strSport)
        parcel.writeValue(idLeague)
        parcel.writeString(strLeague)
        parcel.writeString(strSeason)
        parcel.writeString(strDescriptionEN)
        parcel.writeString(strHomeTeam)
        parcel.writeString(strAwayTeam)
        parcel.writeValue(intHomeScore)
        parcel.writeValue(intRound)
        parcel.writeValue(intAwayScore)
        parcel.writeValue(intSpectators)
        parcel.writeString(strHomeGoalDetails)
        parcel.writeString(strHomeRedCards)
        parcel.writeString(strHomeYellowCards)
        parcel.writeString(strHomeLineupGoalkeeper)
        parcel.writeString(strHomeLineupDefense)
        parcel.writeString(strHomeLineupMidfield)
        parcel.writeString(strHomeLineupForward)
        parcel.writeString(strHomeLineupSubstitutes)
        parcel.writeString(strHomeFormation)
        parcel.writeString(strAwayRedCards)
        parcel.writeString(strAwayYellowCards)
        parcel.writeString(strAwayGoalDetails)
        parcel.writeString(strAwayLineupGoalkeeper)
        parcel.writeString(strAwayLineupDefense)
        parcel.writeString(strAwayLineupMidfield)
        parcel.writeString(strAwayLineupForward)
        parcel.writeString(strAwayLineupSubstitutes)
        parcel.writeString(strAwayFormation)
        parcel.writeValue(intHomeShots)
        parcel.writeValue(intAwayShots)
        parcel.writeValue(dateEvent)
        parcel.writeString(strDate)
        parcel.writeString(strTime)
        parcel.writeString(strTVStation)
        parcel.writeValue(idHomeTeam)
        parcel.writeValue(idAwayTeam)
        parcel.writeString(strResult)
        parcel.writeString(strCircuit)
        parcel.writeString(strCountry)
        parcel.writeString(strCity)
        parcel.writeString(strPoster)
        parcel.writeString(strFanart)
        parcel.writeString(strThumb)
        parcel.writeString(strBanner)
        parcel.writeString(strMap)
        parcel.writeString(strLocked)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MatchResponse> {
        override fun createFromParcel(parcel: Parcel): MatchResponse {
            return MatchResponse(parcel)
        }

        override fun newArray(size: Int): Array<MatchResponse?> {
            return arrayOfNulls(size)
        }
    }
}