package com.dangerfield.wouldyourather.model

class Question(val id: String, var option1Votes: Double, var option2Votes: Double,
                    val questions: ArrayList<String>) {

    var pct_1 = ((option1Votes/(option1Votes + option2Votes)) * 100 ).toInt()
    var pct_2 = ((option2Votes/(option1Votes + option2Votes)) * 100).toInt()

    constructor(): this("",0.toDouble(),0.toDouble(),ArrayList<String>())


}