package com.tjnuman.retrofitpostget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetDataActivity extends AppCompatActivity {

    TextView textView;
    Button getAlldata,getSingledata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        getAlldata = findViewById(R.id.button);
        getSingledata = findViewById(R.id.button2);


        getSingledata.setOnClickListener(v ->

                 getSingledata()

        );

        getAlldata.setOnClickListener(v ->
                getAlldata()

        );
    }

    private void getSingledata() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<PostModel> call = jsonPlaceHolderApi.getSinglePost();
        call.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                if (!response.isSuccessful()){
                    textView.setText(response.code());
                }
                PostModel postModel = response.body();
                String content = "";
                content += "ID: " + postModel.getId() + "\n";
                content += "USER ID: " + postModel.getUserId() + "\n";
                content += "TITLE: " + postModel.getTitlel() + "\n";
                content += "TEXT: " + postModel.getText() + "\n\n";
                 textView.setText(content);


            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });

    }


    private void getAlldata() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<PostModel>> call = jsonPlaceHolderApi.getPost();
        call.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if (!response.isSuccessful()) {
                    textView.setText(response.code());
                    return;
                }
                List<PostModel> postModels = response.body();
                for (PostModel postModel : postModels) {
                    String content = "";
                    content += "ID: " + postModel.getId() + "\n";
                    content += "USER ID: " + postModel.getUserId() + "\n";
                    content += "TITLE: " + postModel.getTitlel() + "\n";
                    content += "TEXT: " + postModel.getText() + "\n\n";
                    textView.append(content);
                    // textView.setText(content);
                }

            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                textView.setText(t.getMessage());

            }
        });

    }
}