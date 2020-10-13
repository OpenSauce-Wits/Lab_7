package com.example.lab_5;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.*;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TextView txt;
    private OkHttpClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        client = new OkHttpClient();
        txt = findViewById(R.id.txtMsg);
        Button get = findViewById(R.id.getButton);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRequest("https://lamp.ms.wits.ac.za/mc/test.php");
            }
        });
        Button post = findViewById(R.id.postButton);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postRequest("https://lamp.ms.wits.ac.za/mc/test2.php");
            }
        });

        Button getWrong = findViewById(R.id.getWrongButton);
        getWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRequest("https://lamp.ms.wits.ac.za/mc/testooo.php");
            }
        });
        Button postWrong = findViewById(R.id.postWrongButton);
        postWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postRequest("https://lamp.ms.wits.ac.za/mc/test2dsafdsf.php");
            }
        });

    }

    private void getRequest(String requestString){
        Request request = new Request.Builder().url(requestString).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                txt.setText("Connection failed: " + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {

                final String responseData = response.body().string();
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                            if (!response.isSuccessful()){
                                txt.setText("Connection failed " + response);
                            }
                            else {
                                txt.setText(responseData);
                            }
                    }
                });
            }
        });
    }

    private void postRequest(String postString){
        HttpUrl.Builder urlBuilder = HttpUrl.parse(postString).newBuilder();
        urlBuilder.addQueryParameter("username","pravesh");
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                txt.setText("Connection failed: " + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {

                final String responseData = response.body().string();
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                            if (!response.isSuccessful()){
                                txt.setText("Connection failed " + response);
                            }
                            else {
                                txt.setText(responseData);
                            }
                    }
                });
            }
        });
    }


}
