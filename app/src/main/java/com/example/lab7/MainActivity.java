package com.example.lab7;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewResult;
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mTextViewResult = findViewById(R.id.textViewOut);
        linearLayout = findViewById(R.id.linearLayout);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s1908649/cars.php")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }


            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (!response.isSuccessful()){
                    throw new IOException("unexpected code " + response);

                }else{
                    final String myResponse = response.body().string();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextViewResult.setText(myResponse);
                            processJSON(myResponse);
                        }
                    });
                }
            }
        });

    }

    public void processJSON(String json){
        try {
            JSONArray all = new JSONArray(json);
            for (int i=0; i<all.length(); i++){
                JSONObject item=all.getJSONObject(i);
                String Licence = item.getString("NUMBER");
                TextView textView = new TextView(this);
                switch (item.getString("CAR_ID")){
                    case "1":
                        textView.setId(R.id.car_1);
                        break;
                    case "2":
                        textView.setId(R.id.car_2);
                        break;
                    case "3":
                        textView.setId(R.id.car_3);
                        break;
                    default: textView.setId(R.id.car_4);
                }
                textView.setText(Licence);
                linearLayout.addView(textView);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
