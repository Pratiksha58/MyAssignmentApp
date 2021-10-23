package com.app.assignmentapp.domain.interactor

import com.app.assignmentapp.data.Responses.DataResponseEntity
import com.app.assignmentapp.domain.repository.UserRepository
import io.reactivex.Observable

class DataUC constructor(val userRepository: UserRepository) :
    UseCase<DataResponseEntity, Unit>() {
    override fun build(param: Unit): Observable<DataResponseEntity> {
        return userRepository.getData()
    }
}