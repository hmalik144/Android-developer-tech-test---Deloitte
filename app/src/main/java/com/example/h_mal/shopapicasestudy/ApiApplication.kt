package com.example.h_mal.shopapicasestudy

import android.app.Application
import com.example.h_mal.shopapicasestudy.db.AppDatabase

class ApiApplication : Application() {

    companion object{
        var db: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()

        db = AppDatabase.invoke(this)
    }
}