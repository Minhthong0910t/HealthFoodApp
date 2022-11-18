package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.MODEL.Admin;
import com.example.myapplication.MODEL.NhanVien;
import com.example.myapplication.MODEL.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.SQLOutput;
import java.util.Scanner;

public class RegisterrActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
// ...
// Initialize Firebase Auth
TextInputLayout til_username,til_password,til_enterpassword, til_ed_email;
    EditText ed_name, ed_email,ed_password,enterpassword;
  Button btn_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sog_in);
        mAuth = FirebaseAuth.getInstance();
        anhXaView();
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ed_name.getText().toString();
                String enterpass = enterpassword.getText().toString();

                String email = ed_email.getText().toString();
                String password = ed_password.getText().toString();
                if( validate(name,password,email,enterpass)){
                    register(email,password);
            }

            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
    public void register(String email, String pass){
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser userCurent = mAuth.getCurrentUser();
                            String userid = userCurent.getUid();
                            FirebaseFirestore db = FirebaseFirestore.getInstance();

                           if(email.matches("^nhanvien+\\w+\\@+\\w+\\.+\\w+")){
                             //  String id, String name, String email, String password, String imgURL, boolean trangThaiTym, int loaiUser, String sdt, String diachi
                                 NhanVien nv = new NhanVien(userid, ed_name.getText().toString(), email,pass,"default",false,2,"chưa thêm số điện thoại", "chưa thêm địa chỉ");
                                db.collection("Users").document("nhanvien")
                                                .collection("nhanviens")
                                                        .document()
                                                                .set(nv).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(RegisterrActivity.this, "dang ky thanh cong", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(RegisterrActivity.this, MainActivity.class));
                                        }
                                    }
                                });
                            }else {

                            }

                        }
                    }
                });
    }
   private void anhXaView(){
        ed_name = findViewById(R.id.ed_name);
        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        enterpassword = findViewById(R.id.enter_password);
        btn_add = findViewById(R.id.btn_ok);
       til_enterpassword=findViewById(R.id.til_enter_password);
       til_username = findViewById(R.id.til_ed_name);
       til_password = findViewById(R.id.til_ed_password);
       til_ed_email=findViewById(R.id.til_ed_email);


   }
    private boolean  validate(String name,String pass, String email, String enterpass){


        if(name.isEmpty()){
            til_username.setError("Tên đăng nhập không đuọc để trống");
        }else{
            til_username.setError("");
        }
        if(pass.length()==0){
            til_password.setError("Mật khẩu không được để trống");
        }else{
            til_password.setError("");
        }
        if(email.length()==0){
            til_ed_email.setError("Email không được để trống");

        }else{
            til_ed_email.setError("");
        }
        if(enterpass.length()==0){
            til_enterpassword.setError("Mật khẩu không được để trống");
        }else{
            til_enterpassword.setError("");
        }
        return false;
    }
}