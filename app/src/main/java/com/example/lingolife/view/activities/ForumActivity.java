package com.example.lingolife.view.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lingolife.R;

import java.util.ArrayList;
import java.util.List;

public class ForumActivity extends AppCompatActivity {

    private EditText newPostInput;
    private Button postButton;
    private RecyclerView forumRecyclerView;
    private List<String> postList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        // Inicializa os componentes
        newPostInput = findViewById(R.id.new_post_input);
        postButton = findViewById(R.id.post_button);
        forumRecyclerView = findViewById(R.id.forum_recycler_view);

        // Inicializa a lista de postagens
        postList = new ArrayList<>();

        // Configura o RecyclerView
        forumRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Ação do botão de postar
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPost = newPostInput.getText().toString();
                if (!newPost.isEmpty()) {
                    postList.add(newPost); // Adiciona a nova postagem à lista
                    newPostInput.setText(""); // Limpa o campo de entrada
                }
            }
        });
    }
}
