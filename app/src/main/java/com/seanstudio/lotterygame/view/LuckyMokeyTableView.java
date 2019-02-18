package com.seanstudio.lotterygame.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.seanstudio.lotterygame.R;

public class LuckyMokeyTableView extends FrameLayout {

    private boolean isMarqueeRunning = false;

    private ImageView bg_Table1;
    private ImageView bg_Table2;

    public LuckyMokeyTableView(@NonNull Context context) {
        this(context, null);
    }

    public LuckyMokeyTableView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LuckyMokeyTableView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.view_lucky_mokey_table, this);
        setupView();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startMarquee();
    }

    @Override
    protected void onDetachedFromWindow() {
        stopMarquee();
        super.onDetachedFromWindow();
    }

    private void setupView() {
        bg_Table1 = (ImageView) findViewById(R.id.ivTable1);
        bg_Table2 = (ImageView) findViewById(R.id.ivTable2);
    }

    private void stopMarquee() {
        isMarqueeRunning = false;
    }

    private void startMarquee() {
        isMarqueeRunning = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isMarqueeRunning) {
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    post(new Runnable() {
                        @Override
                        public void run() {
                            if (bg_Table1 != null && bg_Table2 != null) {
                                if (VISIBLE == bg_Table1.getVisibility()) {
                                    bg_Table1.setVisibility(INVISIBLE);
                                    bg_Table2.setVisibility(VISIBLE);
                                } else {
                                    bg_Table1.setVisibility(VISIBLE);
                                    bg_Table2.setVisibility(INVISIBLE);
                                }
                            }
                        }
                    });
                }
            }
        }).start();
    }

}
