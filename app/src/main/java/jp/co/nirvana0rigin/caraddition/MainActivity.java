package jp.co.nirvana0rigin.caraddition;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends SoundActivity implements View.OnClickListener {

    Resources res;
    Button hard;
    Button normal;
    Button easy;
    Intent goQuesAns;
    Intent goChoice;
    Intent goLauncher;
    Bitmap p0 = getP2();
    Bitmap p1 = getP3();
    ImageView you;
    ImageView him;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res = getResources();

        //くるま画像の設定
        you = (ImageView) findViewById(R.id.you);
        him = (ImageView) findViewById(R.id.him);
        changeCar(you, him);

        //各種ボタンの設定
        Button btnA1 = (Button) findViewById(R.id.start_add);
        btnA1.setOnClickListener(this);
        Button btnA2 = (Button) findViewById(R.id.start_add2);
        btnA2.setOnClickListener(this);
        Button btnM1 = (Button) findViewById(R.id.start_min);
        btnM1.setOnClickListener(this);
        Button btnM2 = (Button) findViewById(R.id.start_min2);
        btnM2.setOnClickListener(this);
        Button btnS = (Button) findViewById(R.id.start_conf);
        btnS.setOnClickListener(this);

        hard = (Button) findViewById(R.id.hard);
        hard.setOnClickListener(this);
        normal = (Button) findViewById(R.id.normal);
        normal.setOnClickListener(this);
        easy = (Button) findViewById(R.id.easy);
        easy.setOnClickListener(this);

        //インテント生成
        goQuesAns = new Intent(this, QuesAns.class);
        goChoice = new Intent(this, Choice.class);
        goLauncher = new Intent(Intent.ACTION_MAIN);
        goLauncher.addCategory(Intent.CATEGORY_HOME);
        goLauncher.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //難易度ボタンの色を皆初期化
        setSelectorColor(hard, normal, easy);
    }

    @Override
    protected void onResume() {
        super.onResume();
        changeCar(you, him);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        goQuesAns.putExtra("main", id);
        setSelectorColor(hard, normal, easy);

        switch (id) {

            case R.id.start_add:
            case R.id.start_add2:
            case R.id.start_min:
            case R.id.start_min2:
                startActivity(goQuesAns);
                //finish();
                break;

            case R.id.start_conf:
                startActivity(goChoice);
                //finish();
                break;

            case R.id.hard:
                setP1(new Integer(2));
                selectButton(hard);
                break;

            case R.id.normal:
                setP1(new Integer(4));
                selectButton(normal);
                break;

            case R.id.easy:
                setP1(new Integer(6));
                selectButton(easy);
                break;
        }
    }

    //難易度ボタンの色を皆初期化
    void setSelectorColor(Button a, Button b, Button c) {
        a.setBackgroundColor(Color.rgb(196, 196, 196));
        b.setBackgroundColor(Color.rgb(196, 196, 196));
        c.setBackgroundColor(Color.rgb(196, 196, 196));
    }

    //難易度ボタン選択時の色変化
    void selectButton(Button target) {
        target.setBackgroundColor(Color.rgb(255, 255, 0));
    }

    void changeCar(ImageView you, ImageView him){
        if (p0 != null) {
            you.setImageBitmap(p0);
        }
        if (p1 != null) {
            him.setImageBitmap(p1);
        }
    }

    //バックボタンが押された時
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(goLauncher);
            //finish();
            return true;
        }
        return false;
    }

}
