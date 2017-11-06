package com.example.magneto.testingcaller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.provider.Telephony;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.example.magneto.testingcaller.MainActivity.busy;
import static com.example.magneto.testingcaller.MainActivity.s;

/**
 * Created by MAGNETO on 11/5/2017.
 */

public class CallReceiver extends BroadcastReceiver {

    AudioManager am;
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            int m=incomingNumber.length();
            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                Toast.makeText(context, "Ringing State Number is - " + incomingNumber+" "+String.valueOf(m), Toast.LENGTH_SHORT).show();
                am=(AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
                int g=am.getRingerMode();
                if(incomingNumber.equals(s.get(0)) || incomingNumber.equals(s.get(1)) || incomingNumber.equals(s.get(2))) {
                    if(busy==0)
                    if (g == 0 || g == 1) am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                }
                else Toast.makeText(context, "This is not registered", Toast.LENGTH_SHORT).show();
                   // Toast.makeText(context, "First Number is - " + s[0], Toast.LENGTH_SHORT).show();
                    //Toast.makeText(context, "2nd Number is - " + s[1], Toast.LENGTH_SHORT).show();
                    //Toast.makeText(context, "3rd Number is - " + s[2], Toast.LENGTH_SHORT).show();

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
