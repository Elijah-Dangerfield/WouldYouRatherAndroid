package com.dangerfield.wouldyourather.repository

import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    /*
    Sep. of concerns
    This file ONLY:
    exposes live data obtained from repository
    holds lifecycle spanning state

    Rules: if youre not observing it, dont make it live data
     */

    /**
     * these pack names will be used to decide which ranges to pull from
     */
    var packs = listOf<String>()


}