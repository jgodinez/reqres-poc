package com.reqres.recyclerview

interface RecyclerViewOnItemClickListener<in Model> {
    fun onClick(model: Model)
}