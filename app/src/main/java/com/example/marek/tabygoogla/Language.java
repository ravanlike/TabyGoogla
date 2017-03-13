package com.example.marek.tabygoogla;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by Marek on 14/12/2016.
 */

//select radio button depending on settings
//dynamic action bar label change

public class Language extends AppCompatActivity {



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language_main);



        RadioGroup rGroup = (RadioGroup)findViewById(R.id.radio_languages);
        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {

            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                Locale locale;
                switch (checkedId) {
                    case R.id.radio_english:
                        locale = new Locale("en");

                        break;


                    case R.id.radio_swedish:
                        locale = new Locale("sv");

                        break;

                    case R.id.radio_polish:
                        locale = new Locale("pl");

                        break;

                    case R.id.radio_arabic:
                        locale = new Locale("ar");

                        break;

                    default:
                        locale = new Locale("en");

                }

                Configuration overrideConfiguration = getBaseContext().getResources().getConfiguration();
                overrideConfiguration.setLocale(locale);
                Context context = createConfigurationContext(overrideConfiguration);
                Resources resources = context.getResources();



            }

        });
        setTitle(getString(R.string.label_language))
        ;

    }



}
