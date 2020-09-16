package com.apollo.android.cleanarchitecture.presentation.main

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.transition.TransitionManager
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.apollo.android.cleanarchitecture.R
import kotlinx.android.synthetic.main.layout_live_info.view.*


class LiveInfoView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_live_info, this)
    }

    var isExpanded = true

    @SuppressLint("NewApi")
    fun toggle(containerView:ConstraintLayout, viewId: Int) {
        if (isExpanded) {
            collapse(containerView, viewId)
        } else {
            expand(containerView, viewId)
        }
    }

    var oriHeight: Int = 0

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun collapse(containerView:ConstraintLayout, viewId: Int) {
        if (isExpanded.not()) return

        Log.e("@jj", "collapse")

        oriHeight = root.measuredHeight

        val set = ConstraintSet()
        set.clone(containerView)
        set.constrainHeight(viewId, 60)
        TransitionManager.beginDelayedTransition(containerView)
        set.applyTo(containerView)

        isExpanded = false
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun expand(containerView:ConstraintLayout, viewId: Int) {
        if (isExpanded) return

        Log.e("@jj", "expand")
        val set = ConstraintSet()
        set.clone(containerView)
        set.constrainHeight(viewId, oriHeight)
        TransitionManager.beginDelayedTransition(containerView)
        set.applyTo(containerView)

        isExpanded = true
    }

}