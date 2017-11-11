package me.sadraa.detoxiom;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.mAppTheme);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        View aboutPage = new AboutPage(this)
                .setImage(R.mipmap.ic_launcher)
                .setDescription("دیتاکسیوم:  \nترک شبکه‌های اجتماعی به کمک بزرگان تاریخ")
                .addWebsite("https://sadraa.me")
                .addTwitter("01sadra")
                .addGitHub("01sadra")
                .addEmail("01sadra@gmail.com")
                .addItem(new Element().setTitle("Version 1.2"))
                .create();


        setContentView(aboutPage);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }



}
