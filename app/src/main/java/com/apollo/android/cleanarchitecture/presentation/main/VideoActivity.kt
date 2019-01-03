package com.apollo.android.cleanarchitecture.presentation.main

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.apollo.android.cleanarchitecture.R
import com.apollo.android.cleanarchitecture.util.THUMBNAIL_URL
import com.apollo.android.cleanarchitecture.widget.AsyncImageView
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("@jj", "VideoActivity::onCreate")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Log.d("@jj", "VideoActivity::postpone")
            postponeEnterTransition()
        } else {
            supportPostponeEnterTransition()
        }

        setContentView(R.layout.activity_video)

        initView()
    }

    private fun initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fVideoThumbNailView.transitionName = "sharedThumb"
        }
        fVideoThumbNailView.setUrl(THUMBNAIL_URL)

        // after load
        fVideoThumbNailView.onLoadCompleteListener =
                object : AsyncImageView.OnLoadCompleteListener {
                    override fun onComplete(drawable: Drawable) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            startPostponedEnterTransition()
                        } else {
                            supportStartPostponedEnterTransition()
                        }
                    }
                }
    }
}