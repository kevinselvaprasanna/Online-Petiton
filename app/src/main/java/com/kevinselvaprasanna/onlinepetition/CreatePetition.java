package com.kevinselvaprasanna.onlinepetition;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kevinselvaprasanna.onlinepetition.Network.PostRequest;
import com.kevinselvaprasanna.onlinepetition.Objects.PostParam;
import com.kevinselvaprasanna.onlinepetition.Objects.UserProfile;

import org.json.JSONObject;

import java.util.ArrayList;

public class CreatePetition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_petition);
        Button create = (Button)findViewById(R.id.create);
        final EditText ethead = (EditText)findViewById(R.id.ethead);
        final EditText etcontent = (EditText)findViewById(R.id.etcontent);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = ethead.getText().toString();
                String content = etcontent.getText().toString();
                CreateTask ct = new CreateTask(head,content);
                ct.execute();

            }
        });
    }
    public class CreateTask extends AsyncTask<Void, Void, Boolean> {
        private final String head;
        private final String content;

        CreateTask(String head,String content) {
            this.head = head;
            this.content = content;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            Intent i = new Intent(CreatePetition.this, LoginActivity.class);
            startActivity(i);
            finish();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            ArrayList<PostParam> PostParams = new ArrayList<PostParam>();
            PostParam post = new PostParam("name", UserProfile.getName(CreatePetition.this));
            PostParam post2 = new PostParam("head",head);
            PostParam post3 = new PostParam("content",content);
            PostParams.add(post);
            PostParams.add(post2);
            PostParams.add(post3);
            JSONObject ResponseJSON = PostRequest.execute("http://onlinepetition.comli.com/onlinepetition/newpet.php", PostParams, null);
            Log.d("RESPONSE", ResponseJSON.toString());
            return null;
        }
    }
}
