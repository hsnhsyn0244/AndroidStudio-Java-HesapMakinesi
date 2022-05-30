package com.example.hesapmakinesistaj.girissayfasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hesapmakinesistaj.R;
import com.example.hesapmakinesistaj.anasayfa.Anasayfa;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class KayitOl extends AppCompatActivity {

    TextView girisYapBtn;
    TextInputEditText mailInput, passwordInput;
    String mailText = "";
    String  passwordText = "";
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    Button kayitOlButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol);

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        girisYapBtn  = findViewById(R.id.girisYapBtn);
        mailInput = findViewById(R.id.mailInput);
        passwordInput = findViewById(R.id.passwordInput);
        kayitOlButton = findViewById(R.id.kayitOlButton);
        kontrolUser();
        setOnClicks();


    }

    private void kontrolUser() {
        if(firebaseUser != null){
            startActivity(new Intent(getApplicationContext(), Anasayfa.class));
        }
    }

    private void getInputText() {
        mailText = mailInput.getText().toString();
        passwordText = passwordInput.getText().toString();
    }

    private void setOnClicks() {
        girisYapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), GirisYap.class));
            }
        });
        kayitOlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInputText();
                if(mailText.isEmpty() || passwordText.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Tüm alanları doldurunuz",Toast.LENGTH_SHORT).show();
                }else{
                    //Firebase
                    kullaniciOlustur();
                }
            }
        });
    }

    private void kullaniciOlustur() {
        mAuth.createUserWithEmailAndPassword(mailText,passwordText).addOnCompleteListener(
                KayitOl.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Kayıt başarılı",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Anasayfa.class));
                }else{
                    Toast.makeText(getApplicationContext(),"Kayıt başarılı değil",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}