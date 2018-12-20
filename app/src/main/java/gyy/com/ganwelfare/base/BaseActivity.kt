package gyy.com.ganwelfare.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity:AppCompatActivity(){

     lateinit var baseActivity:BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        baseActivity = this

        setContentView(getLayoutId())


    }


     /**
      * 返回布局
      * */
    abstract fun getLayoutId():Int
}