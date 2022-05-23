package com.reqres.remote.data

import com.google.gson.annotations.SerializedName

data class Wrapper<T>(
    @SerializedName("data")
    val data: T
)