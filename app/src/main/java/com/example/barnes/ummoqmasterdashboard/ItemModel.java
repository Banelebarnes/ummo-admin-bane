package com.example.barnes.ummoqmasterdashboard;

import android.animation.TimeInterpolator;

/**
 * Created by barnes on 7/24/16.
 */
public class ItemModel
{
    public final String description;
    public final int colorId1;
    public final int colorId2;
    public final TimeInterpolator interpolator;

    public ItemModel(String description, int colorId1, int colorId2, TimeInterpolator interpolator) {
        this.description = description;
        this.colorId1 = colorId1;
        this.colorId2 = colorId2;
        this.interpolator = interpolator;
    }
}
