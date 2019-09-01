package com.dangerfield.wouldyourather.custom

import android.animation.ValueAnimator
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.dangerfield.wouldyourather.R
import kotlinx.android.synthetic.main.fragment_game.*

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


val rotateAnimation = ValueAnimator.ofFloat(0f,720f)

fun View.startRotation() {
    rotateAnimation.addUpdateListener {
        val value = it.animatedValue as Float
        this.rotation = value
    }
    rotateAnimation.duration = 1000
    rotateAnimation.start()
}

fun View.stopRotation() {
    rotateAnimation.cancel()
}

