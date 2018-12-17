package com.zmcurosr.baseandoidapp.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.zmcurosr.baseandoidapp.R;
import com.zmcurosr.baseandoidapp.model.Bean.ListItem;
import com.zmcurosr.baseandoidapp.model.DataFactory;
import com.zmcurosr.baseandoidapp.view.widget.ListAdapter;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private final String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private DataFactory dataFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        Log.i(TAG, "onCreate: begin");
        dataFactory = new DataFactory(this);
    }

    public void inflateList() {
        ArrayList<ListItem> list = dataFactory.getList();
        ListAdapter adapter = new ListAdapter(list, (v, position) -> Toast.makeText(getApplicationContext(), list.get(position).content, Toast.LENGTH_LONG).show());
        recyclerView.setAdapter(adapter);
    }

}
