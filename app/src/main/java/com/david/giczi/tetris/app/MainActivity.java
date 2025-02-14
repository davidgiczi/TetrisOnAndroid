package com.david.giczi.tetris.app;

import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.david.giczi.tetris.app.databinding.ActivityMainBinding;

import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Spinner;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    public static int PAGE_NUMBER_VALUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_start) {
            popupStartGameDialog();
            return true;
        }
        else if (id == R.id.action_exit) {
            exitAppDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void gotoNextFragment(){
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        if( PAGE_NUMBER_VALUE == 0 ){
            navController.navigate(R.id.action_HomeFragment_to_GameFragment);
        }
    }

    private void exitAppDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.close_app_title);
        builder.setMessage(R.string.close_app_question);

        builder.setPositiveButton(R.string.yes, (dialog, which) -> {
            dialog.dismiss();
            System.exit(0);
        });

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

    private void popupStartGameDialog(){
        ViewGroup container = (ViewGroup) getLayoutInflater().inflate(R.layout.fragment_start_game, null);
        PopupWindow startGameWindow = new PopupWindow(container, 1000, 700, true);
        startGameWindow.showAtLocation(binding.getRoot(), Gravity.CENTER, 0, - 400);
        Spinner tempoSpinner = container.findViewById(R.id.tempo_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.spinner_items,
                R.layout.spinner_tempo
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tempoSpinner.setAdapter(adapter);

    }

}