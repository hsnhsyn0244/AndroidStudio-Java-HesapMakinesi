package com.example.hesapmakinesistaj.anasayfa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hesapmakinesistaj.R;
import com.example.hesapmakinesistaj.girissayfasi.GirisYap;
import com.google.firebase.auth.FirebaseAuth;

public class Anasayfa extends AppCompatActivity {
    Button sifir, bir, iki, uc, dort, bes, alti, yedi, sekiz, dokuz, toplama, cikarma, carpma, bolme, silme, temizle, esittir, del;
    EditText ekran;
    double ilkSayi;
    String islemDurum = "0";

    TextView anasayfaText ;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa);
        sifir = findViewById(R.id.sifir);
        bir = findViewById(R.id.bir);
        iki = findViewById(R.id.iki);
        uc = findViewById(R.id.üç);
        dort = findViewById(R.id.dört);
        bes = findViewById(R.id.beş);
        alti = findViewById(R.id.altı);
        yedi = findViewById(R.id.yedi);
        sekiz = findViewById(R.id.sekiz);
        dokuz = findViewById(R.id.dokuz);
        toplama = findViewById(R.id.toplama);
        cikarma = findViewById(R.id.cıkarma);
        carpma = findViewById(R.id.carpma);
        bolme = findViewById(R.id.bölme);
        silme = findViewById(R.id.silme);
        temizle = findViewById(R.id.c);
        ekran = findViewById(R.id.ekran);
        esittir = findViewById(R.id.esittir);
        ekran.setText("0");
        del = findViewById(R.id.silme);
        mAuth = FirebaseAuth.getInstance();
        anasayfaText = findViewById(R.id.anasayfatext);
        setOnClicks();

    }

    private void setOnClicks() {
        sifir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numaraTikla(0);

            }
        });
        bir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numaraTikla(1);
            }
        });
        iki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numaraTikla(2);
            }
        });
        uc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numaraTikla(3);
            }
        });
        dort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numaraTikla(4);
            }
        });
        bes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numaraTikla(5);
            }
        });
        alti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numaraTikla(6);
            }
        });
        yedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numaraTikla(7);
            }
        });
        sekiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numaraTikla(8);
            }
        });
        dokuz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numaraTikla(9);
            }
        });
        toplama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sembolTikla("+");
            }
        });
        cikarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sembolTikla("-");
            }
        });
        carpma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sembolTikla("*");
            }
        });
        bolme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sembolTikla("/");
            }
        });
        temizle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sembolTikla("sıfırlama");
            }
        });
        esittir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sembolTikla("=");
            }
        });
        silme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sembolTikla("del");
            }
        });

        anasayfaText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Toast.makeText(getApplicationContext(),"Çıkıs Yapıldı",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), GirisYap.class));
            }
        });
    }
    private void numaraTikla(int sayi) {
        System.out.println("test" + ekran.getText().toString());
        if (ekran.getText().toString().equals("0")) {
            ekran.setText("");

        } else if (ekran.getText().toString().equals("+") || ekran.getText().toString().equals("-")

                || ekran.getText().toString().equals("*") || ekran.getText().toString().equals("/")) {

            ekran.setText("");
        }
        ekran.setText(ekran.getText() + String.valueOf(sayi));

    }

    private void sembolTikla(String sembol) {
        switch (sembol) {
            case "=":
                if (ekran.getText().toString() == "+" || ekran.getText().toString() == "*" || ekran.getText().toString() == "/" || ekran.getText().toString() == "-") {
                    ekran.setText("");
                } else {
                    switch (islemDurum) {
                        case "+":
                            ilkSayi = ilkSayi + Double.parseDouble(ekran.getText().toString());
                            ekran.setText(ilkSayi + "");
                            break;
                        case "-":
                            ilkSayi = ilkSayi - Double.parseDouble(ekran.getText().toString());
                            ekran.setText(ilkSayi + "");
                            break;
                        case "*":
                            ilkSayi = ilkSayi * Double.parseDouble(ekran.getText().toString());
                            ekran.setText(ilkSayi + "");
                            break;
                        case "/":
                            ilkSayi = ilkSayi / Double.parseDouble(ekran.getText().toString());
                            ekran.setText(ilkSayi + "");
                            break;

                        default:
                            ekran.setText(ilkSayi + "");
                    }

                }

                break;
            case "sıfırlama":
                ilkSayi = 0.0;
                ekran.setText("0");
                islemDurum = "0";

                break;
            case "del":
                String ekranText = ekran.getText().toString();
                String yeniText = "";
                if (ekranText.length() > 0) {
                    yeniText = ekranText.substring(0, ekranText.length() - 1);
                    ekran.setText(yeniText);
                }
                break;
            default: {
                if (ekran.getText().toString() == "+" || ekran.getText().toString() == "*" || ekran.getText().toString() == "/" || ekran.getText().toString() == "-") {
                    if (islemDurum == "*" || islemDurum == "/") {
                        ilkSayi = -1 * ilkSayi;
                    }

                    ekran.setText(sembol);
                    islemDurum = sembol;
                } else {
                    ilkSayi = Double.parseDouble(ekran.getText().toString());
                    ekran.setText(sembol);
                    islemDurum = sembol;
                }
            }
            break;

        }

    }
}