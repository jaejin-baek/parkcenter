package com.apollo.android.cleanarchitecture.presentation.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.apollo.android.cleanarchitecture.R
import com.apollo.android.cleanarchitecture.event.VideoOnClickEvent
import com.apollo.android.cleanarchitecture.presentation.model.VideoFeed
import com.apollo.android.cleanarchitecture.util.RxBus
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by inject()

    private val adapter = MyAdapter()

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        init()

        viewModel.loadData()

        // test()
    }

    private fun init() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = this.adapter

        viewModel.users.observe(this, Observer {
            Log.d("@jj", "Users : $it")

            adapter.setUsers(it)
        })

        RxBus.listen<VideoOnClickEvent>().subscribe {
            Log.d("@jj", "video clicked!!")
            val options = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptionsCompat.makeSceneTransitionAnimation(this, it.view, "sharedThumb").toBundle()
            } else null

            startActivity(Intent(this, VideoActivity::class.java), options)
        }.addTo(compositeDisposable)
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

    private fun test() {

    }
}
