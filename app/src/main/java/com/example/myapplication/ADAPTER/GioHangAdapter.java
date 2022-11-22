package com.example.myapplication.ADAPTER;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.myapplication.MODEL.GioHang;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.List;


public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.VIewholder>{
    private Context context;
    private List<GioHang> list;
    int count=0;
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
        holder.tvGia.setText("" + gh.getDonGia());
        holder.tvSoLuong.setText("" + gh.getSoLuong());


        holder.tang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
              holder.tvSoLuong.setText(""+count);
            }
        });

        holder.giam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count<=0)
                    count=0;
                else
                count--;
                holder.tvSoLuong.setText(""+count);
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VIewholder extends RecyclerView.ViewHolder {
        ImageView img_sp;
        TextView tvTenLoai, tvTenSp, tvGia, tvSoLuong;
        ImageView tang,giam;
        public VIewholder(@NonNull View itemView) {
            super(itemView);
            img_sp = itemView.findViewById(R.id.img_sp_gh);
//            tvTenLoai = itemView.findViewById(R.id.tv_ten_loai);
            tvTenSp= itemView.findViewById(R.id.tv_tensp);
            tvGia = itemView.findViewById(R.id.tv_gia);
            tvSoLuong = itemView.findViewById(R.id.tv_soluong);
            tang=itemView.findViewById(R.id.tang);
            giam=itemView.findViewById(R.id.giam);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("GioHangs");
                    reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            for(DataSnapshot dataSnapshot : task.getResult().getChildren()){

                                GioHang gh = dataSnapshot.getValue(GioHang.class);
                                if(gh.getTenSanPham().equals(list.get(getAdapterPosition()).getTenSanPham())){
                                   // Log.d(TAG, "onComplete: " + gh.getTenSanPham());
                                    reference.child(dataSnapshot.getKey()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                           if(task.isSuccessful()){
                                               Toast.makeText(context, "xoa thanh cong khoi gio hang", Toast.LENGTH_SHORT).show();
                                           }
                                        }
                                    });

                                }
                            }


                        }
                    });
//                    reference.child(list.get(getAdapterPosition()).).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if(task.isSuccessful()){
//                                Snackbar snackbar = Snackbar.make(context,view,"Xoa thanh cong khoi gio hang",2000);
//                                snackbar.setAction("ok", null).show();
//                                Toast.makeText(context, "xóa thành công khỏi giỏ hàng", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
                    return false;
                }
            });
        }
    }
}

//    public void increm(View view){ count  ; value.setText("" count); }
//    public void decrem(View view){ if (count<=0) count=0; else count--; value.setText("" count); }