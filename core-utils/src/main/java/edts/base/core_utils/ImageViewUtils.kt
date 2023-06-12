package edts.base.core_utils

import android.app.Activity
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import id.co.edtslib.edtsds.R
import id.co.edtslib.edtsds.Util

fun AppCompatImageView.load(url: String?, placeholder: Int? = null, grayScale: Boolean = false) {
    if (url?.isNotEmpty() == true) {
        colorFilter = if (grayScale) {
            val colorMatrix = ColorMatrix()
            colorMatrix.setSaturation(0f)
            val filter = ColorMatrixColorFilter(colorMatrix)
            filter
        } else {
            null
        }

        Glide.with(this.context).load(url).placeholder(Util.shimmerDrawable)
            .error(R.drawable.ic_broken_product).into(this)
    }
    else if (placeholder != null) {
        setImageResource(placeholder)
    }
}

fun AppCompatImageView.loadFromCached(url: String?, placeholder: Int? = null, grayScale: Boolean = false) {
    if (url?.isNotEmpty() == true) {
        colorFilter = if (grayScale) {
            val colorMatrix = ColorMatrix()
            colorMatrix.setSaturation(0f)
            val filter = ColorMatrixColorFilter(colorMatrix)
            filter
        } else {
            null
        }

        Glide.with(this.context).load(url).placeholder(Util.shimmerDrawable)
            .error(R.drawable.ic_broken_product).dontTransform()
            .onlyRetrieveFromCache(true).listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    post {
                        this@loadFromCached.load(url = url, placeholder = placeholder, grayScale = grayScale)
                    }
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    post {
                        this@loadFromCached.setImageDrawable(resource)
                    }
                    return true
                }

            }).submit()
    }
    else if (placeholder != null) {
        setImageResource(placeholder)
    }
}

fun AppCompatImageView.loadCircle(url: String) {
    Glide.with(this.context).load(url).apply(
        RequestOptions().circleCrop()
        .placeholder(Util.shimmerDrawable).error(edts.base.android.core_utils.R.drawable.ic_circle_broken_image)).into(this)
}

fun AppCompatImageView.loadCircle(uri: Uri) {
    Glide.with(this.context).load(uri).apply(
        RequestOptions().circleCrop()
        .placeholder(Util.shimmerDrawable).error(edts.base.android.core_utils.R.drawable.ic_circle_broken_image)).into(this)
}

fun AppCompatImageView.load(uri: Uri) {
    Glide.with(this.context).load(uri).apply(
        RequestOptions()
        .placeholder(Util.shimmerDrawable).error(R.drawable.ic_broken_product)).into(this)
}

fun AppCompatImageView.loadCircle(url: GlideUrl) {
    Glide.with(this.context).load(url).apply(
        RequestOptions().circleCrop()
        .placeholder(Util.shimmerDrawable).error(edts.base.android.core_utils.R.drawable.ic_circle_broken_image)).into(this)
}

fun AppCompatImageView.load(url: GlideUrl) {
    Glide.with(this.context).load(url).apply(
        RequestOptions()
        .placeholder(Util.shimmerDrawable).error(R.drawable.ic_broken_product)).into(this)
}

fun AppCompatImageView.loadNoCache(url: GlideUrl) {
    Glide.with(this.context).load(url).
        diskCacheStrategy(DiskCacheStrategy.NONE).
        skipMemoryCache(true).apply(
        RequestOptions()
            .placeholder(Util.shimmerDrawable).error(R.drawable.ic_broken_product)).into(this)
}

fun AppCompatImageView.loadBanner(url: String) {
    Glide.with(this.context).load(url).placeholder(Util.shimmerDrawable)
        .error(R.drawable.ic_broken_banner).into(this)
}

fun AppCompatImageView.loadTransition(activity: Activity, url: String?, grayScale: Boolean,
                                      onCompleted: () -> Unit) {
    if (url?.isNotEmpty() == true) {
        try {
            Glide.with(context).load(url)
                .apply(
                    RequestOptions()
                        .placeholder(Util.shimmerDrawable)
                        .dontTransform()
                        .onlyRetrieveFromCache(true))
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        ActivityCompat.startPostponedEnterTransition(activity)
                        post {
                            try {
                                Glide.with(context).setDefaultRequestOptions(
                                    RequestOptions()
                                        .placeholder(Util.shimmerDrawable)
                                        .dontTransform()
                                )
                                    .load(url).into(this@loadTransition)
                                onCompleted()
                            } catch (e: IllegalArgumentException) {
                                onCompleted()
                            }
                        }
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        ActivityCompat.startPostponedEnterTransition(activity)
                        post {
                            try {
                                Glide.with(context).setDefaultRequestOptions(
                                    RequestOptions()
                                        .placeholder(Util.shimmerDrawable)
                                        .dontTransform()
                                )
                                    .load(url).into(this@loadTransition)
                                onCompleted()
                            } catch (e: IllegalArgumentException) {
                                onCompleted()
                            } catch (e: OutOfMemoryError) {
                                onCompleted()
                            }
                        }
                        return false
                    }

                })
                .into(this)

            colorFilter = if (grayScale) {
                val colorMatrix = ColorMatrix()
                colorMatrix.setSaturation(0f)
                val filter = ColorMatrixColorFilter(colorMatrix)
                filter
            } else {
                null
            }
        } catch (e: OutOfMemoryError) {
            ActivityCompat.startPostponedEnterTransition(activity)
            onCompleted()
        } catch (e: IllegalArgumentException) {
            ActivityCompat.startPostponedEnterTransition(activity)
            onCompleted()
        } catch (ignore: VerifyError) {
        } catch (ignore: IllegalStateException) {
            // nothing to dos
        }
    }
    else {
        ActivityCompat.startPostponedEnterTransition(activity)
        onCompleted()
    }
}

fun AppCompatImageView.load(url: String, corner: Int) {
    if (corner > 0) {
        Glide.with(this.context).load(url).transform(CenterInside(), RoundedCorners(corner))
            .placeholder(Util.shimmerDrawable).error(R.drawable.ic_broken_banner).into(this)
    } else {
        Glide.with(this.context).load(url).into(this)

    }
}

fun AppCompatImageView.load(url: GlideUrl, corner: Int) {
    if (corner > 0) {
        Glide.with(this.context).load(url).transform(CenterCrop(), RoundedCorners(corner))
            .placeholder(Util.shimmerDrawable).error(R.drawable.ic_broken_banner).into(this)
    } else {
        Glide.with(this.context).load(url).into(this)

    }
}