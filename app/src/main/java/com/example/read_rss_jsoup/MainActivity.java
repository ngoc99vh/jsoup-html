package com.example.read_rss_jsoup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.read_rss_jsoup.adapter.Adapter_Item_RSS;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edt_search;
    ImageButton btn_search;
    RecyclerView ryc_view_rss;
    Adapter_Item_RSS adapter_item_rss;
    ArrayList<RSS> listRss = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ExecuteRss().execute();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        ryc_view_rss.setLayoutManager(linearLayoutManager);


    }

    private void init() {
        edt_search = findViewById(R.id.search_url);
        btn_search = findViewById(R.id.search_url_button);
        ryc_view_rss = findViewById(R.id.ryc_view_rss);
    }

    private class ExecuteRss extends AsyncTask<Void, Void, Void> {
        String title;
        String link;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                // Connect to the web site
                String url = edt_search.getText().toString().trim();
                Document document = Jsoup.connect(url).get();
                Elements elements = document.select("ul.list-rss li a[href]");
                Log.d("test1:", String.valueOf(elements.size()));
                for (Element e : elements) {
                    title = e.attr("title");
                    link = url + e.attr("href");
                    Log.d("test2", title);
                    Log.d("test3", link);
                    listRss.add(new RSS(title, link));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            adapter_item_rss = new Adapter_Item_RSS(getApplicationContext(), listRss);
            ryc_view_rss.setAdapter(adapter_item_rss);

        }
    }
}
