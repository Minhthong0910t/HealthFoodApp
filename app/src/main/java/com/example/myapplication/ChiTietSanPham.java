package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.ADAPTER.CommentAdapter;
import com.example.myapplication.MODEL.Comment;
import com.example.myapplication.MODEL.Loaisanpham;
import com.example.myapplication.MODEL.Sanpham;
import com.example.myapplication.MODEL.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChiTietSanPham extends AppCompatActivity {
String TAG  = "ChiTietSanPham";

ImageView img_sanpham;
TextView tv_ten_sp,tv_mota,tv_gia,tv_loaips,tv_luotban ;
RecyclerView recyrcleDanhGia;
EditText ed_cmt;
Button btn_cmt;


List<Comment> comments;
CommentAdapter commentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);

        anhXaView();
        Intent intent = getIntent();
        FirebaseUser usercurent = FirebaseAuth.getInstance().getCurrentUser();
        String iduser = usercurent.getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String maSP = intent.getStringExtra("maSP");
       String name =  intent.getStringExtra("name");
       double dgia =  intent.getDoubleExtra("donGia", 0);
       String urlIMG =  intent.getStringExtra("hinhAnh");
       String moTa =  intent.getStringExtra("moTa");
       int star =  intent.getIntExtra("star", 0);
       boolean favorite = intent.getBooleanExtra("favorite",false);
       int timeShip =  intent.getIntExtra("time", 0);
       String tenLoai =  intent.getStringExtra("tenLoai");

        Glide.with(this).load(urlIMG).into(img_sanpham);
        tv_ten_sp.setText(name);
        tv_mota.setText(moTa);
        tv_gia.setText("Gia: " + dgia);
        tv_loaips.setText(tenLoai);
        tv_luotban.setText("0");


        db.collection("LoaiSanPhams").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for(QueryDocumentSnapshot doc : value){
                    if(doc.toObject(Loaisanpham.class).getName().equals(tenLoai)){
                        db.collection("LoaiSanPhams").document(doc.toObject(Loaisanpham.class).getMaLoai())
                                .collection("sanphams").document(maSP)
                                .collection("comments")
                                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                    @Override
                                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
                                        if (e != null) {
                                            Log.w(TAG, "Listen failed.", e);
                                            return;
                                        }
                                        comments.clear();
                                        for (QueryDocumentSnapshot doc : value) {
                                            Comment cm = doc.toObject(Comment.class);
                            comments.add(cm);
                                            Log.d(TAG, "onEvent: "+comments.size());

                                        }
                                        commentAdapter = new CommentAdapter(ChiTietSanPham.this, comments);
                                        recyrcleDanhGia.setAdapter(commentAdapter);
                                    }
                                });
                    }

                }

            }
        });


        btn_cmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = ed_cmt.getText().toString();
                db.collection("Users").addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w(TAG, "Listen failed.", e);
                            return;
                        }
                        for(QueryDocumentSnapshot document : value){
                            User usr = document.toObject(User.class);
                            if(usr.getId().equals(iduser)){
                                Map<String, Comment> map_cmt = new HashMap<>();

                                    Comment cm = new Comment();
                                    cm.setId_comment(content);
                                    cm.setId_user(iduser);
                                    cm.setName_user(usr.getName());
                                    cm.setImg_user("default");
                                    cm.setContent(content);
                                    String timeCureent = "";
                                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern( "yyyy/MM/dd HH:mm:ss" );
                                        LocalDateTime now = LocalDateTime.now();
                                        timeCureent = dtf.format(now);
                                        cm.setTime_comment(timeCureent);
                                    }
                                    cm.setSoSaoDanhGia(5);
                                    //(masp, name, price, time_ship, describe, 0, false, muri, null,lsp.getName(),3
                                   // map.put(maSP, new Sanpham(maSP, name, dgia, timeShip, moTa, 0,favorite, urlIMG, map_cmt, tenLoai, star));
                                    db.collection("LoaiSanPhams").addSnapshotListener(new EventListener<QuerySnapshot>() {
                                        @Override
                                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
                                            if (e != null) {
                                                Log.w(TAG, "Listen failed.", e);
                                                return;
                                            }
                                            for (QueryDocumentSnapshot doc : value) {
                                                Loaisanpham lsp = doc.toObject(Loaisanpham.class);
                                                if(lsp.getName().equals(tenLoai)){
                                                    db.collection("LoaiSanPhams").document(lsp.getMaLoai())
                                                            .collection("sanphams").document(maSP)
                                                            .collection("comments").document()
                                                            .set(cm).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                    Toast.makeText(ChiTietSanPham.this, "them danh gia chu thanh cong", Toast.LENGTH_SHORT).show();
                                                                    ed_cmt.setText("");
                                                                }
                                                            });
//
                                                }
                                            }
                                        }
                                    });

                            }
                        }
                    }
                });

            }


        });


    }
    private void anhXaView(){
         img_sanpham = findViewById(R.id.img_sanpham);
         tv_ten_sp = findViewById(R.id.tv_ten_sp);
         tv_mota = findViewById(R.id.tv_mota);
         tv_gia = findViewById(R.id.tv_gia);
         tv_loaips = findViewById(R.id.tv_loaips);
        tv_luotban  = findViewById(R.id.tv_luotban);
         recyrcleDanhGia = findViewById(R.id.recyrcleDanhGia);
         recyrcleDanhGia.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
         comments = new ArrayList<>();
         ed_cmt = findViewById(R.id.ed_comment);
         btn_cmt = findViewById(R.id.btn_comment);

    }
}