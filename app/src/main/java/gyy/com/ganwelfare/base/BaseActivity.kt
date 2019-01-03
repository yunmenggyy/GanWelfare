package gyy.com.ganwelfare.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity:AppCompatActivity(){

    public lateinit var mActivity :BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        mActivity = this
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
}