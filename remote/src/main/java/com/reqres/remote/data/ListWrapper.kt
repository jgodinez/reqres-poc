package com.reqres.remote.data

import com.google.gson.annotations.SerializedName

data class ListWrapper<T>(
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("data")
    val data: List<T>
)