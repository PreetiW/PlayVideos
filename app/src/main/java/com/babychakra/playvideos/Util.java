package com.babychakra.playvideos;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Game on 20-09-17.
 */

public class Util {

    private static final String AUTO_PLAY_VIDEO = "auto_play";

    public static void saveAutoPlayVideo(Context context){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean savedAutoPlay = sharedPreferences.getBoolean(AUTO_PLAY_VIDEO, false);
                SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(AUTO_PLAY_VIDEO, !savedAutoPlay);
        editor.commit();
    }
    public static boolean getAutoPlayVideo(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(AUTO_PLAY_VIDEO, false);
    }

}
