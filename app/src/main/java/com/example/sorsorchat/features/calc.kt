package com.example.sorsorchat.features

class calc {

    fun getPositiveNumber(x:Int) : Boolean{
        return x > 0
    }

    /**
     * @return concatenation of the argument with itself e.g. "hi" -> "hihi"
     */
    fun duplicate(string: String): String {
        // the bug is triplication instead of duplication
        return string + string + string
    }




}