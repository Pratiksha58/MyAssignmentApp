package com.app.assignmentapp.data.exceptions

class NotFoundException(error: String?) : Exception(error){
    override val message: String?
        get() = "not found. Please try again"
}