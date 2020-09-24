package com.apollo.android.cleanarchitecture.presentation.main.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.apollo.android.cleanarchitecture.R
import kotlinx.android.synthetic.main.layout_myconstraintview.view.*

class MyConstraintView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_myconstraintview, this)
    }

    var expanded = true

    fun toggle() {
        if(expanded) {
            collapse()
        } else {
            expand()
        }
    }

    private fun expand() {
        if(expanded) {
            return
        }

        Log.e("@jj", "expand")


    }

    private fun collapse() {
        if(expanded.not()) {
            return
        }

        Log.e("@jj", "collapse")

        //expanded = false

        // do animation
        val set = ConstraintSet()
        set.clone(motion_container)

        set.constrainHeight(R.id.motion_container, 70)

        //set.constrainHeight(R.id.motion_child, 70)

        set.applyTo(motion_container)

    }


}