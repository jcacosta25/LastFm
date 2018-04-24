package com.example.kon3050.lastfm.data.domain.model.base

open class BaseModel {

    var error: Boolean = false
    var message: String = ""
    var status: String = "SUCCESS"


    fun setError(message: String) {
        this.message = message
        this.error = true
        this.status = "ERROR"
    }
}
