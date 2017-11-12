package me.sadraa.detoxiom;


import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
//It is the costum fragment we use for intro activity
public class IntroFragment extends Fragment {
    String description;
    ImageView iv;
    TextView tv;
    Drawable drawable;
//It's a deprecated method for creating new fragments, because of lifecycle problems
    //but for our goal there is no problem and we can use it
    @SuppressLint("ValidFragment")
    public IntroFragment(String description, Drawable drawable  ) {
        this.description = description;
        this.drawable = drawable;
    }

    public IntroFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //set the values that come from intro activity
        iv= view.findViewById(R.id.iv_intro);
        tv = view.findViewById(R.id.tv_intro);
        iv.setImageDrawable(drawable);
        tv.setText(description);
    }
}
