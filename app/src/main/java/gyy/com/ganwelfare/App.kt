package gyy.com.ganwelfare

import android.app.Application
import android.content.Context
import gyy.com.ganwelfare.utils.ToastUtils

class App:Application(){

    lateinit var context:Context
    lateinit var toastUtils:ToastUtils

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        toastUtils = ToastUtils(context)
    }

}