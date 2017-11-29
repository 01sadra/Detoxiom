package me.sadraa.detoxiom.features.about_app;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.sadraa.detoxiom.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeachingFragment extends Fragment {
    boolean isImageFitToScreen;
    private Unbinder unbinder;

    @BindView(R.id.about_call) Button callAbout;
    @BindView(R.id.intro_call) Button callIntro;

    public TeachingFragment() {
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
