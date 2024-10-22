package com.example.lingolife.view.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.lingolife.R;

public class MaterialDialogActivity extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Criar um AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Publicar Material")
                .setMessage("Insira as informações necessárias para publicar seu material.")
                .setView(R.layout.dialog_material) // Um layout customizado que você vai criar
                .setPositiveButton("Publicar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Ação ao clicar em "Publicar"
                        // Aqui você pode pegar os valores inseridos no formulário
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Fecha o diálogo
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }
}
