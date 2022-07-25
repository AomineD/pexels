package com.wine.pexels;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.wine.pexels.models.PexelImage;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Pexels.init(this, "563492ad6f91700001000001260162bc2b22489eb68b31976376d648");

        Pexels.getPexels()
                .search("nature")
                .load(1, new LoadImages() {
                    @Override
                    public void OnLoadImages(ArrayList<PexelImage> pexelImages) {
                        Log.e("MAIN", "OnLoadImages: "+pexelImages.size() );

                        for (int i = 0; i < pexelImages.size(); i++) {
                            Log.e("MAIN", "OnLoadImages: "+pexelImages.get(i).getSrc().getLarge2x() );
                        }
                    }

                    @Override
                    public void OnError(String erno) {
                        Log.e("MAIN", "OnError: "+erno );
                    }
                });

    }
}