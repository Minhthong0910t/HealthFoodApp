package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
ImageView img_login;
TextInputEditText ed_user, ed_pass;
Button btn_login;
TextInputLayout til_username,til_password;

private FirebaseAuth mAuth;
GoogleSignInOptions googleSignInOptions;
GoogleSignInClient googleSignInClient;
int REQUEST_CODE_SIGIN = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        anhxaview();
        mAuth = FirebaseAuth.getInstance();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = ed_user.getText().toString().trim();
                String passWord = ed_pass.getText().toString().trim();
               if( validate(username,passWord))
                clickLogin(username, passWord);
            }
        });

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        img_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sigIn();
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
    private void clickLogin(String username, String passWord) {
        mAuth.signInWithEmailAndPassword(username, passWord)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            Toast.makeText(LoginActivity.this, "login success!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "login faild!", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }


    private void anhxaview(){
        img_login = findViewById(R.id.loginWithGoogle);
        btn_login = findViewById(R.id.btn_login);
        img_login = findViewById(R.id.loginWithGoogle);
        ed_pass = findViewById(R.id.ed_password);
        ed_user=findViewById(R.id.ed_username);
        btn_login = findViewById(R.id.btn_login);
        til_username= findViewById(R.id.til_ed_username);
        til_password = findViewById(R.id.til_ed_password);
    }


    private void sigIn() {
        Intent intent = googleSignInClient.getSignInIntent();
        startActivityForResult(intent, REQUEST_CODE_SIGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_SIGIN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                 mainActivity();

            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void mainActivity() {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private boolean validate( String user, String password){


        if(user.isEmpty()){
            til_username.setError("Tên đăng nhập không được để trống ");
        }else {
            til_username.setError("");

        }
        if(password.length()==0){
            til_password.setError("Mật khẩu không được để trống");

        }
        else{
            til_password.setError("");

        }
        return  false;



    }
}