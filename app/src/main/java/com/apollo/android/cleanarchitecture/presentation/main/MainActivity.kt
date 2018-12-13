package com.apollo.android.cleanarchitecture.presentation.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.apollo.android.cleanarchitecture.R
import com.apollo.android.cleanarchitecture.presentation.model.User
import com.apollo.android.cleanarchitecture.presentation.second.SecondActivity
import com.google.android.exoplayer2.SimpleExoPlayer
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by inject()

    private val adapter =
        MyAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        button.setOnClickListener { startActivity(Intent(this, SecondActivity::class.java)) }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = this.adapter

        viewModel.users.observe(this, Observer {
            Log.d("@jj", "Users : $it")

            adapter.setUsers(it)
        })

        viewModel.loadData()
    }

    class MyAdapter : ListAdapter<User, MyViewHolder>(
        DIFF_UTIL
    ) {
        private var users: List<User> = arrayListOf()

        companion object {
            val DIFF_UTIL = object : ItemCallback<User>() {
                override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                    return oldItem == newItem
                }
            }
        }

        fun setUsers(users: List<User>) {
            this.users = users
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder.create(
                parent
            )
        }

        override fun getItemCount(): Int {
            return users.size
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.bind(users[position])
        }
    }
}
