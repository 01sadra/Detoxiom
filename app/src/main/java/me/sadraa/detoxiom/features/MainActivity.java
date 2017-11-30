package me.sadraa.detoxiom.features;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.ronash.pushe.Pushe;
import me.sadraa.detoxiom.MyApplication;
import me.sadraa.detoxiom.R;
import me.sadraa.detoxiom.data.SharedprefrenceProvider;
import me.sadraa.detoxiom.features.about_app.IntroActivity;
import me.sadraa.detoxiom.features.archive_quotes.ArchiveFragment;
import me.sadraa.detoxiom.features.get_new_quote.NewQuoteFragment;
import me.sadraa.detoxiom.features.teaching.TeachingFragment;
import me.sadraa.detoxiom.features.time_save.SavedTimeFragment;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends AppCompatActivity {

    FragmentTransaction ft;
    int openedTimes;
    int badgeCount;
    @Nullable @BindView(R.id.toolbar_main) Toolbar mToolbar;
    @Nullable @BindView(R.id.bottomBar) BottomBar bottomBar;
    @Nullable @BindView(R.id.tab_new) BottomBarTab tabNew;
    //injecting the dependency using dagger an application class
    SharedprefrenceProvider sharedprefrenceProvider = MyApplication.getAppComponent().getSharedPrefrenceProvider();
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_main);
        Pushe.initialize(this,true);
        //binding the butterknife
        ButterKnife.bind(this);

        //everyTime onCreate fire we +1 shared prefrences counter
        openedTimes = sharedprefrenceProvider.loadOpenedTimes();
        sharedprefrenceProvider.saveOpenedTimes(++openedTimes);
        //Get a random number between 1 and 5 for letting user try they chance
        //save it as a prefrence to new quote fragment can use it
        badgeCount=getMeRandomNumber();
        sharedprefrenceProvider.saveBadgeCounter(badgeCount);

        //If it's the first time user open the app, show him the intro.
        if(openedTimes<2){
            Intent nIntent = new Intent(MainActivity.this,IntroActivity.class);
            startActivity(nIntent);
        }

        //Set toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("دیتاکسیوم");
        //make the actionbar right to left
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        getSupportActionBar().setElevation(8);


        //force the bottomBar use left to right direction.
        bottomBar.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        //set notification badge on new quote tab base on badge counter
        tabNew.setBadgeCount(sharedprefrenceProvider.loadBadgeCount());
        //if It's the first time user open the app show tap target view for teaching the user how use the app
        if(openedTimes<3){
            TapTargetView.showFor(this, TapTarget.forView(findViewById(R.id.tab_setting) , "آموزش", "روی این تب کلیک کنید تا نحوه‌ی اضافه کردن ویجت به صفحه رو ببینید")  // All options below are optional
                    .outerCircleColor(R.color.primary_dark)      // Specify a color for the outer circle
                    .outerCircleAlpha(0.96f)            // Specify the alpha amount for the outer circle
                    .targetCircleColor(R.color.low_primary)   // Specify a color for the target circle
                    .titleTextSize(40)                  // Specify the size (in sp) of the title text
                    .descriptionTextSize(30)            // Specify the size (in sp) of the description text
                    .dimColor(R.color.bb_tabletRightBorderDark)            // If set, will dim behind the view with 30% opacity of the given color
                    .drawShadow(true)                   // Whether to draw a drop shadow or not
                    .tintTarget(true)                   // Whether to tint the target view's color
                    .transparentTarget(false)           // Specify whether the target is transparent (displays the content underneath)
                    .targetRadius(60));
        }
        //Set click listener for bottombar tabs
        bottomBar.setOnTabSelectListener(tabId -> {
          //using switch to woke fragments up
            switch (tabId){
                case R.id.tab_archive :
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.contentContainer,new ArchiveFragment());
                    break;
                case R.id.tab_time:
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.contentContainer,new SavedTimeFragment());
                    break;
                case R.id.tab_new :
                    ft = getSupportFragmentManager().beginTransaction();
                    //if user click on tabnew remove the badges
                    tabNew.removeBadge();
                    ft.replace(R.id.contentContainer,new NewQuoteFragment());
                    break;
                case R.id.tab_setting :
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.contentContainer,new TeachingFragment());
               //     tabSetting.removeBadge();
                    break;
            }
            ft.commit();
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }



    public int getMeRandomNumber(){
        return MyApplication.getAppComponent().getRandom().nextInt(5)+1;
    }
    //Just following the matrial design guide
    @Override
    public void onBackPressed() {
        finish();
    }
}

