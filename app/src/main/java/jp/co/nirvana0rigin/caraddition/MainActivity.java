package jp.co.nirvana0rigin.caraddition;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends SoundActivity implements View.OnClickListener {

    Button hard;
    Button normal;
    Button easy;
    Intent goQuesAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        Button btnC = (Button) findViewById(R.id.close);
        btnS.setOnClickListener(this);
        hard = (Button) findViewById(R.id.hard);
        hard.setOnClickListener(this);
        normal = (Button) findViewById(R.id.normal);
        normal.setOnClickListener(this);
        easy = (Button) findViewById(R.id.easy);
        easy.setOnClickListener(this);

        //インテント生成
        goQuesAns = new Intent(this, QuesAns.class);

        //難易度ボタンの色を皆初期化
        setSelectorColor(hard, normal, easy);
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
                finish();
                break;

            case R.id.start_conf:
                //startActivity(goConfig);
                Toast.makeText(this, R.string.start_toast, Toast.LENGTH_SHORT).show();
                break;

            case R.id.close:
                sMapReset();
                //moveTaskToBack(true);
                this.finish();
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

}
