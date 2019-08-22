package com.dangerfield.wouldyourather.view.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProviders
import com.dangerfield.wouldyourather.Custom.log
import com.dangerfield.wouldyourather.Custom.select
import com.dangerfield.wouldyourather.Custom.unselect
import com.dangerfield.wouldyourather.R
import com.dangerfield.wouldyourather.repository.GameViewModel
import kotlinx.android.synthetic.main.fragment_game.view.*
import kotlinx.android.synthetic.main.fragment_game.view.btn_option2


class GameFragment : Fragment() {
    private val TAG = "!!!GAME FRAGMENT!!!"
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

        val packs = arguments?.run{
            this.getStringArrayList(resources.getString(R.string.packs_key))
        }
        viewModel = ViewModelProviders.of(activity!!).get(GameViewModel::class.java)

        //TODO: observe current question when updated , set views off screen, text text, animate slide in

    }

    private fun initializeViews(root: View) {
        "INIT GAME VIEWS".log()
        root.run{
            options = listOf(btn_option1,btn_option2)
            //if the other option hasnt been clicked, select this boy
            options.forEach { btn ->
                btn.setOnClickListener {
                    if(options.elementAt(options.size - options.indexOf(it) - 1).tag != 1)
                        it.select()
                }
            }

            btn_next.setOnClickListener { loadNextQuestion() }
        }
    }

    private fun loadNextQuestion() {
        options.forEach {
            it.text = ""
            it.unselect()
        }
        viewModel.getNextQuestion()
    }
}
