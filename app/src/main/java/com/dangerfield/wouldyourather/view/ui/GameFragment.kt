package com.dangerfield.wouldyourather.view.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dangerfield.wouldyourather.Custom.AlertFactory
import com.dangerfield.wouldyourather.Custom.log
import com.dangerfield.wouldyourather.Custom.select
import com.dangerfield.wouldyourather.Custom.unselect
import com.dangerfield.wouldyourather.R
import com.dangerfield.wouldyourather.model.Question
import com.dangerfield.wouldyourather.repository.GameViewModel
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.fragment_game.view.*
import kotlinx.android.synthetic.main.fragment_game.view.btn_option1
import kotlinx.android.synthetic.main.fragment_game.view.btn_option2


class GameFragment : Fragment() {
    private lateinit var viewModel: GameViewModel
    lateinit var options: List<Button>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_game, container, false)
        initializeViews(root)
        return root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(activity!!).get(GameViewModel::class.java)

        viewModel.getRanges { viewModel.pool.toString().log() }

        viewModel.getQuestion().observe(viewLifecycleOwner, Observer {
            updateOptions(it)
        })


    }

    private fun updateOptions(question: Question) {
        btn_option1.text = question.questions[0]
        btn_option2.text = question.questions[1]
        tv_pct_1.text = question.option1Votes.toString()
        tv_pct_2.text = question.option2Votes.toString()
    }

    private fun initializeViews(root: View) {
        root.run{
            options = listOf(btn_option1,btn_option2)

            options.forEach { option -> option.setOnClickListener { vote(option) }}

            btn_next.setOnClickListener { loadNextQuestion() }
        }
    }

    private fun vote(option: Button){
        if(options.elementAt(options.size - options.indexOf(option) - 1).tag != 1){
            option.select()
            viewModel.submitVote {
                tv_pct_1.visibility = View.VISIBLE
                tv_pct_2.visibility = View.VISIBLE
            }
        }
    }

    private fun loadNextQuestion() {
        options.forEach {
            it.text = ""
            it.unselect()
        }
        tv_pct_1.visibility = View.INVISIBLE
        tv_pct_2.visibility = View.INVISIBLE
        viewModel.loadNextQuestion(whenEmpty = {showEmptyAlert()})
    }

    private fun showEmptyAlert(){
        context?.let {
            AlertFactory.simpleAlert(it,"No More Questions","There are no more questions for the selected" +
                    "packs :(. More will be coming soon!","Okay").show()
        }
    }
}
