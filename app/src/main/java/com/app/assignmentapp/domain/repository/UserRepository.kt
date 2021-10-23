package com.app.assignmentapp.domain.repository

import com.app.assignmentapp.data.Responses.DataResponseEntity
import io.reactivex.Observable

interface UserRepository {
    fun getData(): Observable<DataResponseEntity>
}