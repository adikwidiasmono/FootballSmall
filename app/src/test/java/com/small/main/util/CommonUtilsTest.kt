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

    @Test
    fun safePresentStringTest() {
        val input1 = "INPUT"
        val input2 = ""
        val input3 = "Null"
        val input4 = null

        assertEquals("INPUT", CommonUtils.safePresentString(input1))
        assertEquals("-", CommonUtils.safePresentString(input2))
        assertEquals("-", CommonUtils.safePresentString(input3))
        assertEquals("-", CommonUtils.safePresentString(input4))
    }

}