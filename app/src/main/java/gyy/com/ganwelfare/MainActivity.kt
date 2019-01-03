package gyy.com.ganwelfare

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import gyy.com.ganwelfare.base.BaseActivity
import gyy.com.ganwelfare.bean.GanWelfareBean
import gyy.com.ganwelfare.ui.MainPresenter
import gyy.com.ganwelfare.ui.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(),MainView{


    private var mainPresenter = MainPresenter(this)


    override fun getLayoutId(): Int = R.layout.activity_main



    override fun initView() {
        mainActivity_recycleView.layoutManager = GridLayoutManager(this,2)
        mainActivity_recycleView.adapter = MyRecycleAdapter(mActivity,  mutableListOf() )
    }


    override fun initData() {
        mainActivity_refrushLayout.isRefreshing = true
        mainPresenter.getBeautyImages(10,1)
    }


    /**
     *
     * 设置图片数据
     * */
    override fun setImags(datas: List<GanWelfareBean>) {
        mainActivity_refrushLayout.isRefreshing = false
        (mainActivity_recycleView.adapter as MyRecycleAdapter).mDatas.let {
            it?.clear()
            it?.addAll(datas)
        }
    }

    class MyHolder(view:View):RecyclerView.ViewHolder(view){
        var imageView = view.findViewById<ImageView>(R.id.item_image)
    }
    class MyRecycleAdapter(context:Context, datas:MutableList<GanWelfareBean>?): RecyclerView.Adapter<MyHolder>() {
        private var mContext = context
        public var mDatas = datas

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyHolder {
           return MyHolder(View.inflate(mContext,R.layout.activity_main_recycle_item, null))
        }

        override fun getItemCount(): Int {
            return if (mDatas == null || mDatas?.size == 0)
                0
            else
                mDatas!!.size
        }

        override fun onBindViewHolder(p0: MyHolder, p1: Int) {
           Glide.with(mContext).load(mDatas?.get(p1)).into(p0.imageView)
        }
    }
}
