package com.openshop.creativemindsdevtask.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.openshop.creativemindsdevtask.data.helper.OnEndLess;
import com.openshop.creativemindsdevtask.R;
import com.openshop.creativemindsdevtask.adapter.GithubAdapter;
import com.openshop.creativemindsdevtask.data.model.githubSquare.GithubSquare;
import com.openshop.creativemindsdevtask.data.model.room.room.RepoItem;
import com.openshop.creativemindsdevtask.data.model.room.room.RoomDao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.openshop.creativemindsdevtask.data.api.RetrofitClient.getClient;
import static com.openshop.creativemindsdevtask.data.model.room.room.RoomManger.getInistance;

public class GithubPagination extends AppCompatActivity {

    @BindView(R.id.pagination_rv_list)
    RecyclerView paginationRvList;
    ArrayList<GithubSquare> data;
    GithubAdapter adapter;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    private RoomDao roomDao;
    LinearLayoutManager linearLayoutManager;
    private OnEndLess onEndLess;
    private int Maxpage = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_pagination);
        ButterKnife.bind(this);
        data = new ArrayList<>();

        linearLayoutManager = new LinearLayoutManager(this);
        paginationRvList.setLayoutManager(linearLayoutManager);
        getPaginatedData(1);
        adapter = new GithubAdapter(data, GithubPagination.this);
        paginationRvList.setAdapter(adapter);

        onEndLess = new OnEndLess(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {

                if (current_page <= Maxpage) {
                    if (Maxpage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        getPaginatedData(current_page);
                        adapter.notifyDataSetChanged();
                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                } else {
                    onEndLess.current_page = onEndLess.previous_page;

                }


            }
        };

    }

    private void getPaginatedData(int i) {
        getClient().getMine(i, 10, "85700a8ab5ad5336a9314e30e9849b9973df6069")
                .enqueue(new Callback<List<GithubSquare>>() {
                    @Override
                    public void onResponse(Call<List<GithubSquare>> call, Response<List<GithubSquare>> response) {
                        try {
                            data.addAll(response.body());
                            addDataToRoom(data);

                            adapter.notifyDataSetChanged();

                        } catch (Exception e) {

                        }

                    }

                    @Override
                    public void onFailure(Call<List<GithubSquare>> call, Throwable t) {

                    }
                });
    }

    void addDataToRoom(ArrayList<GithubSquare> data) {
        roomDao = getInistance(this).roomDao();
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                roomDao.delAll();
                for (int i = 0; i < data.size(); i++) {
                    roomDao.addItem(new RepoItem(data.get(i).getName(),
                            data.get(i).getDescription(), data.get(i).getOwner().getLogin(), data.get(i).getHtmlUrl()
                            , data.get(i).getOwner().getHtmlUrl()));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        }
                    });

                }
            }
        });
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPaginatedData(1);
                swipe.setRefreshing(false);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.room:
                startActivity(new Intent(GithubPagination.this, CasheActivity.class));

                break;

        }
        return super.onOptionsItemSelected(item);
    }
}