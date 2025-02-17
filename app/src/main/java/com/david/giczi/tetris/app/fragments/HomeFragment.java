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
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.david.giczi.tetris.app.MainActivity;
import com.david.giczi.tetris.app.R;
import com.david.giczi.tetris.app.databinding.FragmentHomeBinding;
import com.david.giczi.tetris.app.db.Gamer;
import com.david.giczi.tetris.app.db.GamerService;

import java.util.Collections;
import java.util.Locale;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private LinearLayout.LayoutParams PARAMS;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        PARAMS = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        MainActivity.PAGE_NUMBER_VALUE = 0;
        if( ((MainActivity) requireActivity()).menu != null ){
            ((MainActivity) requireActivity()).menu.findItem(R.id.game_start).setEnabled(true);
            ((TextView)  requireActivity().findViewById(R.id.game_info_title)).setText("");
        }
        GamerService.getAllGamers(getContext());
        setGamersDataForPortraitOrientation();
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
            setGamersDataForLandscapeOrientation();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setGamersDataForPortraitOrientation();
        }
    }

    private void setGamersDataForPortraitOrientation(){
        binding.startPage.removeAllViews();
        LinearLayout gamersData = new LinearLayout(getContext());
        gamersData.setLayoutParams(PARAMS);
        gamersData.setOrientation(LinearLayout.HORIZONTAL);
        gamersData.setGravity(Gravity.CENTER_HORIZONTAL);
            LinearLayout resultCol = new LinearLayout(getContext());
            resultCol.setOrientation(LinearLayout.VERTICAL);
            resultCol.setGravity(Gravity.CENTER_HORIZONTAL);
            TextView resultTitle = new TextView(getContext());
            resultTitle.setText(R.string.gamer_result);
            resultTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
            resultTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
            resultCol.addView(resultTitle);
            LinearLayout nameCol = new LinearLayout(getContext());
            nameCol.setOrientation(LinearLayout.VERTICAL);
            nameCol.setGravity(Gravity.CENTER_HORIZONTAL);
            TextView gamerNameTitle = new TextView(getContext());
            gamerNameTitle.setText(R.string.gamer_name);
            gamerNameTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
            gamerNameTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
            gamerNameTitle.setPadding(80,0,0,0);
            nameCol.addView(gamerNameTitle);
            LinearLayout creditCol = new LinearLayout(getContext());
            creditCol.setOrientation(LinearLayout.VERTICAL);
            creditCol.setGravity(Gravity.CENTER_HORIZONTAL);
            TextView creditTitle = new TextView(getContext());
            creditTitle.setText(R.string.gamer_credit);
            creditTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
            creditTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
            creditCol.addView(creditTitle);
            LinearLayout dateCol = new LinearLayout(getContext());
            dateCol.setOrientation(LinearLayout.VERTICAL);
            dateCol.setGravity(Gravity.CENTER_HORIZONTAL);
            TextView dateTitle = new TextView(getContext());
            dateTitle.setText(R.string.game_date);
            dateTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
            dateTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
            dateTitle.setPadding(80, 0, 0, 0);
            dateCol.addView(dateTitle);
            Collections.sort(GamerService.GAMERS);
            for (Gamer gamer: GamerService.GAMERS) {
                TextView resultData = new TextView(getContext());
                resultData.setGravity(Gravity.CENTER_HORIZONTAL);
                String resultValue = (GamerService.GAMERS.indexOf(gamer) + 1) + ".";
                resultData.setText(resultValue);
                resultData.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                TextView gamerNameData = new TextView(getContext());
                gamerNameData.setOnClickListener(n -> deleteGamerDialog(gamer.getName()));
                gamerNameData.setGravity(Gravity.CENTER_HORIZONTAL);
                gamerNameData.setText(gamer.getName());
                gamerNameData.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                TextView creditData = new TextView(getContext());
                creditData.setGravity(Gravity.CENTER_HORIZONTAL);
                creditData.setText(String.format(Locale.getDefault(), "%d", gamer.getCredit()));
                creditData.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                TextView dateData = new TextView(getContext());
                dateData.setGravity(Gravity.CENTER_HORIZONTAL);
                dateData.setText(gamer.getDate());
                dateData.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                switch (GamerService.GAMERS.indexOf(gamer)) {
                    case 0:
                        resultData.setTextColor(ContextCompat.getColor(requireContext(), R.color.yellow));
                        gamerNameData.setTextColor(ContextCompat.getColor(requireContext(), R.color.yellow));
                        creditData.setTextColor(ContextCompat.getColor(requireContext(), R.color.yellow));
                        dateData.setTextColor(ContextCompat.getColor(requireContext(), R.color.yellow));
                        break;
                    case 1:
                        resultData.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange));
                        gamerNameData.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange));
                        creditData.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange));
                        dateData.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange));
                        break;
                    case 2:
                        resultData.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));
                        gamerNameData.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));
                        creditData.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));
                        dateData.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));
                        break;
                    default:
                        resultData.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue));
                        gamerNameData.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue));
                        creditData.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue));
                        dateData.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue));
                        break;
                }
                resultCol.addView(resultData);
                nameCol.addView(gamerNameData);
                creditCol.addView(creditData);
                dateCol.addView(dateData);
            }
            gamersData.addView(resultCol);
            gamersData.addView(nameCol);
            gamersData.addView(creditCol);
            gamersData.addView(dateCol);
            binding.startPage.addView(gamersData);
    }


    private void setGamersDataForLandscapeOrientation() {
        binding.startPage.removeAllViews();
        LinearLayout gamersData = new LinearLayout(getContext());
        gamersData.setLayoutParams(PARAMS);
        gamersData.setOrientation(LinearLayout.HORIZONTAL);
        gamersData.setGravity(Gravity.CENTER_HORIZONTAL);
        LinearLayout resultCol = new LinearLayout(getContext());
        resultCol.setOrientation(LinearLayout.VERTICAL);
        resultCol.setGravity(Gravity.CENTER_HORIZONTAL);
        TextView resultTitle = new TextView(getContext());
        resultTitle.setText(R.string.gamer_result);
        resultTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
        resultTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
        resultCol.addView(resultTitle);
        LinearLayout nameCol = new LinearLayout(getContext());
        nameCol.setOrientation(LinearLayout.VERTICAL);
        nameCol.setGravity(Gravity.CENTER_HORIZONTAL);
        TextView gamerNameTitle = new TextView(getContext());
        gamerNameTitle.setText(R.string.gamer_name);
        gamerNameTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
        gamerNameTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
        gamerNameTitle.setPadding(100, 0, 0, 0);
        nameCol.addView(gamerNameTitle);
        LinearLayout creditCol = new LinearLayout(getContext());
        creditCol.setOrientation(LinearLayout.VERTICAL);
        creditCol.setGravity(Gravity.CENTER_HORIZONTAL);
        TextView creditTitle = new TextView(getContext());
        creditTitle.setText(R.string.gamer_credit);
        creditTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
        creditTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
        creditTitle.setPadding(40, 0, 0, 0);
        creditCol.addView(creditTitle);
        LinearLayout dateCol = new LinearLayout(getContext());
        dateCol.setOrientation(LinearLayout.VERTICAL);
        dateCol.setGravity(Gravity.CENTER_HORIZONTAL);
        TextView dateTitle = new TextView(getContext());
        dateTitle.setText(R.string.game_date);
        dateTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
        dateTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
        dateTitle.setPadding(100, 0, 0, 0);
        dateCol.addView(dateTitle);
        LinearLayout scoreCol = new LinearLayout(getContext());
        scoreCol.setOrientation(LinearLayout.VERTICAL);
        scoreCol.setGravity(Gravity.CENTER_HORIZONTAL);
        TextView scoreTitle = new TextView(getContext());
        scoreTitle.setText(R.string.game_score);
        scoreTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
        scoreTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
        scoreTitle.setPadding(40, 0, 0, 0);
        scoreCol.addView(scoreTitle);
        LinearLayout durationCol = new LinearLayout(getContext());
        durationCol.setOrientation(LinearLayout.VERTICAL);
        durationCol.setGravity(Gravity.CENTER_HORIZONTAL);
        TextView durationTitle = new TextView(getContext());
        durationTitle.setText(R.string.game_length);
        durationTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
        durationTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
        durationTitle.setPadding(80, 0, 0, 0);
        durationCol.addView(durationTitle);
        Collections.sort(GamerService.GAMERS);
        for (Gamer gamer : GamerService.GAMERS) {
            TextView resultData = new TextView(getContext());
            resultData.setGravity(Gravity.CENTER_HORIZONTAL);
            String resultValue = (GamerService.GAMERS.indexOf(gamer) + 1) + ".";
            resultData.setText(resultValue);
            resultData.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            TextView gamerNameData = new TextView(getContext());
            gamerNameData.setOnClickListener(n -> deleteGamerDialog(gamer.getName()));
            gamerNameData.setGravity(Gravity.CENTER_HORIZONTAL);
            gamerNameData.setText(gamer.getName());
            gamerNameData.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            TextView creditData = new TextView(getContext());
            creditData.setGravity(Gravity.CENTER_HORIZONTAL);
            creditData.setText(String.format(Locale.getDefault(), "%d", gamer.getCredit()));
            creditData.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            TextView dateData = new TextView(getContext());
            dateData.setGravity(Gravity.CENTER_HORIZONTAL);
            dateData.setText(gamer.getDate());
            dateData.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            TextView scoreData = new TextView(getContext());
            scoreData.setGravity(Gravity.CENTER_HORIZONTAL);
            scoreData.setText(String.format(Locale.getDefault(), "%d", gamer.getScore()));
            scoreData.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            TextView durationData = new TextView(getContext());
            durationData.setGravity(Gravity.CENTER_HORIZONTAL);
            durationData.setText(String.format(Locale.getDefault(), "%d", gamer.getDuration()));
            durationData.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            switch (GamerService.GAMERS.indexOf(gamer)) {
                case 0:
                    resultData.setTextColor(ContextCompat.getColor(requireContext(), R.color.yellow));
                    gamerNameData.setTextColor(ContextCompat.getColor(requireContext(), R.color.yellow));
                    creditData.setTextColor(ContextCompat.getColor(requireContext(), R.color.yellow));
                    dateData.setTextColor(ContextCompat.getColor(requireContext(), R.color.yellow));
                    scoreData.setTextColor(ContextCompat.getColor(requireContext(), R.color.yellow));
                    durationData.setTextColor(ContextCompat.getColor(requireContext(), R.color.yellow));
                    break;
                case 1:
                    resultData.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange));
                    gamerNameData.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange));
                    creditData.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange));
                    dateData.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange));
                    scoreData.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange));
                    durationData.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange));
                    break;
                case 2:
                    resultData.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));
                    gamerNameData.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));
                    creditData.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));
                    dateData.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));
                    scoreData.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));
                    durationData.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));
                    break;
                default:
                    resultData.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue));
                    gamerNameData.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue));
                    creditData.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue));
                    dateData.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue));
                    scoreData.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue));
                    durationData.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue));
                    break;
            }
            resultCol.addView(resultData);
            nameCol.addView(gamerNameData);
            creditCol.addView(creditData);
            dateCol.addView(dateData);
            scoreCol.addView(scoreData);
            durationCol.addView(durationData);
        }
        gamersData.addView(resultCol);
        gamersData.addView(nameCol);
        gamersData.addView(creditCol);
        gamersData.addView(dateCol);
        gamersData.addView(scoreCol);
        gamersData.addView(durationCol);
        binding.startPage.addView(gamersData);
    }

    private void deleteGamerDialog(String gamerName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        builder.setTitle("\"" + gamerName + "\" törlése");
        builder.setMessage(R.string.delete_gamer_question);

        builder.setPositiveButton(R.string.yes, (dialog, which) -> {
            GamerService.deleteGamer(gamerName);
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                setGamersDataForLandscapeOrientation();
            } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                setGamersDataForPortraitOrientation();
            }
            dialog.dismiss();});

        builder.setNegativeButton(R.string.no, (dialog, which) -> dialog.dismiss());

        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}