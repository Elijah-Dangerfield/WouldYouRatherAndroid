package com.dangerfield.wouldyourather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dangerfield.wouldyourather.model.Question
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.ArrayList

class GameViewModel : ViewModel() {

    var packs = listOf<String>()
    var pool = Stack<String>()
    private val currentQuestion: MutableLiveData<Question> = MutableLiveData()
    private val db = FirebaseFirestore.getInstance()


    /**
     * exposes live data to be observed
     */
    fun getQuestion() = currentQuestion

    fun resetGame() {
        packs = listOf()
        pool = Stack()
    }

    fun startGame() = getQuestionIDs {
            loadNextQuestion()
        }

    fun submitVote(option: String) {
        val questionID = currentQuestion.value?.id ?: return
        db.collection("Packs").document(questionID).update(option,FieldValue.increment(1))
    }

    /**
     * gets next question with ID in pool from firebase
     * then updates livedata
     */
    fun loadNextQuestion(whenEmpty: (()-> Unit) = {}) {
        if(pool.isEmpty()){
            whenEmpty.invoke()
            return
        }
        val questionID = pool.pop()
        db.collection("Test").document(questionID).get().addOnSuccessListener {questionDoc->
            if(questionDoc!= null && questionDoc.exists()){
                val question = Question(questionID,questionDoc["1"] as Double,
                    questionDoc["2"] as Double,
                    questionDoc["questions"] as ArrayList<String>)
                currentQuestion.value = question
            }else{
                //TODO send something to crashlytics
            }
        }
    }

    /**
     * gets the id for all questions in selected packs and stores in array
     */
    fun getQuestionIDs(completion: (()-> Unit)) {
        var completed = 0
        packs.forEach {pack ->
            db.collection("Packs").document(pack.toLowerCase()).get().addOnSuccessListener {
                val range = it.data?.get("range") as ArrayList<Long>
                for(i in range[0]..range[1]) pool.add(i.toString())
                completed+=1

                if(completed == packs.size) {
                    pool.shuffle()
                    completion.invoke()
                }
            }
        }
    }

}