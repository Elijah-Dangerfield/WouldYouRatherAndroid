package com.dangerfield.wouldyourather.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dangerfield.wouldyourather.Custom.log
import com.google.firebase.firestore.FirebaseFirestore

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
    var pool = ArrayList<String>()
    private val db = FirebaseFirestore.getInstance()
    val currentQuestion: LiveData<ArrayList<String>> = MutableLiveData()


    fun getNextQuestion() {
        //update livedata current Question
    }


    fun getRanges(completion: (()-> Unit)) {
        var completed = 0
        packs.forEach {pack ->
            db.collection("Packs").document(pack.toLowerCase()).get().addOnSuccessListener {
                val range = it.data?.get("range") as ArrayList<Long>
                range.toString().log()
                for(i in range[0]..range[1]) pool.add(i.toString())
                completed+=1
                if(completed ==packs.size) completion.invoke()
            }
        }


    }

}