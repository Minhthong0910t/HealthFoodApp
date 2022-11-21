package com.example.myapplication.ADAPTER;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.DanhSachSanPham;
import com.example.myapplication.MODEL.Loaisanpham;
import com.example.myapplication.R;

import java.util.List;

public class LoaiSanPhamAdapter extends RecyclerView.Adapter<LoaiSanPhamAdapter.ViewHolder>{
    private Context context;
    private List<Loaisanpham> list;

    public LoaiSanPhamAdapter(Context context, List<Loaisanpham> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_loai_sp,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Loaisanpham lsp = list.get(position);
        if(lsp==null)
            return;

        Glide.with(context).load(lsp.getImgURL()).into(holder.img_lsp);
        holder.tv_name.setText(lsp.getName());

        holder.img_lsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DanhSachSanPham.class);
                intent.putExtra("tenLoai", lsp.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list!=null)
            return list.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        ImageView img_lsp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_tenLoai);
            img_lsp =itemView.findViewById(R.id.img_lsp);
        }
    }
}
