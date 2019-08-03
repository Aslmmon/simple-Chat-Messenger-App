package com.example.sorsorchat.features

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class calcTest {

    var cal: calc? = null
    @Before
    fun setup() {
        cal = calc()
    }

    @Test
    fun test1(){
        var result = cal?.getPositiveNumber(-1)
        if (result != null) {
            assertEquals(false,result)
        }
    }


    @Test
    fun test2(){
        var result = cal?.getPositiveNumber(0)
        assertEquals(false,result)
    }

    @Test
    fun test3(){
        var result = cal?.getPositiveNumber(5)
        assertEquals(true,result)
    }

    @Test
    fun duplicate_ReturnDoubleOfString() {
        val result = cal?.duplicate("comBack")
        assertEquals("comBackcomBackcomBack",result)
    }

    @Test
    fun duplicate_ReturnDoubleOfString_any() {
        val result = cal?.duplicate("sheka")
        assertEquals("shekashekasheka",result)
    }

    @Test
    fun duplicate_ReturnDoubleOfString_False() {
        val result = cal?.duplicate("sheka")
        assertEquals("shekasheka",result)
    }



}