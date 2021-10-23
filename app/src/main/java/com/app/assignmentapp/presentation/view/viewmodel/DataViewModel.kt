package com.app.assignmentapp.presentation.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.assignmentapp.data.Responses.DataResponseEntity
import com.app.assignmentapp.domain.interactor.DataUC
import com.app.assignmentapp.presentation.view.model.DataModel
import io.reactivex.observers.DisposableObserver

class DataViewModel(var mDataUC: DataUC) : ViewModel() {
        var mMutableLiveDataModel = MutableLiveData<DataModel>()

        fun getDataResponse(): MutableLiveData<DataModel> {
            return mMutableLiveDataModel
        }
    fun getData() {
        mDataUC.execute(object : DisposableObserver<DataResponseEntity>() {
            override fun onComplete() {
                Log.d("TAG--> ", "onComplete")

            }

            override fun onNext(response: DataResponseEntity) {
                mMutableLiveDataModel.value = DataModel.success(response)
            }

            override fun onError(e: Throwable) {
                Log.d("TAG--> ", "onError" + e.message)
                mMutableLiveDataModel.value = DataModel.error(e)
            }


        }, Unit)
    }
}