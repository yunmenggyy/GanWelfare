package gyy.com.ganwelfare.base

import gyy.com.ganwelfare.utils.Utils
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.schedulers.Schedulers

/**
 *
 * 创建时间: 2019/1/3
 *
 * 作者：Guan Yang Yi
 *
 * 功能描述：基础转换类，进行请求的线程转换
 *
 *
 */
open class BasePresenter{


    fun <T> toSubscribe(observable: Observable<T>, action1: Action1<T>){
        observable.observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(action1, Action1 {  it.message?.let { it1 -> Utils.toastUtils.showToast(it1) };it.printStackTrace() })
    }
}