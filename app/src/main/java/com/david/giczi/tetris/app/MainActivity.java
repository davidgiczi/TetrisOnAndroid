package com.david.giczi.tetris.app;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.david.giczi.tetris.app.databinding.ActivityMainBinding;
import com.david.giczi.tetris.app.db.Gamer;
import com.david.giczi.tetris.app.db.GamerService;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    public static int PAGE_NUMBER_VALUE;
    public Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GamerService(this);
        GamerService.getAllGamers();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.blue));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.game_start) {
            popupStartGameDialog();
            return true;
        }
        else if (id == R.id.action_exit) {
            exitAppDialog();
            return true;
        }
        else if( id == android.R.id.home ){
            exitGameDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void exitAppDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.close_app_title);
        builder.setMessage(R.string.close_app_question);

        builder.setPositiveButton(R.string.yes, (dialog, which) -> {
            System.exit(0);
            dialog.dismiss();
        });

        builder.setNegativeButton(R.string.no, (dialog, which) -> dialog.dismiss());

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void exitGameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.close_game_title);
        builder.setMessage(R.string.close_game_question);

        builder.setPositiveButton(R.string.yes, (dialog, which) -> {
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.action_GameFragment_to_HomeFragment);
            dialog.dismiss();
        });

        builder.setNegativeButton(R.string.no, (dialog, which) -> dialog.dismiss());

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void playerNameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.info_title);
        builder.setMessage(R.string.player_name_option);

        builder.setPositiveButton(R.string.ok, (dialog, which) -> dialog.dismiss());

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void createPlayerDialog(PopupWindow startGameWindow, String player, String info) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String title = "\"" + player + "\" regisztrálása";
        builder.setTitle(title);
        builder.setMessage(R.string.create_new_player_question);

        builder.setPositiveButton(R.string.yes, (dialog, which) -> {
            GamerService.insertGamer(new Gamer(player, 0, 0, 0));
            startGameProcess(startGameWindow, info);
            dialog.dismiss();});

        builder.setNegativeButton(R.string.no, (dialog, which) -> dialog.dismiss());

        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void popupStartGameDialog(){
        ViewGroup container = (ViewGroup) getLayoutInflater().inflate(R.layout.fragment_start_game, null);
        PopupWindow startGameWindow = new PopupWindow(container, 1000, 700, true);
        startGameWindow.showAtLocation(binding.getRoot(), Gravity.CENTER, 0, - 400);
        Spinner tempoSpinner = container.findViewById(R.id.tempo_spinner);
        tempoSpinner.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.spinner_items,
                R.layout.spinner_tempo
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tempoSpinner.setAdapter(adapter);
        container.findViewById(R.id.start_button).setOnClickListener(s ->{
            String player = ((TextView) container.findViewById(R.id.gamer_name_input_field)).getText().toString().trim();
            if( player.isEmpty() ){
                playerNameDialog();
                return;
            }
            String title =  player + ", " + tempoSpinner.getSelectedItem().toString();
            if( !GamerService.GAMERS.contains(new Gamer(player, 0,0, 0)) ){
                createPlayerDialog(startGameWindow, player, title);
                return;
            }
            startGameProcess(startGameWindow, title);
        });
    }

    private void startGameProcess(PopupWindow startGameWindow, String title){
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.action_HomeFragment_to_GameFragment);
        menu.findItem(R.id.game_start).setEnabled(false);
        ((TextView)  findViewById(R.id.game_info_title)).setText(title);
        startGameWindow.dismiss();
    }

}