package com.example.vinsent_y.loverspace.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

//import com.example.vinsent_y.loverspace.adapter.ScheduleAdapter;
import com.example.vinsent_y.loverspace.R;
import com.example.vinsent_y.loverspace.entity.Schedule;

import java.util.ArrayList;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
//    private ScheduleAdapter mAdapter;
    private List<Schedule> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initView();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
//        mAdapter = new ScheduleAdapter(list);
//        mRecyclerView.setAdapter(mAdapter);
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.list_recycleView);
    }
}
