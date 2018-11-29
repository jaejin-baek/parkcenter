package com.apollo.android.cleanarchitecture.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apollo.android.cleanarchitecture.R
import com.apollo.android.cleanarchitecture.presentation.model.User
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.*

class MyViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {
    companion object {
        fun create(parent: ViewGroup): MyViewHolder {
            return MyViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_user,
                    parent,
                    false
                )
            )
        }
    }

    fun bind(user: User) {
        guestUserId.text = user.name
        userName.text = user.id.toString()
    }
}