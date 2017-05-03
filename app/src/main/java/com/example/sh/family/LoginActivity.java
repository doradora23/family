package com.example.sh.family;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.Iterator;

public class LoginActivity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText IDText = (EditText)findViewById(R.id.IDText);
        final EditText PasswordText = (EditText)findViewById(R.id.PasswordText);
        Button LoginButton = (Button)findViewById(R.id.LoginButton);
        TextView RegisterButton = (TextView)findViewById(R.id.RegisterButton);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterator<DataSnapshot> child = dataSnapshot.child("user").getChildren().iterator();
                        while(child.hasNext())
                        {
                            User tempUser = child.next().getValue(User.class);
                            if(tempUser.getUserID().equals(IDText.getText().toString()))
                            {
                                if(tempUser.getUserPassword().equals(PasswordText.getText().toString()))
                                {
                                    Toast.makeText(getApplicationContext(),"로그인!",Toast.LENGTH_LONG).show();

                                    Intent MainIntent = new Intent(LoginActivity.this, MainActivity.class);
                                    MainIntent.putExtra("loginUser", tempUser);
                                    LoginActivity.this.startActivity(MainIntent);
                                    return;
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"비밀번호가 틀립니다.",Toast.LENGTH_LONG).show();
                                    return;
                                }
                            }
                        }
                        Toast.makeText(getApplicationContext(),"존재하지 않는 아이디입니다.",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });

        RegisterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
    }
}
