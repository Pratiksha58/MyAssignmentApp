package com.app.assignmentapp.data.exceptions

class ExceptionFactory {

    companion object {

        fun create(code: Int, message: String?): Exception {
             when (code) {
                404 -> {
                    return NotFoundException(message)
                }
                 else -> return NetworkUnavailableException(message)
            }

        }
    }
}