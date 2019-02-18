package com.seanstudio.lotterygame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.seanstudio.lotterygame.view.LuckyMonkeyPanelView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends Activity implements View.OnClickListener {

    private LuckyMonkeyPanelView lucky_panel;

    private JSONArray jsonArray;
    private JSONObject jsonObject;
    private TextView m_tvAwardPoint1;
    private TextView m_tvAwardPoint2;
    private TextView m_tvAwardPoint3;
    private TextView m_tvAwardPoint4;
    private TextView m_tvAwardPoint5;
    private TextView m_tvAwardPoint6;
    private TextView m_tvAwardName1;
    private TextView m_tvAwardName2;
    private TextView m_tvAwardName3;
    private TextView m_tvAwardName4;
    private TextView m_tvAwardName5;
    private TextView m_tvAwardName6;
    private ImageView m_ivReallyOver1;
    private ImageView m_ivReallyOver2;
    private ImageView m_ivReallyOver3;
    private ImageView m_ivReallyOver4;
    private ImageView m_ivReallyOver5;
    private ImageView m_ivReallyOver6;
    private ImageView m_ivFinger;
    private boolean m_bStarGame = false;
    private int m_iPoint;
    private int m_iPlayPoint;
    private final Handler handler = new Handler();

    private int[] iNumber = {10, 0, 4, 3, 5, 7, 2, 1, 6, 8, 9, 11};
    private ImageView[] view = new ImageView[6];


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_horsetable);
        initBody();

    }

    private void initBody() {
        ImageView m_ivAwardPoint1 = (ImageView) findViewById(R.id.ivAwardPoint1);
        ImageView m_ivAwardPoint2 = (ImageView) findViewById(R.id.ivAwardPoint2);
        ImageView m_ivAwardPoint3 = (ImageView) findViewById(R.id.ivAwardPoint3);
        ImageView m_ivAwardPoint4 = (ImageView) findViewById(R.id.ivAwardPoint4);
        ImageView m_ivAwardPoint5 = (ImageView) findViewById(R.id.ivAwardPoint5);
        ImageView m_ivAwardPoint6 = (ImageView) findViewById(R.id.ivAwardPoint6);
        m_ivAwardPoint6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        view[0] = m_ivAwardPoint1;
        view[1] = m_ivAwardPoint2;
        view[2] = m_ivAwardPoint3;
        view[3] = m_ivAwardPoint4;
        view[4] = m_ivAwardPoint5;
        view[5] = m_ivAwardPoint6;

        m_tvAwardName1 = findViewById(R.id.tvAwardName1);
        m_tvAwardName2 = findViewById(R.id.tvAwardName2);
        m_tvAwardName3 = findViewById(R.id.tvAwardName3);
        m_tvAwardName4 = findViewById(R.id.tvAwardName4);
        m_tvAwardName5 = findViewById(R.id.tvAwardName5);
        m_tvAwardName6 = findViewById(R.id.tvAwardName6);
        m_tvAwardPoint1 = (TextView) findViewById(R.id.tvAwardPoint1);
        m_tvAwardPoint2 = (TextView) findViewById(R.id.tvAwardPoint2);
        m_tvAwardPoint3 = (TextView) findViewById(R.id.tvAwardPoint3);
        m_tvAwardPoint4 = (TextView) findViewById(R.id.tvAwardPoint4);
        m_tvAwardPoint5 = (TextView) findViewById(R.id.tvAwardPoint5);
        m_tvAwardPoint6 = (TextView) findViewById(R.id.tvAwardPoint6);
        ImageView m_ivAwardPic1 = (ImageView) findViewById(R.id.ivAwardPic1);
        ImageView m_ivAwardPic2 = (ImageView) findViewById(R.id.ivAwardPic2);
        ImageView m_ivAwardPic3 = (ImageView) findViewById(R.id.ivAwardPic3);
        ImageView m_ivAwardPic4 = (ImageView) findViewById(R.id.ivAwardPic4);
        ImageView m_ivAwardPic5 = (ImageView) findViewById(R.id.ivAwardPic5);
        ImageView m_ivAwardPic6 = (ImageView) findViewById(R.id.ivAwardPic6);

        m_ivAwardPic1.setOnClickListener(this);
        m_ivAwardPic2.setOnClickListener(this);
        m_ivAwardPic3.setOnClickListener(this);
        m_ivAwardPic4.setOnClickListener(this);
        m_ivAwardPic5.setOnClickListener(this);
        m_ivAwardPic6.setOnClickListener(this);

        m_ivReallyOver1 = (ImageView) findViewById(R.id.ivReallyOver1);
        m_ivReallyOver2 = (ImageView) findViewById(R.id.ivReallyOver2);
        m_ivReallyOver3 = (ImageView) findViewById(R.id.ivReallyOver3);
        m_ivReallyOver4 = (ImageView) findViewById(R.id.ivReallyOver4);
        m_ivReallyOver5 = (ImageView) findViewById(R.id.ivReallyOver5);
        m_ivReallyOver6 = (ImageView) findViewById(R.id.ivReallyOver6);

        m_ivFinger = findViewById(R.id.ivFinger);
        handler.postDelayed(taskAnim, 1000);

        long time = System.currentTimeMillis();

        lucky_panel = findViewById(R.id.lucky_panel);

        lucky_panel.setOnGameOverListener(new LuckyMonkeyPanelView.onGameOverlistener() {
            @Override
            public void onGameOver(boolean isOver) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!isFinishing()) {
                            newAlertDialog("恭喜中獎",null).show();
                            m_bStarGame = false;
                        }
                    }
                }, 2000);
            }
        });
    }


    public int removeDuplicates(int[] data) {
        if (data.length == 0)
            return 0;

        int index = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[index] != data[i]) { // 與我不同的值
                index++; // 下一個位置
                data[index] = data[i]; // 放進來
            }
        }
        return index + 1;
    }

    @Override
    public void onClick(View v) {
        int iViewID = v.getId();

        Object obj = v.getTag();
        if (obj != null) {
            iViewID = (Integer) obj;
        }

        if (m_bStarGame) {
            return;
        }

        switch (iViewID) {
            case R.id.ivAwardPic1:
                showGameDialog(0);
                break;
            case R.id.ivAwardPic2:
                showGameDialog(1);
                break;
            case R.id.ivAwardPic3:
                showGameDialog(2);
                break;
            case R.id.ivAwardPic4:
                showGameDialog(3);
                break;
            case R.id.ivAwardPic5:
                showGameDialog(4);
                break;
            case R.id.ivAwardPic6:
                showGameDialog(5);
                break;
        }
    }

    private AlertDialog showGameDialog(final int position) {

        AlertDialog.Builder rateDialog = new AlertDialog.Builder(this);

        rateDialog.setTitle("LotteryGame");

        String strMsg = String.format(Locale.getDefault(), "我要押注%1$s，須扣%2$s點", "XX商品", "100");
        rateDialog.setMessage(strMsg);

        rateDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        rateDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                lucky_panel.startGame();
                m_bStarGame = true;
                final Random random = new Random();
                int time = (int)(Math.random()*(5000-3000+1))+3000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int number = random.nextInt(10);
                        lucky_panel.tryToStop(iNumber[number - 1]);
                    }
                }, time);
            }
        });
        return rateDialog.show();
    }

    private void setTextPoint() {
        m_tvAwardPoint1.setText(jsonArray.optJSONObject(0).optString("txPoint"));
        m_tvAwardPoint2.setText(jsonArray.optJSONObject(1).optString("txPoint"));
        m_tvAwardPoint3.setText(jsonArray.optJSONObject(2).optString("txPoint"));
        m_tvAwardPoint4.setText(jsonArray.optJSONObject(3).optString("txPoint"));
        m_tvAwardPoint5.setText(jsonArray.optJSONObject(4).optString("txPoint"));
        m_tvAwardPoint6.setText(jsonArray.optJSONObject(5).optString("txPoint"));
    }

    private void setPicPoint() {
        for (int i = 0; i < 6; i++) {
            if (i % 2 != 1) {
                switch (jsonArray.optJSONObject(i).optInt("txPoint")) {
                    case 50:
                        view[i].setImageResource(R.drawable.bar_point_1);
                        break;
                    case 100:
                        view[i].setImageResource(R.drawable.bar_point_3);
                        break;
                    case 150:
                        view[i].setImageResource(R.drawable.bar_point_5);
                        break;
                    default:
                        view[i].setImageResource(R.drawable.bar_point_5);
                        break;
                }
            } else {
                switch (jsonArray.optJSONObject(i).optInt("txPoint")) {
                    case 50:
                        view[i].setImageResource(R.drawable.bar_point_2);
                        break;
                    case 100:
                        view[i].setImageResource(R.drawable.bar_point_4);
                        break;
                    case 150:
                        view[i].setImageResource(R.drawable.bar_point_6);
                        break;
                    default:
                        view[i].setImageResource(R.drawable.bar_point_6);
                        break;
                }
            }
        }
    }

    private final Runnable taskAnim = new Runnable() {
        public void run() {
            // TODO Auto-generated method stub
            handler.postDelayed(this, 3000);
            setfingerAnim(m_ivFinger, 5, 1000);
        }
    };


    private void setfingerAnim(View view, float shakeDegrees, long duration) {
        if (view == null) {
            return;
        }
        Animation rotateAnim = new RotateAnimation(-shakeDegrees, shakeDegrees, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        rotateAnim.setDuration(duration / 10);
        rotateAnim.setRepeatMode(Animation.REVERSE);
        rotateAnim.setRepeatCount(5);
        rotateAnim.setInterpolator(new LinearInterpolator());

        AnimationSet smallAnimationSet = new AnimationSet(false);
        smallAnimationSet.addAnimation(rotateAnim);

        view.startAnimation(smallAnimationSet);
    }

    private int getMemberAvailablePoint(JSONArray arData) {
        if (arData == null) {
            return 0;
        }

        int iPoint = 0;
        JSONObject obj;

        for (int i = 0; i < arData.length(); i++) {
            obj = arData.optJSONObject(i);
            iPoint += obj.optInt("points");
        }

        return iPoint;
    }

    protected AlertDialog newAlertDialog(String strMsg, DialogInterface.OnClickListener yeslistener) {
        return new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_menu_info_details)
                .setTitle("LotteryGame")
                .setMessage(strMsg)
                .setPositiveButton("OK", yeslistener)
                .setCancelable(false)
                .create();
    }
}
