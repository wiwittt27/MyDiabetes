package com.alawiyaa.mydiabetes.data.source.remote

import com.alawiyaa.mydiabetes.vo.Resource
import com.alawiyaa.mydiabetes.vo.Status

class ApiResponse<T>(val status: StatusResponse, val body: T?, val message: String?) {
    companion object {
        fun <T> success(body: T): ApiResponse<T> = ApiResponse(StatusResponse.SUCCESS, body, null)

        fun <T> error(msg: String, body: T): ApiResponse<T> = ApiResponse(StatusResponse.ERROR, body, msg)


    }
}