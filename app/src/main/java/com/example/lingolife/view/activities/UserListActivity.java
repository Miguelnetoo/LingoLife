package com.example.lingolife.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lingolife.R;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {

    private List<String> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        // Inicializa a lista de usuários
        userList = new ArrayList<>();
        userList.add("Usuário 1");
        userList.add("Usuário 2");
        userList.add("Usuário 3");

        // Botões para visitar perfil
        Button visitar1 = findViewById(R.id.visitar1);
        Button visitar2 = findViewById(R.id.visitar2);
        Button visitar3 = findViewById(R.id.visitar3);
        ImageView logoUser = findViewById(R.id.logouser);



        visitar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserListActivity.this, UserProfileActivity.class);
                intent.putExtra("profileName", "Ana Pereira"); // Passe informações específicas se necessário
                startActivity(intent);
            }
        });

        visitar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserListActivity.this, UserProfileActivity.class);
                intent.putExtra("profileName", "Maria Silva");
                startActivity(intent);
            }
        });

        visitar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserListActivity.this, UserProfileActivity.class);
                intent.putExtra("profileName", "José Lucas");
                startActivity(intent);
            }
        });



        logoUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserListActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

    }
}
