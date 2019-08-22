package com.dangerfield.wouldyourather.view.ui


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.dangerfield.wouldyourather.Custom.toggleBackground
import com.dangerfield.wouldyourather.R
import com.dangerfield.wouldyourather.repository.GameViewModel
import kotlinx.android.synthetic.main.fragment_start.*

class StartFragment : Fragment() {

    /*
    SEPERATION OF CONCERNS:
    this file is only concerned with displaying options to chose from. Upon clicking them the viewmodel (logic handler)
    will have its state updated
     */

    private var options = listOf<View>()
    private lateinit var viewModel: GameViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(activity!!).get(GameViewModel::class.java)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //TODO these get redefined every time we visit this screen.. DEAL W/ THIS!

        options = listOf(btn_option1,btn_option2,btn_option3)

        options.forEach { view ->
            (view as Button).setOnClickListener { it.toggleBackground() }
        }

        btn_start.setOnClickListener {
            val packs = options.filter { it.tag == 1 }.map { (it as Button).text.toString() }

            if(packs.isNotEmpty()){
                val bundle = bundleOf(resources.getString(R.string.packs_key) to packs)
                findNavController().navigate(R.id.action_startFragment_to_gameFragment, bundle)
            }else{
                Toast.makeText(context,"Please Select a Pack",Toast.LENGTH_LONG).show()
            }
        }
    }


}
