package com.app.assignmentapp.data.services

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.app.assignmentapp.data.Responses.DataResponse
import com.app.assignmentapp.data.Responses.ErrorResponse
import com.app.assignmentapp.data.exceptions.ExceptionFactory
import com.app.assignmentapp.data.exceptions.NetworkUnavailableException
import io.reactivex.Observable
import retrofit2.Response


class RestApiImpl constructor(
    var mNetworkService: NetworkServices,
    var mContext: Context
) : RestApi {

    var context: Context

    val type = object : TypeToken<ErrorResponse>() {}.type
    val gson = Gson()

    init {
        this.context = mContext
    }

    fun isThereInternetConnection(): Boolean {
        val cm =
            this.mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return cm!!.activeNetworkInfo != null
    }

    override fun getData(): Observable<Response<DataResponse>> {
        return Observable.create<Response<DataResponse>> { emitter ->

            if (!isThereInternetConnection()) {
                emitter.onError(NetworkUnavailableException("No Internet,Please check...!"))
                return@create
            }
            val sessionEntity: Response<DataResponse> =
                mNetworkService.getData().execute()

            if (sessionEntity.isSuccessful) {
                if (sessionEntity.body() != null) {
                    emitter.onNext(sessionEntity)

                    emitter.onComplete()
                } else {
                    emitter.onError(UnknownError())
                }
            } else {
                val error = sessionEntity.errorBody()
                Log.e("Error", "" + error)
                val errorResponse: ErrorResponse? =
                    gson.fromJson(sessionEntity.errorBody()!!.charStream(), type)
                emitter.onError(
                    ExceptionFactory.create(
                        sessionEntity.code(),
                        errorResponse?.message
                    )
                )
            }
        }
    }
}