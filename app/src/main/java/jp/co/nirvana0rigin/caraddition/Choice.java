package jp.co.nirvana0rigin.caraddition;
import android.content.Context;
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

public class Choice extends SoundActivity implements View.OnClickListener {

    Context con;
    Resources res;
    Intent goHome;

    //選択ボタン
    ImageView[][] car = new ImageView[2][9];
    Bitmap[][] carImg = new Bitmap[2][9];
    int[][] viewID = new int[2][9];
    int[][] imgID = new int[2][9];
    //戻るボタン
    Button back;
    //選択したくるまを渡す
    Bitmap p0 = getP2();
    Bitmap p1 = getP3();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice);
        res = getResources();
        con = getApplicationContext();
        goHome = new Intent(this, MainActivity.class);

        //くるま選択ボタンリソース設定
        //非選択時はデフォルトになる
        String resIDstr;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 9; j++) {
            	//viewもimgも同名
                resIDstr = "c" + i + "" + j;
                //imgのRを取得
                imgID[i][j] =
                res.getIdentifier(resIDstr, "drawable", getPackageName());
                //viewのRを取得
                viewID[i][j] =
                res.getIdentifier(resIDstr, "id", getPackageName());

                //imgを取得
                carImg[i][j] = BitmapFactory.decodeResource(res, imgID[i][j]);
                //viewにsetOnClickListener
                car[i][j] = (ImageView) findViewById(viewID[i][j]);
                car[i][j].setOnClickListener(this);
            }
        }

        //くるま選択ボタンリソース設定
        back = (Button) findViewById(R.id.back_choice);
        back.setOnClickListener(this);
    }

    //各種ボタンリスナー
    //解答選択後、一時リスナー解除
    @Override
    public void onClick(View v) {
        int clicked = v.getId();
		//もどるボタン
        if (clicked == R.id.back_choice) {
            startActivity(goHome);
            car = null;
            carImg = null;
            finish();
        //くるま選択ボタン
        } else {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 9; j++) {
                    if (clicked == viewID[i][j]) {
                        if (i == 0) {
                            resetImg(i);
                            selectImg(car[i][j]);
                            p0 = carImg[i][j];
                            setP2(p0);
                        } else {
                            resetImg(i);
                            selectImg(car[i][j]);
                            p1 = carImg[i][j];
                            setP3(p1);
                        }
                        break;
                    }
                }
            }
        }
    }

    void resetImg(int i) {
        for (int j = 0; j < 9; j++) {
            car[i][j].setBackgroundColor(Color.rgb(255, 255, 255));
        }
    }

    //難易度ボタン選択時の色変化
    void selectImg(ImageView target) {
        target.setBackgroundColor(Color.rgb(255, 255, 0));
    }

    void setImg(Bitmap b, ImageView v) {
        v.setImageDrawable(null);
        v.setImageBitmap(null);
        v.setImageBitmap(b);
    }

    //バックグラウンドに回った時
    @Override
    protected void onPause() {
        super.onPause();

    }

    //destroy
    protected void onDestroy() {
        super.onDestroy();

    }

    //バックボタンが押された時
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(goHome);
            car = null;
            carImg = null;
            finish();
            return true;
        }
        return false;
    }

}


