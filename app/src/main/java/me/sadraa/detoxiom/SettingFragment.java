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


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {
    ImageView iv1 ,iv2 , iv3;
    boolean isImageFitToScreen;
    Button callAbout, callIntro;
    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        callAbout =getView().findViewById(R.id.about_call);
        callIntro =getView().findViewById(R.id.intro_call);
        iv1 = view.findViewById(R.id.iv_setting_1);
        iv2 = view.findViewById(R.id.iv_setting_2);
        iv3 = view.findViewById(R.id.iv_setting_3);

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });

        callAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);

            }
        });

        callIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IntroActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
    }
}
