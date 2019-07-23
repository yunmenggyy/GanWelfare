package gyy.com.ganwelfare

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import gyy.com.ganwelfare.base.BaseActivity
import gyy.com.ganwelfare.bean.GanWelfareBean
import gyy.com.ganwelfare.ui.MainPresenter
import gyy.com.ganwelfare.ui.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(),MainView{

    var pageIndex:Int = 0

    private val mainPresenter = MainPresenter(this)


    override fun getLayoutId(): Int = R.layout.activity_main



    override fun initView() {
        mainActivity_recycleView.layoutManager = GridLayoutManager(this,2)
        mainActivity_recycleView.adapter = MyRecycleAdapter(mActivity,  mutableListOf() )
        refreshLayout.setOnRefreshListener { mainPresenter.getBeautyImages(10, pageIndex++) }
    }


    override fun initData() {
        refreshLayout.autoRefresh()
    }


    /**
     *
     * 设置图片数据
     * */
    override fun setImags(datas: List<GanWelfareBean>?) {
        if ( datas == null )  return
        refreshLayout.finishRefresh()
        (mainActivity_recycleView.adapter as MyRecycleAdapter).mDatas.let {
            it?.clear()
            it?.addAll(datas)
        }
        (mainActivity_recycleView.adapter as MyRecycleAdapter).notifyDataSetChanged()
    }

    class MyHolder(view:View): RecyclerView.ViewHolder(view){
        var imageView = view.findViewById<ImageView>(R.id.item_image)
    }
    class MyRecycleAdapter(context:Context, datas:MutableList<GanWelfareBean>?): RecyclerView.Adapter<MyHolder>() {
        private var mContext = context
        var mDatas:MutableList<GanWelfareBean>? = datas

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
           Glide.with(mContext).load( (mDatas?.get(p1) as GanWelfareBean).url).into(p0.imageView)
        }
    }
}
