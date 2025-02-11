package com.david.giczi.tetris.app.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.david.giczi.tetris.app.MainActivity;
import com.david.giczi.tetris.app.R;
import com.david.giczi.tetris.app.databinding.FragmentStartBinding;
import com.david.giczi.tetris.app.db.Gamer;
import java.util.Arrays;
import java.util.List;

public class StartFragment extends Fragment {

    private FragmentStartBinding binding;
    private LinearLayout title;
    private final List<Gamer> GAMERS_DATA = Arrays.asList(new Gamer("Dave",1000, 300, 19780712),
                                                    new Gamer("Anna", 1500, 400, 19770327));

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentStartBinding.inflate(inflater, container, false);
        this.title = new LinearLayout(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        this.title.setOrientation(LinearLayout.HORIZONTAL);
        this.title.setLayoutParams(params);
        this.title.setGravity(Gravity.CENTER_HORIZONTAL);
        MainActivity.PAGE_NUMBER_VALUE = 0;
        setTitle();
        return binding.getRoot();

    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getGamerDataRowForLandscapeOrientation();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            getGamerDataRowForPortraitOrientation();
        }
    }

    private void setTitle(){
        binding.startPage.removeView(title);
        title.removeAllViews();
        if( getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ){
            TextView result = new TextView(getContext());
            result.setText(R.string.gamer_result);
            result.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));
            result.setTextSize(20f);
            result.setPadding(0,0,60,0);
            TextView gamerName = new TextView(getContext());
            gamerName.setText(R.string.gamer_name);
            gamerName.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));
            gamerName.setTextSize(20f);
            gamerName.setPadding(0,0,60,0);
            TextView credit = new TextView(getContext());
            credit.setText(R.string.gamer_credit);
            credit.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));
            credit.setTextSize(20f);
            credit.setPadding(0,0,60,0);
            TextView date = new TextView(getContext());
            date.setText(R.string.game_date);
            date.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));
            date.setTextSize(20f);
            date.setPadding(0,0,60,0);
            title.addView(result);
            title.addView(gamerName);
            title.addView(credit);
            title.addView(date);
        }
        else if( getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ){
            TextView result = new TextView(getContext());
            result.setText(R.string.gamer_result);
            result.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));
            result.setTextSize(20f);
            result.setPadding(0,0,60,0);
            TextView gamerName = new TextView(getContext());
            gamerName.setText(R.string.gamer_name);
            gamerName.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));
            gamerName.setTextSize(20f);
            gamerName.setPadding(0,0,60,0);
            TextView credit = new TextView(getContext());
            credit.setText(R.string.gamer_credit);
            credit.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));
            credit.setTextSize(20f);
            credit.setPadding(0,0,60,0);
            TextView date = new TextView(getContext());
            date.setText(R.string.game_date);
            date.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));
            date.setTextSize(20f);
            date.setPadding(0,0,60,0);
            TextView score = new TextView(getContext());
            score.setText(R.string.game_score);
            score.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));
            score.setTextSize(20f);
            score.setPadding(0,0,60,0);
            TextView lengthOfGame = new TextView(getContext());
            lengthOfGame.setText(R.string.game_length);
            lengthOfGame.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));
            lengthOfGame.setTextSize(20f);
            lengthOfGame.setPadding(0,0,60,0);
            title.addView(result);
            title.addView(gamerName);
            title.addView(credit);
            title.addView(date);
            title.addView(score);
            title.addView(lengthOfGame);
        }
        binding.startPage.addView(title);
    }

    private void getGamerDataRowForPortraitOrientation(){
       setTitle();

    }

    private void getGamerDataRowForLandscapeOrientation(){
        setTitle();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}