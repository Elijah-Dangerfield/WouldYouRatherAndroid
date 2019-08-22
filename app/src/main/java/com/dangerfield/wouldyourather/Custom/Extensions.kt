package com.dangerfield.wouldyourather.Custom

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.dangerfield.wouldyourather.R
import kotlinx.android.synthetic.main.alert_custom.view.*


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
