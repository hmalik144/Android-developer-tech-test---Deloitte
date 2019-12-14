package com.example.h_mal.shopapicasestudy.ui

interface ResponseListener{
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)

}