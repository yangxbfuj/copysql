package com.ysqlcp.yxb.ysqlcp

import android.app.Application
import android.util.Log
import com.ysqlcp.yxb.ysalcp.copySQL

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        if (copySQL("test.sqlite", R.raw.test, this))
            Log.i("App", "copy success")
        else
            Log.i("App", "copy fail")
    }
}
