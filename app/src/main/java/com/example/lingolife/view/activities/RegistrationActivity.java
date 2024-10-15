package com.example.lingolife.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lingolife.R;

public class RegistrationActivity extends AppCompatActivity {

    private EditText edtNome, edtNivelIdioma, edtEmail, edtPassword;
    private Button btnLogin, btnBack;
    private ImageButton btnProfilePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Inicializar os campos do layout
        edtNome = findViewById(R.id.edtNome);
        edtNivelIdioma = findViewById(R.id.edtNivelIdioma);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnBack = findViewById(R.id.btnBack);
        btnProfilePhoto = findViewById(R.id.btnProfilePhoto);

        // Ação do botão de voltar
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volta para a MainActivity ou a tela anterior
                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Fecha a atividade de registro
            }
        });

        // Ação do botão de login (envio do formulário)
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edtNome.getText().toString().trim();
                String nivelIdioma = edtNivelIdioma.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                // Validação básica dos campos
                if (TextUtils.isEmpty(nome)) {
                    edtNome.setError("Por favor, insira o nome");
                    return;
                }

                if (TextUtils.isEmpty(nivelIdioma)) {
                    edtNivelIdioma.setError("Por favor, insira o nível do idioma");
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    edtEmail.setError("Por favor, insira um e-mail");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    edtPassword.setError("Por favor, insira uma senha");
                    return;
                }

                // Simulação de cadastro bem-sucedido
                if (!TextUtils.isEmpty(nome) && !TextUtils.isEmpty(nivelIdioma) &&
                        !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                    Toast.makeText(RegistrationActivity.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();

                    // Redirecionar para a página de login ou perfil
                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish(); // Fechar a atividade de cadastro
                }
            }
        });

        btnProfilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aqui você pode implementar a lógica para abrir a galeria e selecionar uma imagem
                Toast.makeText(RegistrationActivity.this, "Selecionar foto de perfil", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
