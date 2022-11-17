package com.example.myapplication.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.MODEL.Sanpham;
import com.example.myapplication.R;

import java.util.List;

public class DanhSachSanPhamGridAdapter extends BaseAdapter {
    private Context context;
    private List<Sanpham> list;

    public DanhSachSanPhamGridAdapter(Context context, List<Sanpham> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class Viewholder{
        ImageView img_sp, img_start_four, img_start_five,img_Edit, img_delete;
        TextView tv_ten,tv_mo_Ta, tv_gia, tv_ten_loai;

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewholder holder;
        if(view == null){
            holder = new Viewholder();
            view = LayoutInflater.from(context).inflate(R.layout.item_sanpham, viewGroup,false);
            holder.img_sp = view.findViewById(R.id.img_sanpham);
            holder.img_delete = view.findViewById(R.id.img_dele);
            holder.img_Edit = view.findViewById(R.id.img_edit);

            holder.img_start_four = view.findViewById(R.id.img_start4);
            holder.img_start_five = view.findViewById(R.id.img_start5);

            holder.tv_ten = view.findViewById(R.id.tv_ten_sp);
            holder.tv_gia = view.findViewById(R.id.tv_gia);
            holder.tv_mo_Ta = view.findViewById(R.id.tv_mota);
            holder.tv_ten_loai = view.findViewById(R.id.tv_loaips);

            view.setTag(holder);

        }else
            holder = (Viewholder) view.getTag();

        Sanpham sp = list.get(i);

        Glide.with(context).load(sp.getImgURL()).into(holder.img_sp);
        holder.tv_ten.setText(sp.getName());
        holder.tv_mo_Ta.setText(sp.getDescribe());
        holder.tv_gia.setText("Giá: " + sp.getPrice()+"$");
        holder.tv_ten_loai.setText(sp.getTen_loai());

        return view;
    }
}
