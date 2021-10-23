package com.app.assignmentapp.data.exceptions

class NetworkUnavailableException (error: String?):Exception(error) {

    override val message: String
        get() = "Please check your network connectivity"
}