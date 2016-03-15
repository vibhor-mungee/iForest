package com.ioraecological.ifp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManager {
    private static final String PREF_NAME = "IFPSession";
    private static final String KEY_USER_NAME = "userName";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_IS_LOGIN = "isLogin";
    private static final String QUIZ_TAKEN = "isQuizTaken";
    private static final String SCORE = "userScore";
    private SharedPreferences pref;
    private Editor editor;
    private Context context;
    private int PRIVATE_MODE = 0;

    // Constructor
    public SessionManager(Context context) {
        this.context = context;
        pref = this.context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    //retrieve username frome pref
    public String getSavedUserName() {
        return pref.getString(KEY_USER_NAME, "");
    }

    //save user name to SharedPref
    public void setSavedUserName(String userName) {
        editor.putString(KEY_USER_NAME, userName);
        editor.commit();
    }

    public void setSavedPassword(String pass) {
        editor.putString(KEY_PASSWORD, pass);
        editor.commit();
    }

    public boolean isUserLogin() {
        return pref.getBoolean(KEY_IS_LOGIN, false);
    }

    public void setUserLoggedIn(boolean isLogin) {
        editor.putBoolean(KEY_IS_LOGIN, isLogin);
        editor.commit();
    }

    public boolean isQuizTaken() {
        return pref.getBoolean(QUIZ_TAKEN, false);
    }

    public void setQuizTaken(boolean isTaken) {
        editor.putBoolean(QUIZ_TAKEN, isTaken);
        editor.commit();
    }

    public int getScore() {
        return pref.getInt(SCORE, 0);
    }

    public void setScore(int score) {
        editor.putInt(SCORE, score);
        editor.commit();
    }

    public void clearSession() {
        editor.clear();
        editor.commit();
    }
}
