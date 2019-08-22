package com.dangerfield.wouldyourather.custom

import android.util.Log
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.dangerfield.wouldyourather.R

fun View.toggleBackground() {
     when(this.tag) {
        1 -> unselect()
        0, null -> select()
    }
}

fun View.select() {
    this.tag = 1
    this.background = ResourcesCompat.getDrawable(resources, R.drawable.option_button_selected_drawable,null)
}

fun View.unselect() {
    this.tag = 0
    this.background = ResourcesCompat.getDrawable(resources, R.drawable.option_button_drawable,null)
}

fun String.log() {
    Log.d("DEBUG:",this)
}
