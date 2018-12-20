package gyy.com.ganwelfare.utils

import android.content.Context
import android.widget.Toast
import rx.Observable
import rx.android.schedulers.AndroidSchedulers


class ToastUtils(val context: Context){


    /**
     *
     * 展示提示框
     *
     * */
    fun showToast(message:String = "暂无提示参数", duration: Int = Toast.LENGTH_LONG){
        Observable.just(context)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe { Toast.makeText(context,message,duration).show()}
    }
}