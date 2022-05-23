package com.reqres.poc.ui.component

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.reqres.component.ImageLoader
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator

class PicassoImageLoader(
    private val context: Context,
    private val isDebug: Boolean
) : ImageLoader {

    private val picasso: Picasso
        get() = Picasso.get().apply {
            setIndicatorsEnabled(isDebug)
        }

    override fun load(imageUrl: String): ImageLoader.ImageLoaderBuilder {
        val requestCreator = picasso.load(imageUrl)
        return createBuilder(requestCreator)
    }

    override fun load(@DrawableRes resId: Int): ImageLoader.ImageLoaderBuilder {
        val requestCreator = picasso.load(resId)
        return createBuilder(requestCreator)
    }

    private fun createBuilder(requestCreator: RequestCreator) =
        PicassoImageLoaderBuilder(context, requestCreator)

    inner class PicassoImageLoaderBuilder(
        private val context: Context,
        private var requestCreator: RequestCreator
    ) : ImageLoader.ImageLoaderBuilder {

        private var placeholder: Drawable? = null
        private var error: Drawable? = null
        private var centerCropGravity: Int? = null
        private var centerInside: Boolean = false

        override fun placeholder(@DrawableRes resId: Int): ImageLoader.ImageLoaderBuilder {
            return this.apply {
                val placeholder = ContextCompat.getDrawable(context, resId)
                placeholder(placeholder)
            }
        }

        override fun placeholder(placeholder: Drawable?): ImageLoader.ImageLoaderBuilder {
            return this.apply {
                this.placeholder = placeholder
            }
        }

        override fun error(@DrawableRes resId: Int): ImageLoader.ImageLoaderBuilder {
            return this.apply {
                val error = ContextCompat.getDrawable(context, resId)
                error(error)
            }
        }

        override fun error(error: Drawable?): ImageLoader.ImageLoaderBuilder {
            return this.apply {
                this.error = error
            }
        }

        override fun centerCrop(alignGravity: Int): ImageLoader.ImageLoaderBuilder {
            return this.apply {
                this.centerCropGravity = alignGravity
            }
        }

        override fun centerInside(): ImageLoader.ImageLoaderBuilder {
            return this.apply {
                this.centerInside = true
            }
        }

        override fun into(imageView: ImageView) {
            requestCreator = requestCreator.fit()
            applyConfigurations()
            requestCreator.into(imageView)
        }

        override fun get(): Bitmap {
            applyConfigurations()
            return requestCreator.get()
        }

        private fun applyConfigurations() {
            if (placeholder != null) {
                requestCreator = requestCreator.placeholder(placeholder!!)
            }

            if (error != null) {
                requestCreator = requestCreator.error(error!!)
            }

            if (centerCropGravity != null) {
                requestCreator = requestCreator.centerCrop(centerCropGravity!!)
            }

            if (centerInside) {
                requestCreator = requestCreator.centerInside()
            }
        }
    }
}