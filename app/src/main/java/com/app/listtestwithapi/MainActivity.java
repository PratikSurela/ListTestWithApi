package com.app.listtestwithapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.app.listtestwithapi.adapter.RecyclerViewAdapter;
import com.app.listtestwithapi.model.Quotes;
import com.app.listtestwithapi.model.ResultItem;
import com.app.listtestwithapi.retrofit.ApiClient;
import com.app.listtestwithapi.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ArrayList<ResultItem> arrayList = new ArrayList();
    private RecyclerViewAdapter adapter;
    private Map<String, Integer> data = new HashMap<>();

    /*pagination vars start*/
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    int page = 0;
    int total_pages = 10, totalRecord, limit;
    /*pagination vars end*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        getQuoteList(true);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();
                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            if (page < total_pages) {
                                page++;
                                Log.e("MainActivity", "onScrolled: page : " + page);
                                getQuoteList(false);
                            }
                        }
                    }
                }
            }
        });
    }

    private void getQuoteList(final boolean clearFlag) {

        if (clearFlag) {
            arrayList.clear();
        }
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);

        data.put("page", page);
        Call<Quotes> call = service.getQuotes(data);
        call.enqueue(new Callback<Quotes>() {
            @Override
            public void onResponse(Call<Quotes> call, Response<Quotes> response) {
                arrayList.addAll(response.body().getResult());

                totalRecord = Integer.parseInt(response.body().getTotalRec());
                limit = response.body().getLimit();
                total_pages = totalRecord / limit;
                adapter.notifyDataSetChanged();
                loading = true;
            }

            @Override
            public void onFailure(Call<Quotes> call, Throwable t) {
                Log.e("MainActivity", "onFailure: error : " + t);
            }
        });
    }

    private void initViews() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new RecyclerViewAdapter(this, arrayList);
        recyclerView.setAdapter(adapter);
    }
}
