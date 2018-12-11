package com.apollo.android.cleanarchitecture

import com.apollo.android.cleanarchitecture.mockito.DependentClass
import com.apollo.android.cleanarchitecture.mockito.InjectedClass
import com.apollo.android.cleanarchitecture.mockito.SimpleClass
import com.apollo.android.cleanarchitecture.presentation.second.Util
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {

    @Mock
    lateinit var injectedClass: InjectedClass

    @Mock
    lateinit var dependentClass: DependentClass

    @Test
    fun simpleClass_usingFunction_test() {
        val simpleClass = SimpleClass(injectedClass)

        simpleClass.usingFunction()

        verify(injectedClass).usingDependentObject()
    }

    @Test
    fun simpleClass_settingFunction_test() {
        val simpleClass = SimpleClass(injectedClass)

//        whenever(dependentClass.myCnt).thenReturn(11)
        simpleClass.settingFunction2(dependentClass)

//        whenever(dependentClass.myCnt).thenReturn(21)
        simpleClass.settingFunction2(dependentClass)

//        whenever(dependentClass.myCnt).thenReturn(31)
        simpleClass.settingFunction2(dependentClass)

        whenever(dependentClass.myCnt).thenReturn(31)

        argumentCaptor<DependentClass> {
            verify(injectedClass, times(3)).settingDependentObject(capture())

            assertEquals(3, allValues.size)
            assertEquals(31, firstValue.myCnt)
            assertEquals(31, secondValue.myCnt)
            assertEquals(31, lastValue.myCnt)
        }

        //verify(injectedClass).settingDependentObject(DependentClass().apply { myCnt = 255 })

//        argumentCaptor<DependentClass>().apply {
//            verify(injectedClass).settingDependentObject(capture())
//
//            assertEquals(255, firstValue.myCnt)
//        }
    }

    @Mock
    lateinit var mockUtil: Util

    @Test
    fun util() {
        whenever(mockUtil.addItem(1, 4)).thenReturn(5)

        mockUtil.addItem(1, 4)

        verify(mockUtil).addItem(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())
    }

    @Test
    fun util2() {
        whenever(mockUtil.addItem(1,3)).thenReturn(8)
        assertThat(mockUtil.addItem(1, 3), `is`(8))
    }
}
