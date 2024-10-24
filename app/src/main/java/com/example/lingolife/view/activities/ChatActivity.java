package com.example.lingolife.view.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lingolife.R;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;
    private List<String> messages = new ArrayList<>();
    private EditText messageInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Obtém o nome do perfil que foi passado através do Intent
        String profileName = getIntent().getStringExtra("profileName");

        // Exibe o nome do perfil no título ou em algum lugar da tela do chat
        TextView chatHeaderTextView = findViewById(R.id.chat_header);
        if (profileName != null) {
            chatHeaderTextView.setText("Chat com " + profileName);
        } else {
            chatHeaderTextView.setText("Chat com usuário desconhecido");
        }

        // Configura RecyclerView
        recyclerView = findViewById(R.id.recycler_view_messages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializa o adapter e o configura no RecyclerView
        chatAdapter = new ChatAdapter(messages);
        recyclerView.setAdapter(chatAdapter);

        // Configura o campo de entrada de mensagem e o botão de envio
        messageInput = findViewById(R.id.message_input);
        Button sendButton = findViewById(R.id.btn_send);
        sendButton.setOnClickListener(v -> sendMessage());
    }

    // Método para enviar mensagem
    private void sendMessage() {
        String message = messageInput.getText().toString().trim();
        if (!message.isEmpty()) {
            // Adiciona a mensagem à lista e atualiza o RecyclerView
            messages.add(message);
            chatAdapter.notifyItemInserted(messages.size() - 1);
            recyclerView.scrollToPosition(messages.size() - 1); // Faz scroll até a última mensagem
            messageInput.setText(""); // Limpa o campo de texto
        }
    }

    // Adapter para gerenciar mensagens no RecyclerView
    public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MessageViewHolder> {
        private List<String> messages;

        public ChatAdapter(List<String> messages) {
            this.messages = messages;
        }

        @Override
        public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Infla o layout do item de mensagem
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
            return new MessageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MessageViewHolder holder, int position) {
            // Define o conteúdo da mensagem no TextView
            String message = messages.get(position);
            holder.messageTextView.setText(message);
        }

        @Override
        public int getItemCount() {
            return messages.size();
        }

        // ViewHolder para manter a referência do TextView de mensagem
        class MessageViewHolder extends RecyclerView.ViewHolder {
            TextView messageTextView;

            MessageViewHolder(View itemView) {
                super(itemView);
                messageTextView = itemView.findViewById(R.id.text_message);
            }
        }
    }
}
