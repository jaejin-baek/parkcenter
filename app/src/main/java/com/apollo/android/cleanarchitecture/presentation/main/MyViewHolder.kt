package com.apollo.android.cleanarchitecture.presentation.main

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apollo.android.cleanarchitecture.R
import com.apollo.android.cleanarchitecture.presentation.model.VideoFeed
import com.naver.media.nplayer.NPlayer
import com.naver.media.nplayer.NPlayerException
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.*
import java.net.URL
import android.os.AsyncTask
import android.view.WindowManager
import android.widget.FrameLayout
import android.util.DisplayMetrics


class MyViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    private lateinit var videoFeed: VideoFeed

    private var widthMax: Float = -1f

    private var heightMax: Float = -1f

    companion object {
        fun create(parent: ViewGroup): MyViewHolder {
            val holder = MyViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_user,
                    parent,
                    false
                )
            )

            holder.initValues(parent.context)

            return holder
        }
    }

    private fun initValues(context: Context) {
        val size = Point()

        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        wm.defaultDisplay.getSize(size)

        widthMax = size.x.toFloat()
        heightMax = size.y.toFloat()
    }

    fun bind(feed: VideoFeed) {
        videoFeed = feed

        guestUserId.text = videoFeed.id.toString()
        userName.text = videoFeed.name

        bindPlayer(feed)
    }

    private fun bindPlayer(feed: VideoFeed) {
        //playerThumbNailView.setImageURI(Uri.parse(feed.imageUrl))

        if (feed.thumb == null) {
            DownLoadImageTask().execute(feed.imageUrl)
        } else {
            playerView.background = BitmapDrawable(feed.thumb)
        }

        playerContainer.setOnClickListener {
            Log.e(
                "@jj",
                "onClicked! isPlaying : ${playerView.isPlaying}, isPrePared : ${playerView.isPreparing}"
            )

            if (!playerView.isPlaying) {
                playerView.start()
            } else {
                playerView.pause()
            }
        }

        playerView.addListener(object : NPlayer.EventListener {
            override fun onPlayerError(e: NPlayerException?) {
            }

            override fun onPlayerStateChanged(playWhenReady: Boolean, state: NPlayer.State?) {
            }

            override fun onVideoSizeChanged(width: Int, height: Int, pixelWidthHeightRatio: Float) {
                Log.d("@jj", "onVideoSizeChanged. width : $width, height : $height")

                setVideoViewSize(width, height)

                feed.width = width
                feed.height = height
            }

            override fun onPositionDiscontinuity() {
            }

            override fun onPlayerPrepared() {
                Log.d("@jj", "onPlayerPrepared")

                //playerView.background.setVisible(false, false)
                playerView.visibility = View.VISIBLE
                playerThumbNailView.visibility = View.GONE
            }

            override fun onPlayerInfo(what: Int, extras: Bundle?) {
            }

            override fun onRenderedFirstFrame() {
            }
        })

        playerView.volume = 0f
        Log.d("@jj", "isPreparing : ${playerView.isPreparing}")

        if (playerView.isPreparing) {
            // do nothing
        } else {
            playerView.prepare(Uri.parse(feed.url))
        }

        playerView.seekTo(feed.currentPosition)

        //playerView.playWhenReady = true
    }

    private inner class DownLoadImageTask() :
        AsyncTask<String, Void, Bitmap>() {

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        override fun doInBackground(vararg urls: String): Bitmap? {
            val urlOfImage = urls[0]
            var logo: Bitmap? = null
            try {
                val `is` = URL(urlOfImage).openStream()
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(`is`)
            } catch (e: Exception) { // Catch the download exception
                e.printStackTrace()
            }

            return logo
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        override fun onPostExecute(result: Bitmap) {
            playerThumbNailView.setImageDrawable(BitmapDrawable(result))
            //imageView.setImageBitmap(result)
        }
    }

    fun onViewAttachedToWindow() {
        playerView?.run {
            Log.d("@jj", "onViewAttachedToWindow isPreparing : $isPreparing")

            if (!isPreparing) {
                prepare(Uri.parse(videoFeed.url))
                seekTo(videoFeed.currentPosition)
            }
        }
    }

    fun onViewDetachedFromWindow() {
        Log.d("@jj", "onViewDetachedFromWindow")

        playerView?.run {
            videoFeed.currentPosition = playerView.currentPosition
            val bitmap = playerView.getCurrentFrame((width * .5f).toInt(), (height * .5f).toInt())

            bitmap?.let {
                videoFeed.thumb = bitmap
                background = BitmapDrawable(bitmap)
            }

            reset()
        }
    }

    fun onViewRecycled() {
        Log.d("@jj", "onViewRecycled")
        playerView?.run { release() }
    }

    private fun setVideoViewSize(resWidth: Int, resHeight: Int) {
        if (resWidth > 0 && resHeight > 0) {
            val widthRatio = widthMax / resWidth
            val heightRatio = heightMax / resHeight

            val multiValue = Math.min(widthRatio, heightRatio)

            val param = playerView.layoutParams as FrameLayout.LayoutParams
            param.width = (resWidth * multiValue).toInt()
            param.height = (resHeight * multiValue).toInt()
            playerView.layoutParams = param
        }
    }
}