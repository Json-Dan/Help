package com.yzd.help;

import android.app.Activity;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class WindowScreen extends PopupWindow implements View.OnClickListener {

    private Activity activity;

    private TextView tvSort, tv2, tv3, tv4;

    private TextView tvStartDate;
    private TextView tvEndDate;


    public WindowScreen(Activity activity) {
        super(activity);
        this.activity = activity;
        if (null != activity && !activity.isFinishing())
            init();
    }

    private void init() {

        View view = View.inflate(activity, R.layout.window_screen, null);
        this.setContentView(view);
        this.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        this.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
//        this.setBackgroundDrawable(new ColorDrawable(0x00000000));

//        this.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
//        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
//        this.setBackgroundDrawable(new ColorDrawable(0x40000000));

//        this.setAnimationStyle(R.style.drawer_anim);
        this.setOutsideTouchable(true);
        this.setFocusable(true);
        initView(view);

    }

    private void initView(View view) {
        tvStartDate = view.findViewById(R.id.tv_selector_start_date);
        tvEndDate = view.findViewById(R.id.tv_selector_end_date);
        tvSort = view.findViewById(R.id.tv_selector_sort);
        tv2 = view.findViewById(R.id.tv_selector_status);
        tv3 = view.findViewById(R.id.tv_selector_bm);
        tv4 = view.findViewById(R.id.tv_selector_wx);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tvSort.setOnClickListener(this);
        tvStartDate.setOnClickListener(this);
        tvEndDate.setOnClickListener(this);
    }


    public void closePop() {
        if (this.isShowing())
            this.dismiss();
    }


    public WindowScreen show() {
        if (null != activity && !activity.isFinishing() && !isShowing()) {

            showAtLocation(activity.getWindow().getDecorView(), Gravity.RIGHT | Gravity.TOP, 0, 0);
//            WindowUtils.setAlpha(activity, this);
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


    @Override
    public void onClick(final View v) {
        if (v.getId() == R.id.tv_selector_start_date || v.getId() == R.id.tv_selector_end_date)
        {
            Toast.makeText(activity,"需要时间选择器",Toast.LENGTH_SHORT).show();
            return;
        }

        new WindowSelector(activity).setDatas().setListener(new WindowSelector.OnSelectorListener() {
            @Override
            public void onSelector(String content) {
                ((TextView)v).setText(content);
            }
        }).show(v);
    }
}
