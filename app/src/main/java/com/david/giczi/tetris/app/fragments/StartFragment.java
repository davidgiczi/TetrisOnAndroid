package com.david.giczi.tetris.app.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.TypedValue;
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
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class StartFragment extends Fragment {

    private FragmentStartBinding binding;
    private LinearLayout title;
    private LinearLayout gamersData;
    private LinearLayout.LayoutParams PARAMS;
    private final List<Gamer> GAMERS_DATA = Arrays.asList(new Gamer("Dave",1000, 30, 1978071200),
                                                    new Gamer("Anna", 1800, 40, 1977032700),
                                                    new Gamer("Dalma", 800, 10, 2010011200),
                                                    new Gamer("Anonymus", 3500, 25, 2000010100));

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentStartBinding.inflate(inflater, container, false);
        this.title = new LinearLayout(getContext());
        this.gamersData = new LinearLayout(getContext());
        PARAMS = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        this.title.setOrientation(LinearLayout.HORIZONTAL);
        this.title.setLayoutParams(PARAMS);
        this.title.setGravity(Gravity.CENTER_HORIZONTAL);
        this.gamersData.setOrientation(LinearLayout.VERTICAL);
        this.gamersData.setLayoutParams(PARAMS);
        this.gamersData.setGravity(Gravity.CENTER_HORIZONTAL);
        MainActivity.PAGE_NUMBER_VALUE = 0;
        getGamerDataRowForPortraitOrientation();
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
        binding.startPage.removeView(gamersData);
        title.removeAllViews();
        gamersData.removeAllViews();
        if( getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ){
            TextView result = new TextView(getContext());
            result.setText(R.string.gamer_result);
            result.setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
            result.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
            result.setPadding(0,0,80,0);
            TextView gamerName = new TextView(getContext());
            gamerName.setText(R.string.gamer_name);
            gamerName.setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
            gamerName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
            gamerName.setPadding(0,0,80,0);
            TextView credit = new TextView(getContext());
            credit.setText(R.string.gamer_credit);
            credit.setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
            credit.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
            credit.setPadding(0,0,80,0);
            TextView date = new TextView(getContext());
            date.setText(R.string.game_date);
            date.setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
            date.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
            date.setPadding(0,0,80,0);
            title.addView(result);
            title.addView(gamerName);
            title.addView(credit);
            title.addView(date);
            gamersData.addView(title);
        }
        else if( getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ){
            TextView result = new TextView(getContext());
            result.setText(R.string.gamer_result);
            result.setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
            result.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
            result.setPadding(0,0,80,0);
            TextView gamerName = new TextView(getContext());
            gamerName.setText(R.string.gamer_name);
            gamerName.setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
            gamerName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
            gamerName.setPadding(0,0,80,0);
            TextView credit = new TextView(getContext());
            credit.setText(R.string.gamer_credit);
            credit.setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
            credit.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
            credit.setPadding(0,0,80,0);
            TextView date = new TextView(getContext());
            date.setText(R.string.game_date);
            date.setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
            date.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
            date.setPadding(0,0,80,0);
            TextView score = new TextView(getContext());
            score.setText(R.string.game_score);
            score.setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
            score.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
            score.setPadding(0,0,80,0);
            TextView lengthOfGame = new TextView(getContext());
            lengthOfGame.setText(R.string.game_length);
            lengthOfGame.setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
            lengthOfGame.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
            lengthOfGame.setPadding(0,0,10,0);
            TextView min = new TextView(getContext());
            min.setText(R.string.game_min);
            min.setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
            min.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            min.setPadding(0,0,80,0);
            title.addView(result);
            title.addView(gamerName);
            title.addView(credit);
            title.addView(date);
            title.addView(score);
            title.addView(lengthOfGame);
            title.addView(min);
            gamersData.addView(title);
        }
    }

    private void getGamerDataRowForPortraitOrientation(){
       setTitle();
        Collections.sort(GAMERS_DATA);
        for (Gamer gamer: GAMERS_DATA) {
            LinearLayout gamerData = new LinearLayout(getContext());
            gamerData.setOrientation(LinearLayout.HORIZONTAL);
            gamerData.setLayoutParams(PARAMS);
            gamerData.setGravity(Gravity.CENTER_HORIZONTAL);
            TextView result = new TextView(getContext());
            String resultValue = (GAMERS_DATA.indexOf(gamer) + 1) + ".";
            result.setText(resultValue);
            result.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            result.setPadding(0,0,10,0);
            TextView gamerName = new TextView(getContext());
            gamerName.setText(gamer.getName());
            gamerName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            gamerName.setPadding(0,0,10,0);
            TextView credit = new TextView(getContext());
            credit.setText(String.format(Locale.getDefault(), "%d", gamer.getCredit()));
            credit.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            credit.setPadding(0,0,10,0);
            TextView date = new TextView(getContext());
            date.setText(gamer.getDate());
            date.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            date.setPadding(0,0,10,0);
            switch ( GAMERS_DATA.indexOf(gamer) ){
                case 0 :
                    result.setTextColor(ContextCompat.getColor(requireContext(), R.color.yellow));
                    gamerName.setTextColor(ContextCompat.getColor(requireContext(), R.color.yellow));
                    credit.setTextColor(ContextCompat.getColor(requireContext(), R.color.yellow));
                    date.setTextColor(ContextCompat.getColor(requireContext(), R.color.yellow));
                    break;
                case 1 :
                    result.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange));
                    gamerName.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange));
                    credit.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange));
                    date.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange));
                    break;
                case 2 :
                    result.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));
                    gamerName.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));
                    credit.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));
                    date.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));
                    break;
                default:
                    result.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue));
                    gamerName.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue));
                    credit.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue));
                    date.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue));
                    break;
            }
            gamerData.addView(result);
            gamerData.addView(gamerName);
            gamerData.addView(credit);
            gamerData.addView(date);
            gamersData.addView(gamerData);
        }
        binding.startPage.addView(gamersData);
    }

    private void getGamerDataRowForLandscapeOrientation(){
        setTitle();
        binding.startPage.addView(gamersData);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}