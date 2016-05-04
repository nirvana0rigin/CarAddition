package jp.co.nirvana0rigin.caraddition;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.HashMap;

/**
 * Created by user on 2016/04/18.
 */
public class QuesAns extends SoundActivity implements View.OnClickListener {

    Context con;
    Resources res;
    Intent goHome;

    //道路と車
    ImageView road;
    ObjectAnimator roadAnim;
    ImageView you;
    ObjectAnimator youAnim;
    ImageView him;
    ObjectAnimator himAnim;

    //問題と選択肢
    LinearLayout quesAns;
    TextView q;
    Button btnL;
    Button btnR;
    Button btnBack;
    int countQ = 0;
    float maru = 0;
    float batsu = 0;
    static int id = 0;
    ImageView atariHazure;
    Bitmap atari = null;
    Bitmap hazure = null;
    Bitmap docchi = null;

    //タイマーとBGM効果音
    TextView time;
    CountDownTimer cdt;
    static HashMap sounds;
    GenerateQA qa;

    // 難易度は以下をいじる
    //「早く」「幾つ」正解かが勝負
    long timeAll = 30000;
    long timeAllPlus = 1500; //ズレ修正用
    long timeInter = 1000;
    static int difficult = 4;
    static float speed = 24;
    static float youX = 0;
    static float youXX = speed;
    static float himX = 0;
    static float himXX = speed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ques_ans);
        res = getResources();
        con = getApplicationContext();
        goHome = new Intent(this, MainActivity.class);

        //問題と選択肢のLayout
        //結果はLayout差し替え
        quesAns = (LinearLayout) findViewById(R.id.play);
        time = (TextView) findViewById(R.id.time);
        q = (TextView) findViewById(R.id.q);
        btnL = (Button) findViewById(R.id.ans_l);
        btnL.setOnClickListener(this);
        btnR = (Button) findViewById(R.id.ans_r);
        btnR.setOnClickListener(this);
        btnBack = (Button) findViewById(R.id.play_to_home);
        btnBack.setOnClickListener(this);
        atariHazure = (ImageView) findViewById(R.id.ans_image);
        setMyImage(null, atariHazure, R.drawable.docchi);
        atari = BitmapFactory.decodeResource(res, R.drawable.atari);
        hazure = BitmapFactory.decodeResource(res, R.drawable.hazure);

        //車と車の位置初期化
        youX = 0;
        himX = 0;
        you = (ImageView) findViewById(R.id.you);
        him = (ImageView) findViewById(R.id.him);
        yourCar(youX);
        hisCar(himX);

        //BGM効果音生成
        sounds = new HashMap() {{
            put("roop", Integer.valueOf(R.raw.roop));
            put("pinpon", Integer.valueOf(R.raw.pinpon));
            put("bubuu", Integer.valueOf(R.raw.bubuu));
            put("goal", Integer.valueOf(R.raw.goal));
        }};
        setSMap(sounds);
        sMapStartRoop("roop");

        //難易度設定
        Integer dif = (Integer) getP1();
        if (dif != null) {
            difficult = dif.intValue();
        } else {
            difficult = 4;
        }
        speed = 6 * (float) difficult;
        setP1(null);

        //アニメーション時間＝残距離
        //として表示し、結果画面に遷移
        cdt = new CountDownTimer(timeAll, timeInter) {
            String nokori = res.getString(R.string.nokori);
            String m = res.getString(R.string.m);

            @Override
            public void onTick(long millisUntilFinished) {
                time.setText(nokori + Long.toString(millisUntilFinished / 20) + m);
                if (millisUntilFinished % difficult == 0) {
                    hisCar(himX);
                }
            }

            @Override
            public void onFinish() {
                sMapStart("goal");
                finallyView();
            }
        }.start();

        //道路のアニメーションスタート
        road = (ImageView) findViewById(R.id.load);
        //Animation roadPlaying = AnimationUtils.loadAnimation(this, R.anim.road_anim);
        //road.startAnimation(roadPlaying);
        roadAnimStart(timeAll + timeAllPlus);

        //問題の生成
        Intent catchMain = getIntent();
        id = catchMain.getIntExtra("main", R.id.start_add);
        qa = new GenerateQA(id);
        generateQuesAns();
    }

    //各種ボタンリスナー
    //解答選択後、一時リスナー解除
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ans_l:
                btnL.setOnClickListener(null);
                btnR.setOnClickListener(null);
                if (qa.getCollectLR().equals("L")) {
                    setMyImage(docchi, atariHazure, R.drawable.atari);
                    atari();
                } else {
                    setMyImage(docchi, atariHazure, R.drawable.hazure);
                    hazure();
                }
                break;

            case R.id.ans_r:
                btnL.setOnClickListener(null);
                btnR.setOnClickListener(null);
                if (qa.getCollectLR().equals("R")) {
                    setMyImage(docchi, atariHazure, R.drawable.atari);
                    atari();
                } else {
                    setMyImage(docchi, atariHazure, R.drawable.hazure);
                    hazure();
                }
                break;

            case R.id.end_to_home:
            case R.id.play_to_home:
                startActivity(goHome);
                continueCancel();
                break;

            case R.id.close2:
                //sMapReset();
                continueCancel();
                finish();
                break;
        }

    }

    //当たり選択肢の選択時の動作
    void atari() {
        super.sMapStart("pinpon");
        youXX = speed;
        yourCar(youX);
        maru += 1;
        generateQuesAns();
    }

    //ハズレ選択肢の選択時の動作
    void hazure() {
        super.sMapStart("bubuu");
        youXX = -1 * speed;
        yourCar(youX);
        batsu += 1;
    }

    //選択肢選択時の共通後処理動作
    void afterAtariHazure() {
        setMyImage(null, atariHazure, R.drawable.docchi);
        btnL.setOnClickListener(this);
        btnR.setOnClickListener(this);
    }

    //問題と選択肢の都度生成
    void generateQuesAns() {
        qa.generateNextQA();
        String[] lr = qa.getLR();

        btnL.setText(lr[0]);
        btnR.setText(lr[1]);
        q.setText(qa.getQ());
        countQ += 1;
    }

    //問題選択画面を結果画面に変える
    void finallyView() {
        quesAns.removeAllViews();
        getLayoutInflater().inflate(R.layout.end, quesAns);

        TextView rt1 = (TextView) findViewById(R.id.result_text1);
        TextView rt2 = (TextView) findViewById(R.id.result_text2);
        String mon = res.getString(R.string.mon);
        String maruS = String.valueOf((int) maru);
        String batsuS = String.valueOf((int) batsu);
        int tenInt = 100 - ((int) batsu * (100 / (countQ - 1)));
        String tenStr = "【 " + tenInt + " " + res.getString(R.string.ten) + " 】";
        rt1.setText(String.valueOf(countQ - 1) + mon);
        rt2.setText("○" + maruS + "  " + "×" + batsuS + " " + tenStr);

        ImageView ri = (ImageView) findViewById(R.id.result_image);
        Bitmap lose = BitmapFactory.decodeResource(res, R.drawable.lose);
        Bitmap win = BitmapFactory.decodeResource(res, R.drawable.win);
        Bitmap even = BitmapFactory.decodeResource(res, R.drawable.even);
        int[] youCarXY = new int[2];
        int[] hisCarXY = new int[2];
        you.getLocationOnScreen(youCarXY);
        him.getLocationOnScreen(hisCarXY);
        int youCarX = youCarXY[0];
        int hisCarX = hisCarXY[0];

        //float resultYou = maru - batsu;
        //float resultHim = ((timeAll / 1000) / difficult);
        //float resultFlag = resultYou - resultHim;
        int resultFlag = youCarX - hisCarX;
        if (resultFlag < 0) {
            setMyImage(null, ri, R.drawable.lose);
        } else if (resultFlag > 0) {
            setMyImage(null, ri, R.drawable.win);
        } else {
            setMyImage(null, ri, R.drawable.even);
        }

        Button re = (Button) findViewById(R.id.end_to_home);
        re.setOnClickListener(this);

        Button cl = (Button) findViewById(R.id.close2);
        cl.setOnClickListener(this);
    }

    //第一引数は、直前のBitmap#recycle用
    void setMyImage(Bitmap b, ImageView v, int resId) {
        if (b != null) {
            b.recycle();
        }
        b = BitmapFactory.decodeResource(res, resId);
        v.setImageDrawable(null);
        v.setImageBitmap(null);
        v.setImageBitmap(b);
    }

    //正解不正解時の自分車のアニメ
    void yourCar(float yX) {
        youAnim = ObjectAnimator.ofFloat(you, "translationX", yX, yX + youXX);
        youX = yX + youXX;
        youAnim.setDuration(500);
        youAnim.start();
        afterAtariHazure();
    }

    //一定時間で進む対戦者のアニメ
    void hisCar(float hX) {
        himAnim = ObjectAnimator.ofFloat(him, "translationX", hX, hX + himXX);
        himX = hX + himXX;
        himAnim.setDuration(500);
        himAnim.start();
    }

    //一定時間内の道路のアニメ
    void roadAnimStart(long time) {
        roadAnim = ObjectAnimator.ofFloat(road, "translationX", 0, -28000);
        roadAnim.setDuration(time);
        roadAnim.setInterpolator(new LinearInterpolator());
        roadAnim.start();
    }

    //バックグラウンドに回った時
    @Override
    protected void onPause() {
        super.onPause();
        continueCancel();
    }

    //destroy
    protected void onDestroy() {
        super.onDestroy();
        continueCancel();
        sMapReset();
    }

    //バックボタンが押された時
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            continueCancel();
            return true;
        }
        return false;
    }

    //各種disableメソッド用
    void continueCancel() {
        sMapStop("roop");
        cdt.cancel();
        roadAnim.cancel();
        //finish();
    }

}