package me.sadraa.detoxiom;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SavedTimeFragment extends Fragment {
    TextView openedTimesTV;
    int openedTimeInt;
    public SavedTimeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_time, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        openedTimesTV = getView().findViewById(R.id.opened_times);
        //we call sharedprefrence for understanding how many time app was opened.
        openedTimeInt = MainActivity.loadOpenedTimes(getContext());
        openedTimesTV.setText("Opened Times:" + openedTimeInt);
    }

}
