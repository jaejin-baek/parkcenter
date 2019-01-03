package com.apollo.android.cleanarchitecture.widget

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.webkit.URLUtil.isValidUrl
import androidx.appcompat.widget.AppCompatImageView
import com.apollo.android.cleanarchitecture.R
import com.apollo.android.cleanarchitecture.util.ImageUtils
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.ViewPropertyTransition

/**
 * Remote에 있는 image를 다운로드하여 imageView 객체에 보여주는 클래스
 */
class AsyncImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr), RequestListener<Drawable> {

    private val alphaAnimator: AlphaAnimator
    private val circleCrop: Boolean // Style CircleCrop
    private val roundedCorners: Int // Style RoundedCorners

    var onLoadCompleteListener: OnLoadCompleteListener? = null

    init {
        this.alphaAnimator = AlphaAnimator()
        val ta = context.obtainStyledAttributes(attrs, R.styleable.AsyncImageView)
        this.circleCrop = ta.getBoolean(R.styleable.AsyncImageView_circleCrop, false)
        this.roundedCorners = ta.getDimensionPixelSize(R.styleable.AsyncImageView_roundedCorners, 0)
        ta.recycle()
    }

    /**
     * Set an image url with a placeholder drawable like a loading spinner.
     */
    fun setUrl(url: String?, placeHolder: Drawable) {
        val opt = Options()
        opt.placeHolder = placeHolder
        setUrl(url, opt)
    }

    /**
     * Set an image url with a custom [Options].
     */
    fun setUrl(url: String?, opts: Options? = DEFAULT_OPTIONS) {
        var opts = opts
        if (isValidUrl(url)) {
            if (opts == null) {
                opts = DEFAULT_OPTIONS
            }
            loadImage(
                url = url,
                showAlphaTransition = opts.showAlphaTransition,
                placeHolder = opts.placeHolder,
                playGif = opts.playGif,
                transformation = opts.bitmapTransformation
            )
        }
    }

    private fun loadImage(
        url: String?, showAlphaTransition: Boolean, placeHolder: Drawable?,
        playGif: Boolean, transformation: BitmapTransformation?
    ) {
        var url = url
        val requestOptions = buildRequestOptions(placeHolder, transformation, playGif)
        if (playGif && ImageUtils.isGifUrl(url)) {
            // remove thumbnail suffix to play gif animation.
            url = ImageUtils.removeThumbnailTypeFromGifUrl(url)
        }
        Glide.with(context)
            .load(url)
            // .asGif() (or omitted)
            // just using load() will load either a GIF or a Bitmap depending on the type of the data.
            .transition(
                if (showAlphaTransition)
                    GenericTransitionOptions.with<Any>(alphaAnimator)
                else
                    GenericTransitionOptions.withNoTransition<Any>()
            )
            .apply(requestOptions)
            .listener(this)
            .into(this)
    }

    override fun onLoadFailed(
        e: GlideException?,
        model: Any,
        target: Target<Drawable>,
        isFirstResource: Boolean
    ): Boolean {
        Log.d(LOG_TAG, "onLoadFailed(), url = $model, isFirstResource = $isFirstResource")
        e?.printStackTrace()
        return false
    }

    override fun onResourceReady(
        resource: Drawable?,
        model: Any,
        target: Target<Drawable>,
        dataSource: DataSource,
        isFirstResource: Boolean
    ): Boolean {
        if (resource != null) {
            Log.d(LOG_TAG, "onResourceReady(), url = $model, isFirstResource = $isFirstResource")
            if (onLoadCompleteListener != null) {
                onLoadCompleteListener!!.onComplete(resource)
            }
        }
        return false
    }

    override fun setImageResource(resId: Int) {
        super.setBackground(null)
        var rb = Glide.with(context)
            .load(resId)
        if (circleCrop) {
            rb = rb.apply(RequestOptions.circleCropTransform())
        }
        rb.into(this)
    }

    private fun buildRequestOptions(
        placeHolder: Drawable?,
        transformation: BitmapTransformation?, playGif: Boolean
    ): RequestOptions {
        var options = RequestOptions()
        if (transformation != null) {
            options = options.transforms(transformation)
        }
        if (placeHolder != null) {
            options = options.placeholder(placeHolder)
        }
        options =
                options.diskCacheStrategy(if (playGif) DiskCacheStrategy.DATA else DiskCacheStrategy.RESOURCE)
        if (circleCrop) {
            options = options.circleCrop()
        }
        if (roundedCorners != 0) {
            options = options.transform(RoundedCorners(roundedCorners))
        }

        return options
    }

    interface OnLoadCompleteListener {
        fun onComplete(drawable: Drawable)
    }

    private class AlphaAnimator : ViewPropertyTransition.Animator {
        override fun animate(view: View) {
            val anim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1.0f)
            anim.interpolator = AccelerateDecelerateInterpolator()
            anim.duration = ALPHA_ANIM_DURATION.toLong()
            anim.start()
        }
    }

    class Options {
        /**
         * If it's true, a default alpha transition will be applied.
         */
        var showAlphaTransition: Boolean = false
        /**
         * If it's true and the image type is gif as well, the gif will be played automatically.
         */
        var playGif: Boolean = false
        /**
         * A placeholder drawable like a loading spinner
         */
        var placeHolder: Drawable? = null
        /**
         * An extra glide bitmapTransformation
         */
        var bitmapTransformation: BitmapTransformation? = null

        init {
            showAlphaTransition = true
            playGif = false
            placeHolder = null
            bitmapTransformation = null
        }
    }

    companion object {
        private val LOG_TAG = AsyncImageView::class.java.simpleName
        private val ALPHA_ANIM_DURATION = 600 // millis
        private val DEFAULT_OPTIONS = Options()
    }
}
