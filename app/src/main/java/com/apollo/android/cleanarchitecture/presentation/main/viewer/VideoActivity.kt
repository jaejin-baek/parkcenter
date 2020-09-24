package com.apollo.android.cleanarchitecture.presentation.main.viewer

import android.app.PictureInPictureParams
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
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
            enterPip()
        }
    }

    private fun enterPip() {
        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val pipParams = PictureInPictureParams.Builder().build()

            enterPictureInPictureMode(pipParams)
        } else {
            // not Supported
            Log.e("@jj", "PIP not supported!!")
        }
    }


    private fun attachData() {
        // attach RecyclerView
        videoAdapter.submitList(viewModel.getVideoData())
    }

    override fun onPictureInPictureModeChanged(isInPictureInPictureMode: Boolean, newConfig: Configuration?) {
        if (isInPictureInPictureMode) {
            Toast.makeText(this, "PIP MODE", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "not PIP MODE", Toast.LENGTH_SHORT).show()
        }
    }
}