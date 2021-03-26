package com.tjnuman.retrofitpostget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);



        button.setOnClickListener(v ->
                //getAlldata()
               // getSingledata()
                creatPost()
        );
    }

    private void creatPost() {
        PostModel postmodel =  new PostModel(46,"Title","this is a lot of text of the text and text and so on");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<PostModel> postModelCall = jsonPlaceHolderApi.creatPost( postmodel);

        postModelCall.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {

                if (!response.isSuccessful()){
                    textView.setText(response.code());
                }
                PostModel postresponce = response.body();
                String content = "";
                content += "CODE: " + response.code() + "\n\n";
                content += "ID: " + postresponce.getId() + "\n";
                content += "USER ID: " +postresponce.getUserId() + "\n";
                content += "TITLE: " + postresponce.getTitlel() + "\n";
                content += "TEXT: " + postresponce.getText() + "\n\n";
                textView.append(content);

            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                textView.setText(t.getMessage());

            }
        });


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