package com.small.main.util

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class CommonUtils {
    companion object {

        @SuppressLint("SimpleDateFormat")
        fun getStringLocalDate(startDate: Date): String? {
            val writeFormat = SimpleDateFormat("EEE, dd MMM yyyy HH:mm", Locale("in", "ID"))
            var finalDate: String? = null
            try {
                finalDate = writeFormat.format(startDate)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return finalDate
        }

    }
}