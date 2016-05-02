package jp.co.nirvana0rigin.caraddition;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by user on 2016/04/25.
 */
public class MyCountDownTimer extends CountDownTimer {

    TextView time = new TextView(null);

    public MyCountDownTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onFinish() {

    }

    @Override
    public void onTick(long millisUntilFinished) {
        time.setText("のこり" + Long.toString(millisUntilFinished / 25) + "m");
        if (millisUntilFinished % 3 == 0) {

        }
    }
}








