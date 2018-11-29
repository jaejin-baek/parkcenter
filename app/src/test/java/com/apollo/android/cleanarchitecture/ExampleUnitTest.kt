package com.apollo.android.cleanarchitecture

import com.apollo.android.cleanarchitecture.presentation.second.Util
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Mock
    lateinit var mockUtil: Util

    @Test
    fun util() {
        whenever(mockUtil.addItem(1, 4)).thenReturn(5)

        //assertThat(mockUtil.addItem(1,8), `is`(5))

        mockUtil.addItem(1,4)


        verify(mockUtil).addItem(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())
    }

    @Test
    fun util2() {
        // when

        // then

        //verify(mock).addItem(1, 3)

        `when`(mockUtil.addItem(1,3)).thenReturn(8)
        assertThat(mockUtil.addItem(1,3), `is`(8))
    }
}
