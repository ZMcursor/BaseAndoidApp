package com.zmcurosr.baseandoidapp.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.LruCache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zmcurosr.baseandoidapp.model.Bean.JsonResponse;

import java.lang.reflect.Type;
import java.util.Map;

public final class DataHolder {
    private final String TAG = "DataHolder";
    private static DataHolder mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context mCtx;

    private DataHolder(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();

        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<>(50);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static synchronized DataHolder getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DataHolder(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
//            mRequestQueue.start();
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }


    public <T> void addToRequestQueue(int method, String url, @Nullable Map<String, Object> data,
                                      Class<T> type, SuccessListener successListener, FailListener failListener) {
        StringRequest request = new StringRequest(method, url + toUrlString(data), response -> {
            Type jsonType = new TypeReference<JsonResponse<T>>(type) {
            }.getType();
            JsonResponse jsonResponse = JSON.parseObject(response, jsonType);
            if (jsonResponse.success()) {
                successListener.Request(jsonResponse.data);
            } else {
                failListener.Request(response, null);
            }
        }, error -> failListener.Request(null, error));
        addToRequestQueue(request);
    }

    public <T> void addToRequestQueue(String url, Map<String, Object> data, Class<T> type,
                                      SuccessListener successListener, FailListener failListener) {
        this.addToRequestQueue(Request.Method.GET, url, data, type, successListener, failListener);
    }


    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    public interface SuccessListener {
        void Request(Object result);
    }

    public interface FailListener {
        void Request(String response, VolleyError error);
    }

    private String toUrlString(@Nullable Map<String, Object> data) {
        String s = "";
        if (data != null && !data.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                String item = entry.getKey() + "=" + String.valueOf(entry.getValue());
                stringBuilder.append(item);
            }
            s = stringBuilder.toString();
        }
        return s;
    }
}
