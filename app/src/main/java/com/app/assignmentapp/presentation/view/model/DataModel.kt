package com.app.assignmentapp.presentation.view.model
import com.app.assignmentapp.data.Responses.DataResponseEntity
import com.google.gson.annotations.SerializedName

import com.app.assignmentapp.presentation.enums.Status

class DataModel(
    status: Status,
    var mDataEntity: DataResponseEntity?,
    var error: Throwable?
)  {
    @SerializedName("Success")
    var success = null
    @SerializedName("Message")
    var message: String? = null
    @SerializedName("Data")
    var data: List<DataModel>? = null

    var status: Status? = status

    companion object {

        fun success(response: DataResponseEntity): DataModel {
            return DataModel(Status.SUCCESS, response, null)
        }

        fun error(error: Throwable): DataModel {
            return DataModel(Status.ERROR, null, error)
        }
    }
}