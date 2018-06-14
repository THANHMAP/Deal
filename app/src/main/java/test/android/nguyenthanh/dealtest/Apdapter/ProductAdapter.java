package test.android.nguyenthanh.dealtest.Apdapter;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import test.android.nguyenthanh.dealtest.Model.Deal;
import test.android.nguyenthanh.dealtest.R;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewMyHolder> {
    public Context context;
    public List<Deal> dealList;

    public ProductAdapter(Context context, final List<Deal> dealList) {
        this.context = context;
        this.dealList = dealList;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewMyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new ProductAdapter.ViewMyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductAdapter.ViewMyHolder holder, final int position) {
        Deal deal = dealList.get(position);
        holder.txt_product_name.setText(deal.getProductName());
        holder.txt_price.setText(String.valueOf(deal.getProductPrice()) + " đ");
        Glide.with(context)
                .load(deal.getProductThumbnail())
                .into(holder.img_product);
        long start = deal.getStartedDate().getTime();
        long end = deal.getEndDate().getTime();
        Date today = new Date();
        final long currentTime = today.getTime();
        final long timeRemaining = end - System.currentTimeMillis();
        if (holder.timer != null) {
            holder.timer.cancel();
        }
        holder.timer = new CountDownTimer(end - currentTime, 1000) {
            @Override
            public void onTick(long l) {
                holder.txt_coutdown.setText(millToMins(l) + " mins remaining");
            }

            @Override
            public void onFinish() {
                holder.rel_item.setVisibility(View.GONE);
                removeAt(holder.getAdapterPosition());
//                notifyDataSetChanged();
                holder.timer.cancel();
            }
        }.start();


    }

    private String millToMins(long millisec) {
        return millisec / (60000) + ":" + (int) (millisec / 1000) % (60);
    }


    @Override
    public int getItemCount() {
        return dealList.size();
    }

    public class ViewMyHolder extends RecyclerView.ViewHolder {
        ImageView img_product;
        RelativeLayout rel_item;
        CountDownTimer timer;
        TextView txt_product_name, txt_price, txt_coutdown;

        public ViewMyHolder(View itemView) {
            super(itemView);
            rel_item =  itemView.findViewById(R.id.rel_item);
            img_product = itemView.findViewById(R.id.img_product);
            txt_product_name = itemView.findViewById(R.id.txt_product_name);
            txt_price = itemView.findViewById(R.id.txt_price);
            txt_coutdown = itemView.findViewById(R.id.txt_coutdown);

        }
    }

    public void removeAt(int position) {
        if(position == -1)
            return;
        dealList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, dealList.size());

    }
}
