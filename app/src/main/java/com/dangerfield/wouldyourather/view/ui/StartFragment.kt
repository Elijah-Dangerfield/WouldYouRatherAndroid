package com.dangerfield.wouldyourather.view.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.dangerfield.wouldyourather.custom.toggleBackground
import com.dangerfield.wouldyourather.R
import com.dangerfield.wouldyourather.viewmodel.GameViewModel
import kotlinx.android.synthetic.main.fragment_start.*
import kotlinx.android.synthetic.main.fragment_start.view.*
import kotlinx.android.synthetic.main.fragment_start.view.btn_start

class StartFragment : Fragment() {

    private var options = listOf<View>()
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_start, container, false)
        initViews(root)
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(GameViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        viewModel.resetGame()
    }

    fun initViews(root: View){
        viewModel.packs = listOf()
        root.run {
            options = listOf(btn_option1,btn_option2,btn_option3)

            options.forEach { view -> view.setOnClickListener { it.toggleBackground()}}

            btn_start.setOnClickListener {
                it.isClickable = false // to avoid sending multiple requests
                prepareNavigation()}
        }
    }

    private fun prepareNavigation() {
        viewModel.packs = options.filter { it.tag == 1 }.map { (it as Button).text.toString() }

        if (viewModel.packs.isEmpty()) {
            Toast.makeText(context, "Please Select a Pack", Toast.LENGTH_LONG).show()
            btn_start.isClickable = true
            return
        }

        viewModel.startGame()

        findNavController().navigate(R.id.action_startFragment_to_gameFragment)
        btn_start.isClickable = true
    }
}
