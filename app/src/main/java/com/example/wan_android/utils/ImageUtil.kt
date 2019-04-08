package com.example.wan_android.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class ImageUtil {
    companion object {
        @JvmStatic
        @BindingAdapter("app:imageUrl")
        fun load(imageView: ImageView?, url: String) {
            imageView?.apply {
                Glide.with(this).load(url).into(this)
            }
        }
    }
}