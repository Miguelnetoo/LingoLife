package com.example.lingolife.view.activities;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lingolife.R;

public class ForumActivity extends AppCompatActivity {

    private Button postButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);  // Associa com o layout activity_forum.xml

        // Encontra o botão de "Publicar Material"
        postButton = findViewById(R.id.post_button);

        // Configura o listener para abrir o modal
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chama o método para abrir o modal
                openPostMaterialDialog();
            }
        });
    }

    private void openPostMaterialDialog() {
        // Infla o layout personalizado para o diálogo
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_material, null);

        // Configura o AlertDialog
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setView(dialogView);

        // Elementos do layout do diálogo
        EditText materialTitle = dialogView.findViewById(R.id.material_title);
        EditText materialDescription = dialogView.findViewById(R.id.material_description);

        dialogBuilder
                .setTitle("Publicar Material")
                .setPositiveButton("Publicar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Aqui você pode capturar os dados do usuário e salvar a publicação
                        String title = materialTitle.getText().toString();
                        String description = materialDescription.getText().toString();

                        // Exemplo de ação ao publicar
                        Toast.makeText(ForumActivity.this, "Material Publicado: " + title, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss(); // Fecha o diálogo
                    }
                });

        // Cria e exibe o AlertDialog
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }
}
