package com.reqres.component

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.Gravity
import android.widget.ImageView
import androidx.annotation.DrawableRes

interface ImageLoader {

    fun load(imageUrl: String): ImageLoaderBuilder

    fun load(@DrawableRes resId: Int): ImageLoaderBuilder

    interface ImageLoaderBuilder {
        fun placeholder(@DrawableRes resId: Int): ImageLoaderBuilder

        fun placeholder(placeholder: Drawable?): ImageLoaderBuilder

        fun error(@DrawableRes resId: Int): ImageLoaderBuilder

        fun error(error: Drawable?): ImageLoaderBuilder

        fun centerCrop(alignGravity: Int = Gravity.CENTER): ImageLoaderBuilder

        fun centerInside(): ImageLoaderBuilder

        fun into(imageView: ImageView)

        fun get(): Bitmap
    }
}