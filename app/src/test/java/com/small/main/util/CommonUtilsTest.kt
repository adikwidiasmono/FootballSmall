package com.small.main.util

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat

class CommonUtilsTest {

    @Test
    fun testGetStringLocalDate() {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm")
        val date = dateFormat.parse("26/10/2018 09:01")

        assertEquals("Jum, 26 Okt 2018 09:01", CommonUtils.getStringLocalDate(date))
    }

}