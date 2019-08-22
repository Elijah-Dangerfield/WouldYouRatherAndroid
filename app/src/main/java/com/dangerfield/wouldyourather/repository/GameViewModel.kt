package com.dangerfield.wouldyourather.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dangerfield.wouldyourather.Custom.log
import com.dangerfield.wouldyourather.model.Question
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.ArrayList

class GameViewModel : ViewModel() {

    var packs = listOf<String>()
    var pool = Stack<String>()
    private val currentQuestion: MutableLiveData<Question> = MutableLiveData()
    private val db = FirebaseFirestore.getInstance()


    fun getQuestion(): LiveData<Question>{
        return currentQuestion
    }

    fun loadNextQuestion(whenEmpty: (()-> Unit)) {
        if(pool.isEmpty()){
            whenEmpty.invoke()
            return
        }
        val questionID = pool.pop()
        db.collection("Test").document(questionID).get().addOnSuccessListener {questionDoc->
            if(questionDoc!= null && questionDoc.exists()){
                val question = Question(questionDoc["1"] as Double,
                    questionDoc["2"] as Double,
                    questionDoc["questions"] as ArrayList<String>)
                currentQuestion.value = question

            }else{
                ("ERROR: COULD GRAB QUESTION W/ ID: "+ questionID).log()
            }
        }
    }


    /**
     * gets the id for all questions in selected packs and stores in array
     */
    fun getRanges(completion: (()-> Unit)) {
        var completed = 0
        packs.forEach {pack ->
            db.collection("Packs").document(pack.toLowerCase()).get().addOnSuccessListener {
                val range = it.data?.get("range") as ArrayList<Long>
                range.toString().log()
                for(i in range[0]..range[1]) pool.add(i.toString())
                completed+=1
                if(completed ==packs.size) {
                    pool.shuffle()
                    completion.invoke()
                }
            }
        }
    }

    fun resetGame() {
        packs = listOf()
        pool = Stack()
    }


    fun submitVote(completion: () -> Unit) {
        completion.invoke()
    }
}