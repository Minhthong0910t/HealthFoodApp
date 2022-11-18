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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.ADAPTER.CommentAdapter;
import com.example.myapplication.MODEL.Comment;
import com.example.myapplication.MODEL.Loaisanpham;
import com.example.myapplication.MODEL.NhanVien;
import com.example.myapplication.MODEL.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChiTietSanPham extends AppCompatActivity {
String TAG  = "ChiTietSanPham";
    RelativeLayout btn_yeuthich;
ImageView img_sanpham,img_tym_bay;
TextView tv_ten_sp,tv_mota,tv_gia,tv_loaips,tv_luotban ;
RecyclerView recyrcleDanhGia;
EditText ed_cmt;
Button btn_cmt;


List<Comment> comments;
CommentAdapter commentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiwt_san_phammm);
    }
//        anhXaView();
//        Intent intent = getIntent();
//
//        FirebaseUser usercurent = FirebaseAuth.getInstance().getCurrentUser();
//
//
//
//
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        String maSP = intent.getStringExtra("maSP");
//       String name =  intent.getStringExtra("name");
//       double dgia =  intent.getDoubleExtra("donGia", 0);
//       String urlIMG =  intent.getStringExtra("hinhAnh");
//       String moTa =  intent.getStringExtra("moTa");
//       int star =  intent.getIntExtra("star", 0);
//       int favorite = intent.getIntExtra("favorite",0);
//       int timeShip =  intent.getIntExtra("time", 0);
//       String tenLoai =  intent.getStringExtra("tenLoai");
//
//        Glide.with(this).load(urlIMG).into(img_sanpham);
//        tv_ten_sp.setText(name);
//        tv_mota.setText(moTa);
//        tv_gia.setText("Gia: " + dgia);
//        tv_loaips.setText(tenLoai);
//        tv_luotban.setText("0");
//
//        db.collection("Comments").addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
//                if (e != null) {
//                    Log.w(TAG, "Listen failed.", e);
//                    return;
//                }
//                comments.clear();
//                for (QueryDocumentSnapshot doc : value) {
//
//                    Comment cm = doc.toObject(Comment.class);
//                    if(cm.getId_comment().equals(maSP)){
//                        comments.add(cm);
//                    }
//                }
//                Collections.sort(comments, new Comparator<Comment>() {
//                    @Override
//                    public int compare(Comment comment, Comment t1) {
//                        return t1.getTime_comment().compareTo(comment.getTime_comment());
//                    }
//                });
//                commentAdapter = new CommentAdapter(ChiTietSanPham.this, comments);
//                recyrcleDanhGia.setAdapter(commentAdapter);
//            }
//        });
//        btn_cmt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(ed_cmt.getText().toString().equals("")){
//                    Toast.makeText(ChiTietSanPham.this, "vui long nhap binh luan", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(usercurent==null){
//                    finish();
//                    startActivity(new Intent(ChiTietSanPham.this, LoginActivity.class));
//                }else {
//                    String iduser  = usercurent.getUid();
//                    String content = ed_cmt.getText().toString();
//                    Log.d(TAG, "nhan vien"+ usercurent.getEmail());
//                    db.collection("Users").document("nhanvien")
//                            .collection("nhanviens")
//                            .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                                @Override
//                                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
//                                    if (e != null) {
//                                        return;
//                                    }
//                                    for(QueryDocumentSnapshot document : value){
//                                        User usr = document.toObject(User.class);
//                                        if(usr.getId().equals(iduser)){
//
//                                            Comment cm = new Comment();
//                                            cm.setId_comment(maSP);
//                                            cm.setId_user(iduser);
//                                            cm.setName_user(usr.getName());
//                                            cm.setImg_user("default");
//                                            cm.setContent(content);
//                                            String timeCureent = "";
//                                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//                                                DateTimeFormatter dtf = DateTimeFormatter.ofPattern( "yyyy/MM/dd HH:mm:ss" );
//                                                LocalDateTime now = LocalDateTime.now();
//                                                timeCureent = dtf.format(now);
//                                                cm.setTime_comment(timeCureent);
//                                            }
//                                            cm.setSoSaoDanhGia(5);
//                                            db.collection("Comments").document(content).set(cm).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                                @Override
//                                                public void onComplete(@NonNull Task<Void> task) {
//                                                    ed_cmt.setText("");
//                                                }
//                                            });
//                                        }
//                                    }
//                                }
//                            });
//                }
//            }
//        });
//
//        btn_yeuthich.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Animation animation = AnimationUtils.loadAnimation(ChiTietSanPham.this,R.anim.tym_bay_animation);
//                img_tym_bay.startAnimation(animation);
//
//                if(usercurent==null) {
//                    startActivity(new Intent(ChiTietSanPham.this, LoginActivity.class));
//                }else {
//                    FirebaseFirestore db = FirebaseFirestore.getInstance();
//                    if(usercurent.getEmail().matches("^nhanvien+\\w+\\@+\\w+\\.+\\w+")){
//                        db.collection("Users").document("nhanvien")
//                                .collection("nhanviens")
//                                .whereEqualTo("email",usercurent.getEmail())
//                                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                        if(task.isSuccessful()){
//                                            for(QueryDocumentSnapshot doc: task.getResult()){
//                                                NhanVien nv = doc.toObject(NhanVien.class);
//                                                if(!nv.isTrangThaiTym()) {
//
//                                                    db.collection("Users")
//                                                            .document("nhanvien")
//                                                            .collection("nhanviens")
//                                                            .document(doc.getId()).update("trangThaiTym", true);
//                                                    addTymSanPham(maSP, tenLoai, favorite + 1);
//                                                    Log.d(TAG, "trang thai tym" + nv.isTrangThaiTym());
//                                                    Toast.makeText(ChiTietSanPham.this, "Da them vao san pham yeu thich", Toast.LENGTH_SHORT).show();
//                                                }
////                                                else {
////
////                                                    db.collection("Users")
////                                                            .document("nhanvien")
////                                                            .collection("nhanviens")
////                                                            .document(doc.getId()).update("trangThaiTym", false);
////                                                    addTymSanPham(maSP, tenLoai, favorite-1);
////                                                    Log.d(TAG, "trang thai tym" + nv.isTrangThaiTym());
////                                                }
//                                            }
//                                        }
//                                    }
//                                });
//                    }
//                }
//            }
//        });
//
//    }
//    public void addTymSanPham(String maspUpdate,String nameLoai,int favoriteUpdate){
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collection("LoaiSanPhams").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if(task.isSuccessful()){
//                    for(QueryDocumentSnapshot doc: task.getResult()){
//                        Loaisanpham lsp = doc.toObject(Loaisanpham.class);
//                        if(lsp.getName().equals(nameLoai)){
//                            db.collection("LoaiSanPhams")
//                                    .document(doc.getId()).update("sanphams."+maspUpdate+".favorite", favoriteUpdate);
//                        }
//                    }
//                }
//            }
//        });
//    }
//    private void anhXaView(){
//         img_sanpham = findViewById(R.id.img_sanpham);
//         tv_ten_sp = findViewById(R.id.tv_ten_sp);
//         tv_mota = findViewById(R.id.tv_mota);
//         tv_gia = findViewById(R.id.tv_gia);
//         tv_loaips = findViewById(R.id.tv_loaips);
//        tv_luotban  = findViewById(R.id.tv_luotban);
//         recyrcleDanhGia = findViewById(R.id.recyrcleDanhGia);
//         recyrcleDanhGia.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
//         comments = new ArrayList<>();
//         ed_cmt = findViewById(R.id.ed_comment);
//         btn_cmt = findViewById(R.id.btn_comment);
//
//        btn_yeuthich = findViewById(R.id.btn_tha_tym);
//        img_tym_bay= findViewById(R.id.img_tymbay);
//
//    }
}