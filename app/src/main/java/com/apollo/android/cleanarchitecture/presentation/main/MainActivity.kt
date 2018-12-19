package com.apollo.android.cleanarchitecture.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.apollo.android.cleanarchitecture.R
import com.apollo.android.cleanarchitecture.presentation.model.VideoFeed
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by inject()

    private val adapter = MyAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = this.adapter

        viewModel.users.observe(this, Observer {
            Log.d("@jj", "Users : $it")

            adapter.setUsers(it)
        })

        viewModel.loadData()
    }

    class MyAdapter : ListAdapter<VideoFeed, MyViewHolder>(DIFF_UTIL) {
        private var videoFeeds: List<VideoFeed> = arrayListOf()

        companion object {
            val DIFF_UTIL = object : ItemCallback<VideoFeed>() {
                override fun areItemsTheSame(oldItem: VideoFeed, newItem: VideoFeed): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: VideoFeed, newItem: VideoFeed): Boolean {
                    return oldItem == newItem
                }
            }
        }

        fun setUsers(videoFeeds: List<VideoFeed>) {
            this.videoFeeds = videoFeeds
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder.create(parent)
        }

        override fun getItemCount(): Int {
            return videoFeeds.size
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.bind(videoFeeds[position])
        }

        override fun onViewAttachedToWindow(holder: MyViewHolder) {
            holder.onViewAttachedToWindow()
            super.onViewAttachedToWindow(holder)
        }

        override fun onViewDetachedFromWindow(holder: MyViewHolder) {
            holder.onViewDetachedFromWindow()
            super.onViewDetachedFromWindow(holder)
        }

        override fun onViewRecycled(holder: MyViewHolder) {
            holder.onViewRecycled()
            super.onViewRecycled(holder)
        }
    }
}
