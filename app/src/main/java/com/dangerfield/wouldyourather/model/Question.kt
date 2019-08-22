package com.dangerfield.wouldyourather.model

data class Question(var option1Votes: Long, var option2Votes: Long, val questions: ArrayList<String>) {

    constructor(): this(0.toLong(),0.toLong(),ArrayList<String>())
}