package com.example.learnandoird.common

import com.example.learnandoird.`interface`.RetrofitServices
import com.example.learnandoird.retrofit.RetrofitClient

object Common {
    private val BASE_URL = "https://www.simplifiedcoding.net/demos/"
    val retrofitServices: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}