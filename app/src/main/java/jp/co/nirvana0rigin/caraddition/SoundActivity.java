package jp.co.nirvana0rigin.caraddition;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class SoundActivity extends AppCompatActivity {
    static protected HashMap<String, MediaPlayer> sMap = new HashMap();
    protected Context con;
    protected MediaPlayer mp;
    protected static HashMap sounds;
    private static Object param1 = null;
    private static Bitmap param2 = null;
    private static Bitmap param3 = null;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        con = getApplicationContext();
        mp = null;

        sounds = new HashMap() {{
            put("roop", Integer.valueOf(R.raw.roop));
            put("pinpon", Integer.valueOf(R.raw.pinpon));
            put("bubuu", Integer.valueOf(R.raw.bubuu));
            put("goal", Integer.valueOf(R.raw.goal));
        }};
        setSMap(sounds);
    }

    //MediaPlayer単品作成
    //リスト作成時の部品メソッド
    protected MediaPlayer getMP(Integer resI) {
        int res = resI.intValue();
        return MediaPlayer.create(con, res);
    }

    //MediaPlayerリスト作成
    protected void setSMap(HashMap<String, Integer> nameMap) {
        for (Map.Entry<String, Integer> e : nameMap.entrySet()) {
            sMap.put(e.getKey(), getMP(e.getValue()));
        }
    }

    //特定のMediaPlayerのループ再生
    protected void sMapStartRoop(String name) {
        if (!sMap.get(name).isPlaying()) {
            MediaPlayer mpr = sMap.get(name);
            mpr.setLooping(true);
            mpr.start();
        }
    }

    //特定のMediaPlayerの通常再生
    protected void sMapStart(String name) {
        if (!sMap.get(name).isPlaying()) {
            MediaPlayer mps = sMap.get(name);
            mps.setLooping(false);
            mps.start();
        }
    }

    //特定のMediaPlayerの停止
    protected void sMapStop(String name) {
        if (sMap.get(name).isPlaying()) {
            sMap.get(name).stop();
        }
    }

    //停止しているか判定
    protected boolean sMapStoped(String name) {
        if (!sMap.get(name).isPlaying()) {
            return true;
        } else {
            return false;
        }
    }

    //MediaPlayerリストの消去
    protected void sMapReset() {
        for (Map.Entry<String, MediaPlayer> e : sMap.entrySet()) {
            mp = e.getValue();
            mp.reset();
            mp.release();
            mp = null;
            sMap = null;
        }
    }

    //共通paramのsetter&getter
    protected void setP1(Object o) {
        this.param1 = o;
    }

    protected Object getP1() {
        return param1;
    }

    //共通paramのsetter&getter
    protected void setP2(Bitmap o) {
        this.param2 = o;
    }

    protected Bitmap getP2() {
        return param2;
    }

    //共通paramのsetter&getter
    protected void setP3(Bitmap o) {
        this.param3 = o;
    }

    protected Bitmap getP3() {
        return param3;
    }

}
