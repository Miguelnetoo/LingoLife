package com.example.lingolife.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lingolife.R;

public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Obtém o nome do perfil que foi passado através do Intent
        String profileName = getIntent().getStringExtra("profileName");

        // Encontra o TextView e define o nome do usuário
        TextView nameTextView = findViewById(R.id.Nameuser);
        if (profileName != null) {
            nameTextView.setText(profileName);
        } else {
            nameTextView.setText("Usuário desconhecido");
        }

        // Configura o botão "Enviar Mensagem"
        Button sendMessageButton = findViewById(R.id.btn_send_message);
        sendMessageButton.setOnClickListener(v -> {
            // Inicia a atividade de chat e passa o nome do perfil
            openChatActivity(profileName);
        });
    }

    private void openChatActivity(String profileName) {
        Intent intent = new Intent(UserProfileActivity.this, ChatActivity.class);
        intent.putExtra("profileName", profileName); // Passa o nome do usuário para a atividade de chat
        startActivity(intent);
    }
}
