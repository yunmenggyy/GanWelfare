package gyy.com.ganwelfare

import android.app.Application
import gyy.com.ganwelfare.utils.ToastUtils
import gyy.com.ganwelfare.utils.Utils

class App:Application(){


    override fun onCreate() {
        super.onCreate()
        Utils.context = applicationContext
        Utils.toastUtils = ToastUtils(this)
    }

}