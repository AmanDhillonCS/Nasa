package com.example.nasa;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button searchButton;
    // Variables
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Image> imgList;
    private String Search;
    private EditText userSearchInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

        // Get User Input
        userSearchInput = findViewById(R.id.user_input);
        searchButton = findViewById(R.id.search_button);

        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Search = userSearchInput.getText().toString();
                fetchData(Search);

                // Create new list
                imgList = new ArrayList<>();
            }

        });


    }


    // JSON Object request to get the title and image from the API
    private void fetchData(String search) {
        String url = "https://images-api.nasa.gov/search?q=" + Search + "&media_type=image";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject collection = response.getJSONObject("collection");
                    JSONArray itemArray = collection.getJSONArray("items");

                    for (int i = 0; i < itemArray.length(); i++) {
                        JSONObject eachItem = itemArray.getJSONObject(i);

                        JSONArray links = eachItem.getJSONArray("links");
                        JSONArray data = eachItem.getJSONArray("data");

                        String title = null;
                        for (int j = 0; j < data.length(); j++) {
                            JSONObject innerData = data.getJSONObject(j);
                            title = innerData.getString("title");
                        }

                       String imgurl = null;
                        for (int k = 0; k < links.length(); k++) {
                            JSONObject link = links.getJSONObject(k);
                            imgurl = link.getString("href");
                        }

                        Image item = new Image(imgurl, title);
                        imgList.add(item);
                    }

                    ImageAdapter adapter = new ImageAdapter(MainActivity.this, imgList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}