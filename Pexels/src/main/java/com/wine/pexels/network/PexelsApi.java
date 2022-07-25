package com.wine.pexels.network;

import android.content.Context;

import androidx.annotation.RestrictTo;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.wine.pexels.LoadImages;
import com.wine.pexels.Pexels;
import com.wine.pexels.models.PexelImage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
public class PexelsApi {

    private Context context;
    private RequestQueue queue;
    private String baseURl = "https://api.pexels.com/v1/";
    private String search = "search?";
    public PexelsApi(Context c){
       context = c;
       queue = Volley.newRequestQueue(c);
    }


    public void loadImagesSearch(String query, int page, LoadImages loadImages){

        String cUrl =baseURl+search+"query="+query+"&page="+page+"&per_page=15";

        StringRequest request = new StringRequest(Request.Method.GET, cUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject obj = new JSONObject(response);

                    JSONArray arr = obj.getJSONArray("photos");
                    ArrayList<PexelImage> pexelImages = new ArrayList<>();

                    for (int i = 0; i < arr.length(); i++) {

                        PexelImage px = new Gson().fromJson(arr.getJSONObject(i).toString(), PexelImage.class);
                        pexelImages.add(px);

                    }


                    loadImages.OnLoadImages(pexelImages);

                } catch (JSONException e) {
                    loadImages.OnError(e.getMessage());
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loadImages.OnError(error.getMessage());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();

                headers.put("Authorization", Pexels.getPexels().getApi());
                return headers;
            }
        };

        queue.add(request);


    }


}
