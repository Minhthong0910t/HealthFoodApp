package com.example.myapplication.FRAGMENT;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;


public class FragmentProfile extends Fragment {

    private View view;

EditText ed_address, ed_phone;
Button btn_update;
    private Uri muri;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_profile, container, false);
        anhXa();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser userCurrent = FirebaseAuth.getInstance().getCurrentUser();
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("Users").document("nhanvien")
                        .collection("nhanviens").document(userCurrent.getUid()).update("diachi",ed_address.getText().toString(),"sdt",ed_phone.getText().toString());
            }
        });
        return view;
    }
    private void anhXa(){
        ed_address = view.findViewById(R.id.ed_adress);
        ed_phone = view.findViewById(R.id.ed_phone);
        btn_update = view.findViewById(R.id.btn_update);
    }

}