package com.dangerfield.wouldyourather.view.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.dangerfield.wouldyourather.Custom.log
import com.dangerfield.wouldyourather.Custom.toggleBackground
import com.dangerfield.wouldyourather.R
import com.dangerfield.wouldyourather.repository.GameViewModel
import kotlinx.android.synthetic.main.fragment_game.view.*


class GameFragment : Fragment() {
    private val TAG = "!!!GAME FRAGMENT!!!"
    private lateinit var viewModel: GameViewModel
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

        val packs = arguments?.run{
            this.getStringArrayList(resources.getString(R.string.packs_key))
        }
        viewModel = ViewModelProviders.of(activity!!).get(GameViewModel::class.java)

    }

    private fun initializeViews(root: View) {
        "INIT GAME VIEWS".log()
        root.run{
            listOf(btn_option1,btn_option2).forEach {btn ->
                btn.setOnClickListener { it.toggleBackground() }
            }

            btn_next.setOnClickListener { viewModel.getNextQuestion() }
        }
    }
}
