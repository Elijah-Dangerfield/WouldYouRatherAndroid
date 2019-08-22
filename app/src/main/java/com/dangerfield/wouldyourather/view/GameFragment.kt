package com.dangerfield.wouldyourather.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dangerfield.wouldyourather.R
import com.dangerfield.wouldyourather.custom.AlertFactory
import com.dangerfield.wouldyourather.custom.log
import com.dangerfield.wouldyourather.custom.select
import com.dangerfield.wouldyourather.custom.unselect
import com.dangerfield.wouldyourather.model.Question
import com.dangerfield.wouldyourather.viewmodel.GameViewModel
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.fragment_game.view.*
import kotlinx.android.synthetic.main.fragment_game.view.btn_option1
import kotlinx.android.synthetic.main.fragment_game.view.btn_option2

class GameFragment : Fragment() {
    private lateinit var viewModel: GameViewModel
    lateinit var options: List<Button>
    var pct1 = 0
    var pct2 = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_game, container, false)
        initializeViews(root)
        return root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(activity!!).get(GameViewModel::class.java)

        viewModel.getQuestionIDs { viewModel.pool.toString().log() }

        viewModel.getQuestion().observe(viewLifecycleOwner, Observer {
            updateOptions(it)
        })
    }

    private fun updateOptions(question: Question) {
        btn_option1.text = question.questions[0]
        btn_option2.text = question.questions[1]
        val total = question.option1Votes + question.option2Votes
        pct1 = (question.option1Votes / total * 100).toInt()
        pct2 = (question.option2Votes / total * 100).toInt()
    }

    private fun initializeViews(root: View) {
        root.run{
            options = listOf(btn_option1,btn_option2)

            options.forEach { option -> option.setOnClickListener { vote(option) }}

            btn_next.setOnClickListener { loadNextQuestion() }
        }
    }

    private fun vote(option: Button){
        val index = options.indexOf(option)
        //if the other guy has already been voted for, we cant vote again
        if(options.elementAt(options.size - index - 1).tag == 1) return

        option.select()
        viewModel.submitVote((index + 1 ).toString())

        if(index == 0) pct1 += 1
        else pct2 +=1

        tv_pct_1.text = "$pct1%"
        tv_pct_2.text = "$pct2%"

        tv_pct_1.visibility = View.VISIBLE
        tv_pct_2.visibility = View.VISIBLE
    }

    private fun loadNextQuestion() {
        options.forEach { it.text = ""; it.unselect() }
        tv_pct_1.visibility = View.INVISIBLE
        tv_pct_2.visibility = View.INVISIBLE
        viewModel.loadNextQuestion(whenEmpty = {showEmptyAlert()})
    }

    private fun showEmptyAlert(){
        context?.let {
            AlertFactory.simpleAlert(it,
                resources.getString(R.string.string_questions_alert_title),
                resources.getString(R.string.string_questions_alert_message),
                resources.getString(R.string.string_okay)).show()
        }
    }
}
