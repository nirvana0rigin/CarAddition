package jp.co.nirvana0rigin.caraddition;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class SoundActivity extends AppCompatActivity {
    static protected HashMap<String, MediaPlayer> sMap = new HashMap();
<<<<<<< HEAD

=======
    ;
>>>>>>> origin/master
    protected Context con;
    protected MediaPlayer mp;
    private static Object param1 = null;
    private static Object param2 = null;
    private static Object param3 = null;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        con = getApplicationContext();

        mp = null;
    }

<<<<<<< HEAD
    //MediaPlayer単品作成
    //リスト作成時の部品メソッド
=======

>>>>>>> origin/master
    protected MediaPlayer getMP(Integer resI) {
        int res = resI.intValue();
        return MediaPlayer.create(con, res);
    }

<<<<<<< HEAD
    //MediaPlayerリスト作成
=======
>>>>>>> origin/master
    protected void setSMap(HashMap<String, Integer> nameMap) {
        for (Map.Entry<String, Integer> e : nameMap.entrySet()) {
            sMap.put(e.getKey(), getMP(e.getValue()));
        }
    }

<<<<<<< HEAD
    //特定のMediaPlayerのループ再生
=======
>>>>>>> origin/master
    protected void sMapStartRoop(String name) {
        if (!sMap.get(name).isPlaying()) {
            MediaPlayer mpr = sMap.get(name);
            mpr.setLooping(true);
            mpr.start();
        }
    }

<<<<<<< HEAD
    //特定のMediaPlayerの通常再生
=======
>>>>>>> origin/master
    protected void sMapStart(String name) {
        if (!sMap.get(name).isPlaying()) {
            MediaPlayer mps = sMap.get(name);
            mps.setLooping(false);
            mps.start();
        }
    }

<<<<<<< HEAD
    //特定のMediaPlayerの停止
=======
>>>>>>> origin/master
    protected void sMapStop(String name) {
        if (sMap.get(name).isPlaying()) {
            sMap.get(name).stop();
        }
    }

<<<<<<< HEAD
    //停止しているか判定
=======
>>>>>>> origin/master
    protected boolean sMapStoped(String name) {
        if (!sMap.get(name).isPlaying()) {
            return true;
        } else {
            return false;
        }
    }

<<<<<<< HEAD
    //MediaPlayerリストの消去
=======
>>>>>>> origin/master
    protected void sMapReset() {
        for (Map.Entry<String, MediaPlayer> e : sMap.entrySet()) {
            mp = e.getValue();
            mp.reset();
            mp.release();
            mp = null;
            sMap = null;
        }
    }

<<<<<<< HEAD
    //共通paramのsetter&getter
=======
>>>>>>> origin/master
    protected void setP1(Object o) {
        this.param1 = o;
    }

    protected Object getP1() {
        return param1;
    }

<<<<<<< HEAD
    //共通paramのsetter&getter
=======
>>>>>>> origin/master
    protected void setP2(Object o) {
        this.param2 = o;
    }

    protected Object getP2() {
        return param2;
    }

<<<<<<< HEAD
    //共通paramのsetter&getter
=======
>>>>>>> origin/master
    protected void setP3(Object o) {
        this.param3 = o;
    }

    protected Object getP3() {
        return param3;
    }

<<<<<<< HEAD
}
=======
}
>>>>>>> origin/master
