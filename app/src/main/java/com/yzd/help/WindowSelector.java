package com.yzd.help;

import android.app.Activity;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WindowSelector extends PopupWindow {

    private Activity activity;


    private RecyclerView recycler;

    private List<String> datas = new ArrayList<>();


    private MyAdapter adapter;

    public WindowSelector(Activity activity) {
        super(activity);
        this.activity = activity;
        if (null != activity && !activity.isFinishing())
            init();
    }

    private void init() {

        View view = View.inflate(activity, R.layout.window_selector, null);
        this.setContentView(view);
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        this.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
//        this.setBackgroundDrawable(new ColorDrawable(0x00000000));

//        this.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
//        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
//        this.setBackgroundDrawable(new ColorDrawable(0x40000000));

//        this.setAnimationStyle(R.style.drawer_anim);
        this.setOutsideTouchable(true);
        this.setFocusable(true);

        recycler = view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        adapter = new MyAdapter();
        recycler.setAdapter(adapter);


    }


    public WindowSelector setDatas() {
        for (int i = 0; i < 5; i++) {

            datas.add("Selector " + (i + 1));
        }

        adapter.notifyDataSetChanged();
        return this;
    }


    public void closePop() {
        if (this.isShowing())
            this.dismiss();
    }


    public WindowSelector show(View view) {
        if (null != activity && !activity.isFinishing() && !isShowing()) {
//            showAsDropDown(view);
            showAsDropDown(view, 0, 0);

        }
        return this;
    }


//    public WindowComments show(View view) {
//        if (null != activity && !activity.isFinishing() && !isShowing()) {
//            WindowUtils.setAlpha(activity, this);
//            showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
//
//        }
//        return this;
//    }


    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


        @NonNull
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new MyAdapter.ViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_selector_layout, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.ViewHolder viewHolder, final int i) {
            viewHolder.tvContent.setText(datas.get(i));

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    closePop();
                    if (listener != null)
                        listener.onSelector(datas.get(i));
                }
            });
        }

        @Override
        public int getItemCount() {
            return null == datas ? 0 : datas.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView tvContent;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tvContent = itemView.findViewById(R.id.tv_content);
            }
        }
    }


    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Activity context) {
        Rect frame = new Rect();
        context.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }
    private OnSelectorListener listener;

    public WindowSelector setListener(OnSelectorListener listener) {
        this.listener = listener;
        return this;
    }

    public interface OnSelectorListener{
        void onSelector(String content);
    }
}
