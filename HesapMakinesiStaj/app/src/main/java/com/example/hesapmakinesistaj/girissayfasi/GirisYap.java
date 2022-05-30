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

public class GirisYap extends AppCompatActivity {

    TextView uyeOlBtn ;
    TextInputEditText mailInput, passwordInput;
    String mailText = "";
    String  passwordText = "";
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    Button girisYapButton ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_yap);
        uyeOlBtn =  findViewById(R.id.uyeOlBtn);
        mailInput = findViewById(R.id.mailInput);
        passwordInput = findViewById(R.id.passwordInput);
        girisYapButton = findViewById(R.id.girisYapButton);
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        setOnClicks();
        kontrolUser();


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
        uyeOlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), KayitOl.class));
            }
        });
        girisYapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInputText();
                if(mailText.isEmpty() || passwordText.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Tüm alanları doldurunuz",Toast.LENGTH_SHORT).show();
                }else{
                    //Firebase
                    kullaniciGiris();
                }
            }
        });
    }

    private void kullaniciGiris() {
        mAuth.signInWithEmailAndPassword(mailText,passwordText).addOnCompleteListener(
                GirisYap.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Giriş başarılı",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Anasayfa.class));
                        }else{
                            Toast.makeText(getApplicationContext(),"Giriş bilgilerinizi kontrol ediniz",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}