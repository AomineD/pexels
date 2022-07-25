package com.wine.pexels;

import com.wine.pexels.models.PexelImage;

import java.util.ArrayList;

public interface LoadImages {
    void OnLoadImages(ArrayList<PexelImage> pexelImages);
    void OnError(String erno);
}
