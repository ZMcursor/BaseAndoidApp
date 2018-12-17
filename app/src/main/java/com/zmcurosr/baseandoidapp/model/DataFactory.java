package com.zmcurosr.baseandoidapp.model;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.zmcurosr.baseandoidapp.model.Bean.JsonResponse;
import com.zmcurosr.baseandoidapp.model.Bean.List;
import com.zmcurosr.baseandoidapp.model.Bean.ListItem;
import com.zmcurosr.baseandoidapp.view.activity.MainActivity;

import java.util.ArrayList;

public final class DataFactory {
    private final String TAG = "DataFactory";
    private Context context;
    private ArrayList<ListItem> list;
    private long page = 0;
    private boolean hasMore = true;

    public DataFactory(Context context) {
        this.context = context;
        list = new ArrayList<>();
        refresh();
    }

    public boolean refresh() {
        Log.i(TAG, "refresh: ");
        if (hasMore) {
            String url = APIConfig.LIST_URL + page;
            DataHolder.getInstance(context).addToRequestQueue(url, null, List.class, result -> {
                List ls = (List) result;
                page = ls.max_cursor;
                hasMore = ls.has_more;
                list.addAll(ls.data);
                Log.i(TAG, ls.data.toString());
                ((MainActivity) context).inflateList();
            }, (response, error) -> Log.i(TAG, "refresh: get Data fail:" + error.toString()));
        }
        return hasMore;
    }


    public ArrayList<ListItem> getList() {
        return list;
    }
}
