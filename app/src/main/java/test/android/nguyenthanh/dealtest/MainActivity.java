package test.android.nguyenthanh.dealtest;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import test.android.nguyenthanh.dealtest.API.ApiServices;
import test.android.nguyenthanh.dealtest.Apdapter.ProductAdapter;
import test.android.nguyenthanh.dealtest.Model.Deal;

public class MainActivity extends AppCompatActivity {
    private ApiServices apiServices = new ApiServices();
    private List<Deal> deals = new ArrayList<>();
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);
        loadData();
    }

    private void loadData() {
        deals = apiServices.getDeals(MainActivity.this);
        ProductAdapter productAdapter = new ProductAdapter(MainActivity.this, deals);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(productAdapter);
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

//    public void loadData() {
//        LinearLayout layout_item = findViewById(R.id.layout_item);
//        layout_item.removeAllViews();
//        deals = apiServices.getDeals(MainActivity.this);
//        for (int i = 0; i < deals.size(); i++) {
//            View retval = LayoutInflater.from(layout_item.getContext()).inflate(R.layout.item, layout_item, false);
//            ImageView img_product = retval.findViewById(R.id.img_product);
//            TextView txt_product_name = retval.findViewById(R.id.txt_product_name);
//            TextView txt_price = retval.findViewById(R.id.txt_price);
//            final TextView txt_coutdown = retval.findViewById(R.id.txt_coutdown);
//            Glide.with(MainActivity.this)
//                    .load(deals.get(i).getProductThumbnail()).into(img_product);
//            txt_product_name.setText(deals.get(i).getProductName());
//            txt_price.setText(String.valueOf(deals.get(i).getProductPrice()) + " d");
//
////            final long start_millis = deals.get(i).getStartedDate();
////            long end_millis = deals.get(i).getEndDate();
////            long total_millis = (end_millis - start_millis);
//            final Handler someHandler = new Handler(getMainLooper());
//            final int finalI = i;
//            someHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    Log.d("testtime", deals.get(finalI).getStartedDate().toString() + "    " + Calendar.getInstance().getTime());
//                    if (deals.get(finalI).getStartedDate() == Calendar.getInstance().getTime()){
//                        Toast.makeText(MainActivity.this, "ok chay", Toast.LENGTH_SHORT).show();
//                    }
//                    txt_coutdown.setText(new SimpleDateFormat("HH:mm:ss", Locale.US).format(new Date()));
//                    someHandler.postDelayed(this, 1000);
//                }
//            }, 10);
////            if(start_millis == Calendar.getInstance().getTimeInMillis()){
////                CountDownTimer cdt = new CountDownTimer(total_millis, 1000) {
////                    @Override
////                    public void onTick(long millisUntilFinished) {
////                        long days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished);
////                        millisUntilFinished -= TimeUnit.DAYS.toMillis(days);
////
////                        long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
////                        millisUntilFinished -= TimeUnit.HOURS.toMillis(hours);
////
////                        long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
////                        millisUntilFinished -= TimeUnit.MINUTES.toMillis(minutes);
////
////                        long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);
////
////                        txt_coutdown.setText(days + ":" + hours + ":" + minutes + ":" + seconds); //You can compute the millisUntilFinished on hours/minutes/seconds
////                    }
////
////                    @Override
////                    public void onFinish() {
////                        txt_coutdown.setText("Finish!");
////                    }
////                };
////                cdt.start();
////            }
//
//            layout_item.addView(retval);
//        }
//
//    }


}
