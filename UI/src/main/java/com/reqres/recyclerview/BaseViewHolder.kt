package com.reqres.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<in Model>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bindTo(model: Model)
}