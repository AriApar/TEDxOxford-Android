package com.gmail.ariapar.tedxoxford;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;


public class AdScreen extends ActionBarActivity {

    private OnClickListener mCorkyListener = new OnClickListener() {
        public void onClick(View v) {
            // do something when the button is clicked
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        else {
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            getWindow().getDecorView().setSystemUiVisibility(uiOptions);
        }
        setContentView(R.layout.activity_ad_screen);

        findViewById(R.id.button1).setOnClickListener(mCorkyListener);

        ImageView img = (ImageView)findViewById(R.id.imgLogo);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.neptunebigideas.com/?usertype=UK%20Private%20Investor&cm_mmc=Consumer-_-Tedx-_-promotional-_-Bigideas"));
                startActivity(intent);
            }
        });
    }
}
