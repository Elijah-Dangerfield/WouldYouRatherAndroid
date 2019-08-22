package com.dangerfield.wouldyourather.view.ui


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dangerfield.wouldyourather.Custom.log
import com.dangerfield.wouldyourather.R


class GameFragment : Fragment() {
    private val TAG = "!!!GAME FRAGMENT!!!"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val packs = arguments?.run{
            this.getStringArrayList(resources.getString(R.string.packs_key))
        }

        packs.toString().log()


    }

}
