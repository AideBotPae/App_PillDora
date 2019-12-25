package com.example.aidebot.Language;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.aidebot.LoginActivity;

import java.util.Locale;

public class SetLanguage {

    Locale myLocale;

    public void setApplicationLanguage(String language, Activity current_activity) {
        myLocale = new Locale(language);
        Resources res = current_activity.getResources();
        DisplayMetrics display = res.getDisplayMetrics();
        Configuration configuration = res.getConfiguration();
        configuration.locale = myLocale;
        res.updateConfiguration(configuration, display);
        Intent refresh = new Intent(current_activity, LoginActivity.class);
        current_activity.startActivity(refresh);
    }
}
