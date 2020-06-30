package com.sourabhkarkal.blogreaderdemo

import com.sourabhkarkal.blogreaderdemo.utils.Utils
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito.mockStatic
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import java.util.*

@RunWith(PowerMockRunner::class)
@PrepareForTest(Utils::class)
class UtilsTest {

    @Test
    fun testFormatValue() {

        assertEquals("10 ", Utils.formatValue(10.00))

        assertEquals("10K", Utils.formatValue(10000.00))

        assertEquals("1.1M", Utils.formatValue(1050000.00))

    }


    @Test
    fun testFormatDate() {
        val endOfMarch: Calendar = Calendar.getInstance()
        endOfMarch.set(2020, Calendar.JUNE, 30, 18, 31)
        mockStatic(Calendar::class.java)
        Mockito.`when`(Calendar.getInstance()).thenReturn(endOfMarch)

        assertEquals("75 Days", Utils.formatDate("2020-04-16T16:29:21.696Z"))

        assertEquals("2 Hrs 2 Min", Utils.formatDate("2020-06-30T16:29:00.696Z"))

        assertEquals("2 Min", Utils.formatDate("2020-06-30T18:29:21.696Z"))

    }



}