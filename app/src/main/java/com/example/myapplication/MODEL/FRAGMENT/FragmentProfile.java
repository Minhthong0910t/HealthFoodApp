package com.example.myapplication.MODEL.FRAGMENT;

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

import com.example.myapplication.MODEL.KhachHang;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
        DatabaseReference referencekhs = FirebaseDatabase.getInstance().getReference("KhachHangs");
        FirebaseUser userCurrent = FirebaseAuth.getInstance().getCurrentUser();
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ed_address.getText().toString().equals("")){
                    Toast.makeText(getContext(), "khong duoc de trong dia chi", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(ed_phone.getText().toString().equals("")){
                    Toast.makeText(getContext(), "khong dc de trong sdt", Toast.LENGTH_SHORT).show();
                    return;
                }
                referencekhs.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                            KhachHang kh = dataSnapshot.getValue(KhachHang.class);
                            kh.setDiachi(ed_address.getText().toString());
                            kh.setSdt(ed_phone.getText().toString());
                            if(kh.getId().equals(userCurrent.getUid())){
                                referencekhs.child(dataSnapshot.getKey()).setValue(kh);
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
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