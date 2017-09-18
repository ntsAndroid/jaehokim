package com.jaehokim87.android.mission04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    EditText inputMessage;
    TextView inputCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputMessage = (EditText) findViewById(R.id.Text1);
        inputCount = (TextView) findViewById(R.id.Text2);

        Button sendButton = (Button) findViewById(R.id.button1);
        sendButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String message = inputMessage.getText().toString();
                Toast.makeText(getApplicationContext(), "전송됨 " + message, Toast.LENGTH_SHORT).show();
            }
        });

        Button CloseButton = (Button) findViewById(R.id.button2);
        CloseButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });

        TextWatcher watcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                byte[] bytes = null;
                try {
                    bytes = charSequence.toString().getBytes("KSC5601");
                    int strCount = bytes.length;
                    inputCount.setText(strCount + " / 80 바이트");
                } catch (UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String str = editable.toString();
                try{
                    byte[] strBytes = str.getBytes("KSC5601");
                    if (strBytes.length > 80){
//                        editable.delete(editable.length()-2, editable.length()-1);
                    }
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        };
        inputMessage.addTextChangedListener(watcher);
    }
}
