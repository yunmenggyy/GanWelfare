package gyy.com.ganwelfare.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tbruyelle.rxpermissions2.RxPermissions

abstract class BaseActivity:AppCompatActivity(){

    lateinit var mActivity :BaseActivity
    lateinit var rxPermission: RxPermissions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        mActivity = this
        rxPermission = RxPermissions(this)
        setWindow()
        setContentView(getLayoutId())
        initView()
        initData()
    }


     /**
      * 返回布局
      * */
    abstract fun getLayoutId():Int

    abstract fun initData()

    abstract fun initView()

    open fun setWindow(){

    }
}