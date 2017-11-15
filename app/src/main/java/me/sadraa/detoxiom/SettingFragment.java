package me.sadraa.detoxiom;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {
    boolean isImageFitToScreen;
    private Unbinder unbinder;
    @BindView(R.id.iv_setting_1)ImageView iv1;
    @BindView(R.id.iv_setting_2)ImageView iv2;
    @BindView(R.id.iv_setting_3)ImageView iv3;
    @BindView(R.id.about_call) Button callAbout;
    @BindView(R.id.intro_call) Button callIntro;

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    //Adding a feature that when you click on a imageview the image view become full screen.
    @OnClick(R.id.iv_setting_1)
    public void makeIV1fullScreen(){
        if(isImageFitToScreen) {
            isImageFitToScreen=false;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(300,210);
            layoutParams.gravity= Gravity.CENTER;
            iv1.setLayoutParams(layoutParams);
            iv1.setAdjustViewBounds(true);

        }else{
            isImageFitToScreen=true;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(500,400);
            iv1.setLayoutParams(layoutParams);
            iv1.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
    }
    @OnClick(R.id.iv_setting_2)
    public void makeIV2fullScreen(){
        if(isImageFitToScreen) {
            isImageFitToScreen=false;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(300,210);
            layoutParams.gravity= Gravity.CENTER;
            iv2.setLayoutParams(layoutParams);
            iv2.setAdjustViewBounds(true);
        }else{
            isImageFitToScreen=true;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(500,400);
            iv2.setLayoutParams(layoutParams);
            iv2.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
    }
    @OnClick(R.id.iv_setting_3)
    public void makeIV3fullScreen(){
        if(isImageFitToScreen) {
            isImageFitToScreen=false;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(300,210);
            layoutParams.gravity= Gravity.CENTER;
            iv3.setLayoutParams(layoutParams);
            iv3.setAdjustViewBounds(true);
        }else{
            isImageFitToScreen=true;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(500,400);
            iv3.setLayoutParams(layoutParams);
            iv3.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
    }
    @OnClick(R.id.about_call)
    public void callAboutActivity(){
        Intent intent = new Intent(getActivity(), AboutActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.intro_call)
    public void callIntroActivty(){
        Intent intent = new Intent(getActivity(), IntroActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }
}
