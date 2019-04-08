package com.example.wan_android.base

data class BaseResponse<T>(var errorCode: Int, var errorMsg: String, var data: T)