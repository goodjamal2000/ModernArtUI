package com.bio.gamal.modernartui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends AppCompatActivity {
    static private final String URL = "http://www.MoMA.org";
    SeekBar sb = null;
    int prog = 0;
    int R1, G1, B1, R2, G2, B2, R3, G3, B3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sb = (SeekBar) findViewById(R.id.seekBar);
        sb.setMax(127);
        final TextView redbox = (TextView) findViewById(R.id.box1);
        final TextView greenbox = (TextView) findViewById(R.id.box2);
        final TextView bluebox = (TextView) findViewById(R.id.box3);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                prog = progress;
                Log.e("progress", String.valueOf(prog));
                R1 = G2 = B3 = 255 - prog;
                R2 = R3 = G1 = G3 = B1 = B2 = prog;
                redbox.setBackgroundColor(Color.rgb(R1, B1, G1));
                greenbox.setBackgroundColor(Color.rgb(R2, G2, B2));
                bluebox.setBackgroundColor(Color.rgb(R3, G3, B3));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.moreinfo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.more) {
            LayoutInflater layUpdate = LayoutInflater.from(this);
            View infoView = layUpdate.inflate(R.layout.more_info_view, null);
            AlertDialog alertDialog = new AlertDialog.Builder(this, R.style.AlertDialogTheme).create();
            alertDialog.setView(infoView);
            alertDialog.setCancelable(true);
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Not Now",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Visit MOMA",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // User clicked Visit MOMA button
                            Intent momaIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                            startActivity(momaIntent);
                        }
                    });
            alertDialog.show();
            Button btnPositive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            Button btnNegative = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) btnPositive.getLayoutParams();
            layoutParams.weight = 10;
            btnPositive.setLayoutParams(layoutParams);
            btnNegative.setLayoutParams(layoutParams);
            alertDialog.create();
            alertDialog.show();

        }
        return super.onOptionsItemSelected(item);
    }
}