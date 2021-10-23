package com.app.assignmentapp.data.services

import com.app.assignmentapp.data.Responses.DataResponse
import retrofit2.Call
import retrofit2.http.*

interface NetworkServices {
    @POST("getArticleListing")
    fun getData(): Call<DataResponse>

}