package com.small.main.util

import android.annotation.SuppressLint
import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class CommonUtils {
    companion object {

        @SuppressLint("SimpleDateFormat")
        fun getStringLocalDate(startDate: Date): String? {
            val writeFormat = SimpleDateFormat("EEE, dd MMM yyyy HH:mm", Locale("in", "ID"))
            var finalDate: String? = null
            try {
                finalDate = writeFormat.format(parseDateToLocalDate(startDate))
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return finalDate
        }

        fun safePresentString(input: String?): String {
            if (input == null)
                return "-"
            if (input.trim().length < 1)
                return "-"
            if (input.toUpperCase().equals("NULL"))
                return "-"

            return input
        }

        fun parseDateToLocalDate(date: Date): Date {
            val cal = Calendar.getInstance()
            cal.time = date
            cal.add(Calendar.HOUR_OF_DAY, getCurrentGMTHours()) // Adds local GTM time to date
            return cal.time
        }

        fun getCurrentGMTHours(): Int {
            val mCalendar = GregorianCalendar()
            val mTimeZone = mCalendar.timeZone
            val mGMTOffset = mTimeZone.rawOffset
            return TimeUnit.HOURS.convert(mGMTOffset.toLong(), TimeUnit.MILLISECONDS).toInt()
        }

    }
}