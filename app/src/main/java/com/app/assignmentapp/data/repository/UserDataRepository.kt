package com.app.assignmentapp.data.repository

import com.app.assignmentapp.data.Mapper.UserDataMapper
import com.app.assignmentapp.data.Responses.DataResponseEntity
import com.app.assignmentapp.data.services.RestApi
import com.app.assignmentapp.domain.repository.UserRepository
import io.reactivex.Observable

class UserDataRepository constructor(var mRestApi: RestApi, var mUserDataMapper: UserDataMapper) :
    UserRepository {
    override fun getData(): Observable<DataResponseEntity> {
        return mRestApi.getData().map(mUserDataMapper::getData)
    }
}