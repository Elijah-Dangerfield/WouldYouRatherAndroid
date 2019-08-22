package com.dangerfield.wouldyourather.Custom

import android.util.Log
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.dangerfield.wouldyourather.R


fun View.toggleBackground() {
    this.background = when(this.tag) {

        1 ->{
            this.tag = 0
            ResourcesCompat.getDrawable(resources, R.drawable.option_button_drawable,null)
        }

        0 ->{
            this.tag = 1
            ResourcesCompat.getDrawable(resources, R.drawable.option_button_selected_drawable,null)
        }

        else -> {
            this.tag = 1
            ResourcesCompat.getDrawable(resources, R.drawable.option_button_selected_drawable,null)
        }
    }
}

fun String.log() {
    Log.d("DEBUG:",this)
}