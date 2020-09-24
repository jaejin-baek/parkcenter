package com.apollo.android.cleanarchitecture.presentation.main.viewer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.apollo.android.cleanarchitecture.R
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : AppCompatActivity() {

    private val videoAdapter = VideoAdapter()

    private val viewModel = VideoViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_video)

        initView()

        attachData()
    }

    private fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = videoAdapter

        pipBtn.setOnClickListener {

        }
    }


    private fun attachData() {
        // attach RecyclerView
        videoAdapter.submitList(viewModel.getVideoData())
    }
}