package com.yzd.help;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AnalyzeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvScreen;

    private RecyclerView recycler;

    private List<String> datas = new ArrayList<>();

    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze);

        initView();
        initData();
    }

    private void initView() {
        tvScreen = findViewById(R.id.tv_screen);
        recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        myAdapter = new MyAdapter();
        recycler.setAdapter(myAdapter);
        tvScreen.setOnClickListener(this);
    }

    private void initData() {

        for (int i = 0; i < 10; i++) {
            datas.add("");
        }
        myAdapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View v) {
        new WindowScreen(this).show();
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new MyAdapter.ViewHolder(LayoutInflater.from(AnalyzeActivity.this).inflate(R.layout.item_analyze_layout, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        }

        @Override
        public int getItemCount() {
            return null == datas ? 0 : datas.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }
}
