package com.dangerfield.wouldyourather.model

data class Question(var option1Votes: Double, var option2Votes: Double, val questions: ArrayList<String>) {

    constructor(): this(0.toDouble(),0.toDouble(),ArrayList<String>())
}