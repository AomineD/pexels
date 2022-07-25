package com.wine.pexels;

import android.content.Context;

import com.wine.pexels.network.PexelsApi;

import java.lang.reflect.Type;

public class Pexels {
    static String key_api = "563492ad6f91700001000001260162bc2b22489eb68b31976376d648";


    private Context context;
    private static Pexels pexels;
    public String getApi(){
        return key_api;
    }

    public static Pexels getPexels(){
        return pexels;
    }

    public static void init(Context c, String api){
        Pexels p = new Pexels(c, api);
    }

    private Pexels(Context cm, String keyapi){
        context = cm;
        pexels = this;
        key_api = keyapi;
    }

    private PexelsApi pexelsApi;
private String search;
    public Pexels search(String by){
        pexelsApi = new PexelsApi(context);
search = by;
        return this;
    }



    private TypeOrientation t = null;
    public Pexels orientation(TypeOrientation typeOrientation){
t = typeOrientation;
        return this;
    }

    public void load(int page,LoadImages listener){

        if(t == null)
pexelsApi.loadImagesSearch(search, page, listener);
else{
    pexelsApi.setOrientation(t);
pexelsApi.loadByOrientation(search, page, listener);
t = null;
        }

    }

}
