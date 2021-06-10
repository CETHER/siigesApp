package com.glass.siiga.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.view.Window;
import android.view.WindowManager;

import com.glass.siiga.R;
import com.glass.siiga.seguridad.Login;

public class Splash_Screen extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        /*getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setTheme(R.style.MyNoActionBarShadowTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        // set status bar color as blue
        Window window = Splash_Screen.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor( R.color.rojoDebil));

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                SharedPreferences prefs = getSharedPreferences("SIIGA_BD", MODE_PRIVATE);
                Boolean recordar_contrasena = prefs.getBoolean("recordar_contrasena", false);
                Intent ir_inicio;

                if(recordar_contrasena){
                    ir_inicio = new Intent(Splash_Screen.this, Activity_Main.class);
                } else {
                    ir_inicio = new Intent(Splash_Screen.this, Login.class);
                }

                startActivity(ir_inicio);
                Splash_Screen.this.finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
