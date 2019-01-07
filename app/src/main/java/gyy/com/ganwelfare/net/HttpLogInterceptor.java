package gyy.com.ganwelfare.net;

import android.util.Log;
import gyy.com.ganwelfare.utils.JsonFormatUtils;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by GuanYangYi on 2016/5/17.
 * okhttp 拦截器
 */
public class HttpLogInterceptor implements Interceptor {

    public static final String TAG = "SZQB";

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Log.i(TAG, "request:" + request.toString());
        Log.i(TAG, "headers: \n" + request.headers().toString());
        long t1 = System.nanoTime();
        okhttp3.Response response = chain.proceed(chain.request());
        long t2 = System.nanoTime();
        okhttp3.MediaType mediaType = response.body().contentType();
        //Date: Mon, 07 Aug 2017 06:57:17 GMT
//        String date = response.header("Date").trim();
        String content = response.body().string();
        Headers headers = response.headers();
        Log.i(TAG, "  ");
        Log.i(TAG, "----------------------------------------------------------------------------------------|");
        Log.i(TAG, "|->url:" + request.url().toString());
        Log.i(TAG, "|   request body                                                                        |");
        Log.i(TAG, "|->request:" + request.body());
        Log.i(TAG, "|  ");
        Log.i(TAG, "|   response body ");
        Log.i(TAG, "|->time:" + (t2 - t1) / 1e6d);
        Log.i(TAG, "|->response body ");
        Log.i(TAG, JsonFormatUtils.formatJson(content));
        Log.i(TAG, "----------------------------------------------------------------------------------------|");
        Log.i(TAG, "  ");
        String date = headers.get("Date");
        long time = parseGMT("EEE, dd MMM yyyy HH:mm:ss 'GMT'", date, Locale.US);
        if (response.body() != null) {
            // 深坑！
            // 打印body后原ResponseBody会被清空，需要重新设置body
            ResponseBody body = ResponseBody.create(mediaType, content);
            return response.newBuilder().body(body).build();
        } else {
            return response;
        }
    }


    public long parseGMT(String pattern, String date, Locale locale){
        long time = 0L;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, locale);
        try {
            Date tDate = simpleDateFormat.parse(date);
            time = tDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }
}
