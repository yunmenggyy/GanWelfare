package gyy.com.ganwelfare.ui

import gyy.com.ganwelfare.base.BasePresenter
import gyy.com.ganwelfare.net.HttpManager
import rx.functions.Action1

/**
 *
 * 创建时间: 2019/1/3
 *
 * 作者：
 *
 * 功能描述：首页的控制层
 *
 *
 */
class MainPresenter(mainView: MainView):BasePresenter(){

    private val mMainView = mainView


    /**
     * 获取首页的美女图片
     *
     * @param pageIndex 第几页
     * @param pageSize  每页多少条数据
     * */
    fun getBeautyImages(pageSize:Int, pageIndex:Int){

        toSubscribe(HttpManager.getInstance().mHttpApi.getBeautyImages(pageSize.toString(), pageIndex.toString()), Action1 {
            if (!it.error && it.results?.isNotEmpty() == true)
            mMainView.setImags(it?.results)
        })
    }
}