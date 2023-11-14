package com.example.flying.model

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("success") val success: Boolean = false,
) {
}