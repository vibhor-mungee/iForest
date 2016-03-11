package com.ioraecological.ifp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by vibhormungee on 08/03/16.
 */
public class SessionManager {

    static String SESSION_PREF = "IFPSession";

    public void setPreferences(Context context, String key, String value) {

        SharedPreferences.Editor editor = context.getSharedPreferences(SESSION_PREF, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();

    }

    public String getPreferences(Context context, String key) {

        SharedPreferences prefs = context.getSharedPreferences(SESSION_PREF, Context.MODE_PRIVATE);
        String position = prefs.getString(key, "");
        return position;
    }

}
