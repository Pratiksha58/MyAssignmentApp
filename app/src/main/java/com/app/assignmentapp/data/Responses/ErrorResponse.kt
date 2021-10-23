package com.app.assignmentapp.data.Responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class ErrorResponse {

    @SerializedName("message")
    @Expose
    val message: String? = null
}