package com.example.instagramclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    EditText username,fullname,email,password;
    Button register;
    TextView textlogin;
    FirebaseAuth auth;
    DatabaseReference reference;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username=(EditText)findViewById(R.id.Username);
        fullname=(EditText)findViewById(R.id.fullname);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        register=(Button)findViewById(R.id.register);
        textlogin=(TextView)findViewById(R.id.text_login);

        auth=FirebaseAuth.getInstance();

        textlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd=new ProgressDialog(RegisterActivity.this);
                pd.setMessage("Please wait...");
                pd.show();

                String str_username=username.getText().toString();
                String str_fullname=fullname.getText().toString();
                String str_email=email.getText().toString();
                String str_password=password.getText().toString();
                if(TextUtils.isEmpty(str_username)||TextUtils.isEmpty(str_fullname)||TextUtils.isEmpty(str_email)||TextUtils.isEmpty(str_password))
                {
                    Toast.makeText(RegisterActivity.this,"ALL Fields are required",Toast.LENGTH_SHORT).show();
                }else if (str_password.length()<6){
                    Toast.makeText(RegisterActivity.this, "Password must have 6 characters", Toast.LENGTH_SHORT).show();
                }else {

                    register(str_username,str_fullname,str_email,str_password);
                }
            }
        });
    }
    private void register(final String username, final String fullname, final String email, final String password){
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            final FirebaseUser firebaseUser=auth.getCurrentUser();
                            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, "check your email for verification", Toast.LENGTH_SHORT).show();
                                        String userid = firebaseUser.getUid();
                                        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(userid);
                                        HashMap<String, Object> hashMap = new HashMap<>();
                                        hashMap.put("id", userid);
                                        hashMap.put("username", username.toLowerCase());
                                        hashMap.put("fullname", fullname);
                                        hashMap.put("bio", "");
                                        hashMap.put("imageurl", "https://firebasestorage.googleapis.com/v0/b/instagram-clone-db971.appspot.com/o/sss.png?alt=media&token=bf9d7c94-0bc7-4f08-b1fb-1cbaee54d865");
                                        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Users1").child(userid);

                                        HashMap<String, String> hashMap1 = new HashMap<>();
                                        hashMap1.put("id", userid);
                                        hashMap1.put("username", username);
                                        hashMap1.put("imageurl", "https://firebasestorage.googleapis.com/v0/b/instagram-clone-db971.appspot.com/o/sss.png?alt=media&token=bf9d7c94-0bc7-4f08-b1fb-1cbaee54d865");
                                        hashMap1.put("status", "offline");
                                        hashMap1.put("search", username.toLowerCase());
                                        reference1.setValue(hashMap1);

                                        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {

                                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    startActivity(intent);
                                                    pd.dismiss();
                                                }

                                            }
                                        });
                                    }else {
                                        Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }else {
                            pd.dismiss();
                            Toast.makeText(RegisterActivity.this, "You can't register with this email and password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}