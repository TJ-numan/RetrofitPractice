package com.tjnuman.retrofitpostget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostDataActivity extends AppCompatActivity {
    TextView textView;
    Button buttonPost;
    EditText userid,title,text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_data);

    buttonPost = findViewById(R.id.buttonPost);
    textView = findViewById(R.id.posttextView);
    userid = findViewById(R.id.userid);
    title = findViewById(R.id.title);
    text = findViewById(R.id.text);

    buttonPost.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            creatPost();
        }
    });


    }

    private void creatPost() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        PostModel postModel = new PostModel();
        postModel.setUserId(Integer.parseInt(userid.getText().toString()));
        postModel.setText(text.getText().toString());
        postModel.setTitlel(title.getText().toString());
        Call<PostModel> call = jsonPlaceHolderApi.creatPost(postModel);

        call.enqueue(new Callback<PostModel>() {
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
}