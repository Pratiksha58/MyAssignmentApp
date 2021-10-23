package com.app.assignmentapp.data.services

import com.app.assignmentapp.data.Responses.DataResponse
import io.reactivex.Observable
import retrofit2.Response

interface RestApi {
    fun getData(): Observable<Response<DataResponse>>

}