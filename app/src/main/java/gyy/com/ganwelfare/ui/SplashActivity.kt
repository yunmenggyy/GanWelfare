package gyy.com.ganwelfare.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.view.Window
import android.view.WindowManager
import com.bumptech.glide.Glide
import gyy.com.ganwelfare.MainActivity
import gyy.com.ganwelfare.R
import gyy.com.ganwelfare.base.BaseActivity
import gyy.com.ganwelfare.utils.Utils
import kotlinx.android.synthetic.main.activity_splash.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

/**
 *
 * 创建时间: 2019/1/7
 *
 * 作者：Guan Yang YI
 *
 * 功能描述：
 *
 *  app的启动页面
 */
class SplashActivity:BaseActivity(){
    override fun getLayoutId(): Int  = R.layout.activity_splash

    override fun initData() {
    }


    @SuppressLint("CheckResult")
    override fun initView() {
        rxPermission.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET)
            .subscribe {
                if (it){
                    Glide.with(this).load(R.mipmap.wukong).into(spalsh_activity_image)
                    autoJump()
                }else{
                    Utils.toastUtils.showToast("请前往设置中开启必要权限！")
                }

            }
    }

    private fun autoJump() {
        Observable.interval(0, 1, TimeUnit.SECONDS).take(3)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(AndroidSchedulers.mainThread()).subscribe { aLong ->
                if (aLong >= 2) {
                    val intent = Intent(mActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
    }

}