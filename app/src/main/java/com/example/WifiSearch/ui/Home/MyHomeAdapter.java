package com.example.WifiSearch.ui.Home;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.WifiSearch.R;

import java.util.Arrays;
import java.util.List;

public class MyHomeAdapter extends RecyclerView.Adapter<MyHomeAdapter.ViewHolder> {
    private List<String> mData;
    private final List<Integer> drawables = Arrays.asList(
            R.drawable.wifisearch,
            R.drawable.wificontroll);

    public MyHomeAdapter(List<String> data){
        mData=data;
    }
    //set on ListenClicker
    private OnItemClickListener itemClickListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }
    //


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //連結項目佈局檔list_item
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //設置txtItem要顯示的內容
        holder.txtItem.setText(mData.get(position));
        holder.imageView.setImageDrawable(ResourcesCompat.getDrawable(holder.imageView.getResources(),
                drawables.get(position),
                null));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    //建立ViewHolder
    class ViewHolder extends RecyclerView.ViewHolder{
        //宣告元件
        private TextView txtItem;

        private ImageView imageView;




        ViewHolder(View itemview){
            super(itemview);
            txtItem=(TextView) itemview.findViewById(R.id.txtItem);
            imageView=(ImageView) itemview.findViewById(R.id.listimage);

            //點擊項目
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
//
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(getBindingAdapterPosition());
                    }
                }
            });
        }
    }


}
