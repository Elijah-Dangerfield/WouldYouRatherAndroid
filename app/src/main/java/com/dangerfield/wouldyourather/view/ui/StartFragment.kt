package com.dangerfield.wouldyourather.view.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.dangerfield.wouldyourather.Custom.toggleBackground
import com.dangerfield.wouldyourather.R
import kotlinx.android.synthetic.main.fragment_start.*

class StartFragment : Fragment() {

    /*
    SEPERATION OF CONCERNS:
    this file is only concerned with displaying options to chose from. Upon clicking them the viewmodel (logic handler)
    will have its state updated
     */

    private val options = ArrayList<View>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        options.addAll(listOf(btn_option1,btn_option2,btn_option3))

        options.forEach {
            (it as Button).setOnClickListener { it.toggleBackground() }
        }


        btn_start.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_gameFragment)
        }
    }
}
