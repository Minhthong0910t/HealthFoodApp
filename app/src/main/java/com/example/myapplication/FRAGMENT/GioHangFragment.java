package com.example.myapplication.FRAGMENT;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.ADAPTER.GioHangAdapter;
import com.example.myapplication.MODEL.DonHang;
import com.example.myapplication.MODEL.GioHang;
import com.example.myapplication.MODEL.NhanVien;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class GioHangFragment extends Fragment {

String TAG  = "GIOHANG";
private View view;
TextView tv_tongTien;
Button btn_mua;
RecyclerView recyclerView;
GioHangAdapter gioHangAdapter;
List<GioHang> list;
    DatabaseReference reference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_gio_hang, container, false);
        anhXa();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        reference = FirebaseDatabase.getInstance().getReference("GioHangs");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                double tongTien = 0;
                 for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                GioHang gh =dataSnapshot.getValue(GioHang.class);

                    if(gh.getIdUser().equals(user.getUid())){
                        list.add(gh);

                        tongTien += gh.getDonGia()*gh.getSoLuong();
                        }

                    }
                    tv_tongTien.setText(tongTien+"");
                    gioHangAdapter = new GioHangAdapter(getContext(), list);
                 recyclerView.setAdapter(gioHangAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        FirebaseUser userCurrent = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        btn_mua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("Users").document("nhanvien")
                        .collection("nhanviens").addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                if (error != null) {
                                    return;
                                }
                                for(QueryDocumentSnapshot doc:value){
                                    NhanVien nv = doc.toObject(NhanVien.class);
                                    if(nv.getId().equals(userCurrent.getUid())){

                                        DonHang dh = new DonHang();
                                        dh.setName(nv.getName());
                                        dh.setTongTien(Double.parseDouble(tv_tongTien.getText().toString()));
                                        dh.setSanphams(list);

                                        if(nv.getSdt().equals("")){
                                            Toast.makeText(getContext(), "vui long them so dien thoai", Toast.LENGTH_SHORT).show();
                                            return;

                                        }if (nv.getDiachi().equals("")){
                                            Toast.makeText(getContext(), "vui long them dia chi", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        dh.setDiaChi(nv.getDiachi());
                                        dh.setSdt(nv.getSdt());
                                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("DonHangs");
                                        reference.child(nv.getId())
                                                .setValue(dh);
                                    }
                                }
                            }
                        });
            }
        });
        return view;
    }
    private void anhXa(){
        recyclerView = view.findViewById(R.id.rcv_gioHang);
        list = new ArrayList<>();
        tv_tongTien = view.findViewById(R.id.tv_tong_tien);
        btn_mua = view.findViewById(R.id.btn_dat_hang);
    }

}