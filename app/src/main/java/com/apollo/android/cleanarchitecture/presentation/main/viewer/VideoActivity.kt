package com.apollo.android.cleanarchitecture.presentation.main.viewer

import android.app.PictureInPictureParams
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.apollo.android.cleanarchitecture.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
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

    private val imageURL = "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMDA4MzBfNzcg%2FMDAxNTk4NzUwMTEzOTc0.ynHXXcBBH3HkmIYuslyzwI9wPgjAAariLcS_JxJldRMg.FdJKhkJtzEwGWL99zXUVginhbKIPMGLlxWuQG3Dr6wMg.PNG.minizzoa%2Fmobile_184744617136.png&type=l340_165"

    private fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = videoAdapter

        pipBtn.setOnClickListener {
            startActivity(Intent(this, TestActivity::class.java))
            finish()

            //enterPip()
        }

        Glide.with(this)
                .asBitmap()
                .load(imageURL)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        Log.e("@jj", "onResourceReady")

                        val bitmap = Bitmap.createBitmap(resource, 0, 0, resource.width/2, resource.height/2)
                        resource.recycle()        // Caution!!!
                        myBitmap.setImageBitmap(bitmap)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        Log.e("@jj", "onLoadCleared")
                    }
                })
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