package com.asquare.assignment.models

import com.google.gson.annotations.SerializedName

class BaseResponse<T>(
    @SerializedName("data") val data: T,
    @SerializedName("message") val message: String
)