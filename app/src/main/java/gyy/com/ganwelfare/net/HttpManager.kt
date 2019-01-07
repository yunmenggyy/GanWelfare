package gyy.com.ganwelfare.net

import com.google.gson.GsonBuilder
import gyy.com.ganwelfare.net.RequestUrls.BASE_URL
import gyy.com.ganwelfare.utils.Utils
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

/**
 *
 * 创建时间: 2019/1/3
 *
 * 作者：关杨义
 *
 * 功能描述：网络链接管理者
 *
 */
class HttpManager {

    var mHttpApi : Api

    /**
     * 获取实例
     * */
    companion object {
        fun getInstance(): HttpManager = Holder.instance
    }

    private object Holder {
        val instance = HttpManager()
    }

    /**
     * 初始化
     * */
    init {

        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()

        mHttpApi = retrofit.create(Api::class.java)

    }

    private fun createOkHttpClient(): OkHttpClient {
        val cache = Cache(File(Utils.context.cacheDir, "hkdCache"), (1024 * 1024 * 100).toLong())
       return OkHttpClient.Builder().cache(cache).addInterceptor(HttpLogInterceptor()).build()
    }

}