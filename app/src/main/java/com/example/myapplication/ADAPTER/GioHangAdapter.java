package com.example.myapplication.ADAPTER;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.myapplication.MODEL.GioHang;
import com.example.myapplication.R;

import java.util.List;


public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.VIewholder>{
    private Context context;
    private List<GioHang> list;
    String TAG = "GIOHANGADAPTER";
    public GioHangAdapter(Context context, List<GioHang> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VIewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gio_hang,parent,false);

        return new VIewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VIewholder holder, int position) {
        GioHang gh = list.get(position);
        if(gh==null)
            return;

        //Log.d(TAG, "ghadapter: " + gh.getTenLoaiSanPham());
        Glide.with(context).load(gh.getHinhAnh()).into(holder.img_sp);
        holder.tvTenSp.setText(gh.getTenSanPham());
        holder.tvGia.setText("gia: " + gh.getDonGia());
        holder.tvSoLuong.setText("so luong: " + gh.getSoLuong());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VIewholder extends RecyclerView.ViewHolder {
        ImageView img_sp;
        TextView tvTenLoai, tvTenSp, tvGia, tvSoLuong;
        public VIewholder(@NonNull View itemView) {
            super(itemView);
            img_sp = itemView.findViewById(R.id.img_sp_gh);
            tvTenLoai = itemView.findViewById(R.id.tv_ten_loai);
            tvTenSp= itemView.findViewById(R.id.tv_tensp);
            tvGia = itemView.findViewById(R.id.tv_gia);
            tvSoLuong = itemView.findViewById(R.id.tv_soluong);

        }
    }
}
