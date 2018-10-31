package com.small.main.data.remote.response

import android.os.Parcel
import android.os.Parcelable

data class CountrysItem(
        val strDescriptionES: String?,
        val dateFirstEvent: String?,
        val intFormedYear: Int?,
        val strBanner: String?,
        val strSport: String?,
        val strDescriptionIT: String?,
        val strDescriptionCN: String?,
        val strDescriptionEN: String?,
        val strWebsite: String?,
        val strYoutube: String?,
        val idCup: Int?,
        val strLocked: String?,
        val idLeague: Int?,
        val idSoccerXML: Int?,
        val strTrophy: String?,
        val strBadge: String?,
        val strTwitter: String?,
        val strDescriptionHU: String?,
        val strGender: String?,
        val strLeagueAlternate: String?,
        val strDescriptionSE: String?,
        val strNaming: String?,
        val strDescriptionJP: String?,
        val strFanart1: String?,
        val strDescriptionFR: String?,
        val strFanart2: String?,
        val strFanart3: String?,
        val strFacebook: String?,
        val strFanart4: String?,
        val strCountry: String?,
        val strDescriptionNL: String?,
        val strRSS: String?,
        val strDescriptionRU: String?,
        val strDescriptionPT: String?,
        val strLogo: String?,
        val strDescriptionDE: String?,
        val strDescriptionNO: String?,
        val strLeague: String?,
        val strPoster: String?,
        val strDescriptionPL: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
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
        parcel.writeString(strDescriptionES)
        parcel.writeString(dateFirstEvent)
        parcel.writeValue(intFormedYear)
        parcel.writeString(strBanner)
        parcel.writeString(strSport)
        parcel.writeString(strDescriptionIT)
        parcel.writeString(strDescriptionCN)
        parcel.writeString(strDescriptionEN)
        parcel.writeString(strWebsite)
        parcel.writeString(strYoutube)
        parcel.writeValue(idCup)
        parcel.writeString(strLocked)
        parcel.writeValue(idLeague)
        parcel.writeValue(idSoccerXML)
        parcel.writeString(strTrophy)
        parcel.writeString(strBadge)
        parcel.writeString(strTwitter)
        parcel.writeString(strDescriptionHU)
        parcel.writeString(strGender)
        parcel.writeString(strLeagueAlternate)
        parcel.writeString(strDescriptionSE)
        parcel.writeString(strNaming)
        parcel.writeString(strDescriptionJP)
        parcel.writeString(strFanart1)
        parcel.writeString(strDescriptionFR)
        parcel.writeString(strFanart2)
        parcel.writeString(strFanart3)
        parcel.writeString(strFacebook)
        parcel.writeString(strFanart4)
        parcel.writeString(strCountry)
        parcel.writeString(strDescriptionNL)
        parcel.writeString(strRSS)
        parcel.writeString(strDescriptionRU)
        parcel.writeString(strDescriptionPT)
        parcel.writeString(strLogo)
        parcel.writeString(strDescriptionDE)
        parcel.writeString(strDescriptionNO)
        parcel.writeString(strLeague)
        parcel.writeString(strPoster)
        parcel.writeString(strDescriptionPL)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CountrysItem> {
        override fun createFromParcel(parcel: Parcel): CountrysItem {
            return CountrysItem(parcel)
        }

        override fun newArray(size: Int): Array<CountrysItem?> {
            return arrayOfNulls(size)
        }
    }
}
