package com.example.myapplication.FRAGMENT;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.MODEL.Loaisanpham;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;


public class AddLoaiSanPham extends Fragment {

TextView ed_ten, ed_ma;
Button btnadd, btn_xoa_trang;
    View view;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.them_loai_san_pham, container, false);

        anhXaView();
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maLoai = ed_ma.getText().toString();
                String tenLoai = ed_ten.getText().toString();
                Loaisanpham lsp = new Loaisanpham(maLoai, tenLoai, null);
                db.collection("LoaiSanPhams").document(maLoai).set(lsp).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getContext(), "Thêm loại sản phẩm thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        btn_xoa_trang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_ten.setText("");
                ed_ma.setText("");
            }
        });
        return view;
    }
    private void anhXaView(){
        ed_ten = view.findViewById(R.id.ed_ten_loai);
        ed_ma = view.findViewById(R.id.ed_ma_loai);
        btnadd = view.findViewById(R.id.btn_add_loai);
        btn_xoa_trang = view.findViewById(R.id.btn_xoa_trang);
    }
}