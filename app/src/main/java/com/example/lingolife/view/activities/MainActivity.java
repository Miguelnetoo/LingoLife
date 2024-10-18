package com.example.lingolife.view.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lingolife.R;
import com.example.lingolife.utils.SessionManager;

public class MainActivity extends AppCompatActivity {

    private float dX, dY;
    private int lastAction;
    private ImageView additionalImage1, image; // Referências para imagens
    private ImageView optionEnglish, optionSpanish, optionFrench; // Referências para idiomas
    private TextView optionWork, optionTravel, optionFun; // Referências para opções de trabalho, viagem e diversão

    @SuppressLint({"ClickableViewAccessibility", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicializa o gerenciador de sessão
        SessionManager sessionManager = new SessionManager(this);

        // Verifica se o usuário está logado
        if (sessionManager.isLoggedIn()) {
            // Se o usuário já está logado, redireciona para a tela de perfil
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish(); // Finaliza a MainActivity para que o usuário não possa voltar
            return;
        }

        // Exibe a tela de login/registro se não estiver logado
        setContentView(R.layout.activity_main);

        // Ajusta a barra de sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referência dos botões e imagens
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);
        ImageView bannerImage = findViewById(R.id.banner_image); // Referência ao ImageView que será móvel
        additionalImage1 = findViewById(R.id.additional_image1); // Referência ao additional_image1
        image = findViewById(R.id.image); // Referência ao image

        // Referência para os ImageViews de idiomas
        optionEnglish = findViewById(R.id.option_english);
        optionSpanish = findViewById(R.id.option_spanish);
        optionFrench = findViewById(R.id.option_french);

        // Referência para as TextViews de opções (Trabalho, Viagem, Diversão)
        optionWork = findViewById(R.id.option_work);
        optionTravel = findViewById(R.id.option_travel);
        optionFun = findViewById(R.id.option_fun);

        // Ação do botão de Login
        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        // Ação do botão de Registro
        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });

        // Ação da ImageView additional_image1 - Redireciona para a UserListActivity
        additionalImage1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, UserListActivity.class);
            startActivity(intent);
        });

        // Ação da ImageView additional_image2 - Redireciona para a ForumActivity
        image.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ForumActivity.class);
            startActivity(intent);

        });

        // Defina a ação de clique para os ImageViews de idiomas
        optionEnglish.setOnClickListener(v -> openUserList());
        optionSpanish.setOnClickListener(v -> openUserList());
        optionFrench.setOnClickListener(v -> openUserList());

        // Defina a ação de clique para as opções (Trabalho, Viagem, Diversão)
        optionWork.setOnClickListener(v -> openUserList());
        optionTravel.setOnClickListener(v -> openUserList());
        optionFun.setOnClickListener(v -> openUserList());

        // Configura o OnTouchListener para o banner_image
        bannerImage.setOnTouchListener((view, event) -> {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    // Obter a posição inicial
                    dX = view.getX() - event.getRawX();
                    dY = view.getY() - event.getRawY();
                    lastAction = MotionEvent.ACTION_DOWN;
                    return true;

                case MotionEvent.ACTION_MOVE:
                    // Mover a view com base nos dados do toque
                    view.setX(event.getRawX() + dX);
                    view.setY(event.getRawY() + dY);
                    lastAction = MotionEvent.ACTION_MOVE;
                    return true;

                case MotionEvent.ACTION_UP:
                    // Soltar a view
                    if (lastAction == MotionEvent.ACTION_DOWN) {
                        // Caso seja apenas um clique, você pode realizar uma ação aqui
                    }
                    return true;

                default:
                    return false;
            }
        });
    }

    // Método para abrir a UserListActivity
    private void openUserList() {
        Intent intent = new Intent(MainActivity.this, UserListActivity.class);
        startActivity(intent);
    }
}
