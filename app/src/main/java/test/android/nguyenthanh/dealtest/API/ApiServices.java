package test.android.nguyenthanh.dealtest.API;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.xml.datatype.Duration;

import test.android.nguyenthanh.dealtest.Model.Deal;

public class ApiServices {

    private int INCREASING_SECONDS = 15;
    private int DURATION = 60; // 1 minute
    private String ASSERT_FILE_PATTERN = "file:///android_asset";

    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("data/deals.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public List<Deal> getDeals(Context context) {
        List<Deal> dealList = new ArrayList<>();
        try {
            JSONArray obj = new JSONArray(loadJSONFromAsset(context));

            for (int i = 0; i < obj.length(); i++) {
                Deal deal = new Deal();
                JSONObject jo_inside = obj.getJSONObject(i);
                deal.setProductName(jo_inside.getString("name"));
                deal.setProductPrice(jo_inside.getDouble("price"));
                deal.setProductThumbnail(ASSERT_FILE_PATTERN + "/data/images/" +jo_inside.getString("thumbnail"));
                Calendar c = Calendar.getInstance();
                c.add(Calendar.SECOND, i* INCREASING_SECONDS);
                deal.setStartedDate(c.getTime());
                c.add(Calendar.SECOND, DURATION);
                deal.setEndDate(c.getTime());
                dealList.add(deal);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dealList;
    }
}
