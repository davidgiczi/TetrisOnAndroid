package com.david.giczi.tetris.app.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.david.giczi.tetris.app.MainActivity;
import com.david.giczi.tetris.app.R;
import com.david.giczi.tetris.app.databinding.FragmentGameBinding;
import com.david.giczi.tetris.app.db.GamerService;
import com.david.giczi.tetris.app.logic.TetrisGame;

public class GameFragment extends Fragment {

    private FragmentGameBinding binding;
    private TetrisGame game;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentGameBinding.inflate(inflater, container, false);
        this.game = new TetrisGame(binding);
        String playerName = ((MainActivity) requireActivity()).playerName;
        game.setPlayer(GamerService.getGamerByName(playerName));
        game.playGame();
        MainActivity.PAGE_NUMBER_VALUE = 1;
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            continueGameDialog();
        }
    }

    private void continueGameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        builder.setTitle(R.string.continue_game_title);
        builder.setMessage(R.string.continue_game_question);

        builder.setPositiveButton(R.string.yes, (dialog, which) -> dialog.dismiss());

        builder.setNegativeButton(R.string.no, (dialog, which) ->{
                    NavController navController =
                            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
                    navController.navigate(R.id.action_GameFragment_to_HomeFragment);
                    dialog.dismiss();
                });


        AlertDialog alert = builder.create();
        alert.show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}