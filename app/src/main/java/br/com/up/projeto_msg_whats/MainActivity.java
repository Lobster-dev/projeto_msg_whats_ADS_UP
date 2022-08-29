package br.com.up.projeto_msg_whats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    //Variable referent to cell number
    TextInputLayout   cell_number_layout;
    TextInputEditText cell_number_text;

    //Variable referent to message to be send
    TextInputLayout   message_input_layout;
    TextInputEditText message_input_text;

    //Button
    Button            button_send_message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cell_number_layout   = findViewById(R.id.cell_number_layout);
        cell_number_text     = findViewById(R.id.cell_number_text);

        message_input_layout = findViewById(R.id.message_input_layout);
        message_input_text   = findViewById(R.id.message_input_text);

        button_send_message  = findViewById(R.id.button_send_message);

        button_send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

    }

    public void sendMessage() {
        String cellNumber = cell_number_text.getText().toString();
        String message    = message_input_text.getText().toString();

        if(!cellNumber.isEmpty() && !message.isEmpty()){

            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/"+cellNumber+"?text="+message));
            startActivity(i);


        }else{
            Toast.makeText(MainActivity.this,"Por favor preencha todos os campos",Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isWhatappInstalled(){

        PackageManager packageManager = getPackageManager();
        boolean whatsappInstalled;

        try {

            packageManager.getPackageInfo("com.whatsapp",PackageManager.GET_ACTIVITIES);
            whatsappInstalled = true;


        }catch (PackageManager.NameNotFoundException e){

            whatsappInstalled = false;

        }

        return whatsappInstalled;

    }
}