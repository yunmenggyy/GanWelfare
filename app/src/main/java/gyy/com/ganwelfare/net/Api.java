package gyy.com.ganwelfare.net;

import gyy.com.ganwelfare.bean.GanWelfareBean;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

import java.util.List;

/**
 * 创建时间: 2019/1/3
 * <p>
 * 作者：guan yang yi
 * <p>
 * 功能描述：存放所有的api请求地址
 */
public interface Api {

    @GET(RequestUrls.BEAUTY_IMAGES)
   Observable<List<GanWelfareBean>> getBeautyImages(@Path("pageSize") String pageSize, @Path("pageIndex") String pageIndex);
}
