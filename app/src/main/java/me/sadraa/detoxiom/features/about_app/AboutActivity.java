package me.sadraa.detoxiom.features.about_app;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import me.sadraa.detoxiom.R;
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
                .isRTL(true)
                .setImage(R.drawable.about)
                .setDescription("دیتاکسیوم:  \nترک شبکه‌های اجتماعی به کمک بزرگان تاریخ")
                .addWebsite("https://sadraa.me")
                .addTwitter("01sadra")
                .addGitHub("01sadra")
                .addEmail("01sadra@gmail.com")
                .addItem(new Element().setTitle("Version 1.0.0"))
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
