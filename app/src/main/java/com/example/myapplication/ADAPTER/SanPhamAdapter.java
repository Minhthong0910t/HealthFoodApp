package com.example.myapplication.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.MODEL.Sanpham;
import com.example.myapplication.R;

import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.Viewholder>{
    private Context context;
    private List<Sanpham> list;

    public SanPhamAdapter(Context context, List<Sanpham> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sanpham,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Sanpham sp =list.get(position);
        if(sp == null)
            return;

        Glide.with(context).load(sp.getImgURL()).into(holder.img_sp);
        holder.tv_ten.setText(sp.getName());
        if(sp.getDescribe().length()>50){
            holder.tv_mo_Ta.setText(sp.getDescribe().substring(0,50) + "...");
        }else {
            holder.tv_mo_Ta.setText(sp.getDescribe());
        }

        holder.tv_gia.setText("Giá: " + sp.getPrice()+"$");
        holder.tv_ten_loai.setText(sp.getTen_loai());
        holder.tv_luotBan.setText("Lượt bán: "+sp.getLuot_mua());
    }

    @Override
    public int getItemCount() {
        if(list!=null)
            return list.size();
        return 0;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView img_sp, img_start_four, img_start_five,img_Edit, img_delete;
        TextView tv_ten,tv_mo_Ta, tv_gia, tv_ten_loai,tv_luotBan;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            img_sp = itemView.findViewById(R.id.img_sanpham);
            img_start_four = itemView.findViewById(R.id.img_start4);
            img_start_five = itemView.findViewById(R.id.img_start5);
            img_Edit = itemView.findViewById(R.id.img_edit);
            img_delete = itemView.findViewById(R.id.img_dele);
            tv_ten = itemView.findViewById(R.id.tv_ten_sp);
            tv_mo_Ta = itemView.findViewById(R.id.tv_mota);
            tv_gia = itemView.findViewById(R.id.tv_gia);
            tv_ten_loai = itemView.findViewById(R.id.tv_loaips);
            tv_luotBan = itemView.findViewById(R.id.tv_luotban);
        }
    }
}
