package com.example.lingol;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {

    private EditText messageInput;
    private Button sendButton;
    private ListView chatListView;
    private ArrayList<String> chatMessages;
    private ArrayAdapter<String> chatAdapter;

    private DatabaseReference mDatabase;
    private String senderId; // ID do usuário autenticado
    private String receiverId; // ID do outro usuário no chat

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Inicializa os componentes da tela
        messageInput = findViewById(R.id.messageInput);
        sendButton = findViewById(R.id.sendButton);
        chatListView = findViewById(R.id.chatListView);

        // Inicializa a lista de mensagens
        chatMessages = new ArrayList<>();
        chatAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, chatMessages);
        chatListView.setAdapter(chatAdapter);

        // Recupera IDs do sender e receiver
        senderId = FirebaseAuth.getInstance().getUid(); // Obtém o ID do usuário autenticado
        receiverId = getIntent().getStringExtra("receiverId"); // Recebe o ID do destinatário pela Intent

        if (senderId == null || receiverId == null) {
            Toast.makeText(this, "Erro ao carregar IDs dos usuários", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Debug para verificar os IDs
        Log.d("ChatActivity", "senderId: " + senderId);
        Log.d("ChatActivity", "receiverId: " + receiverId);

        // Gera um identificador único para o chat entre os dois usuários
        String chatId = generateChatId(senderId, receiverId);

        // Referência ao Firebase Realtime Database para a conversa específica
        mDatabase = FirebaseDatabase.getInstance().getReference("chats").child(chatId);

        // Configura o botão de envio
        sendButton.setOnClickListener(v -> {
            String messageText = messageInput.getText().toString().trim();
            if (!messageText.isEmpty()) {
                // Criar a mensagem com o timestamp atual
                String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                String messageId = mDatabase.push().getKey(); // Gera um ID único para a mensagem
                Message message = new Message(messageId, messageText, senderId, timestamp);

                // Enviar a mensagem para o Firebase
                if (messageId != null) {
                    mDatabase.child(messageId).setValue(message); // Salva a mensagem no nó correto
                    messageInput.setText(""); // Limpa o campo de entrada
                    Toast.makeText(ChatActivity.this, "Mensagem enviada", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Listener para ler mensagens em tempo real
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                // Recupera a mensagem e a adiciona à lista
                Message message = snapshot.getValue(Message.class);
                if (message != null) {
                    String displayMessage = (message.getSenderId().equals(senderId) ? "Você" : "Outro") + ": " + message.getMessageText();
                    chatMessages.add(displayMessage);
                    chatAdapter.notifyDataSetChanged();
                    chatListView.setSelection(chatMessages.size() - 1); // Rola para a última mensagem
                }
            }

            @Override public void onChildChanged(DataSnapshot snapshot, String previousChildName) {}
            @Override public void onChildRemoved(DataSnapshot snapshot) {}
            @Override public void onChildMoved(DataSnapshot snapshot, String previousChildName) {}
            @Override public void onCancelled(DatabaseError error) {}
        });
    }

    // Função para gerar um identificador único para o chat entre dois usuários
    private String generateChatId(String senderId, String receiverId) {
        // Garante que o ID do chat será sempre o mesmo, independentemente da ordem dos usuários
        return senderId.compareTo(receiverId) < 0 ? senderId + "_" + receiverId : receiverId + "_" + senderId;
    }
}
