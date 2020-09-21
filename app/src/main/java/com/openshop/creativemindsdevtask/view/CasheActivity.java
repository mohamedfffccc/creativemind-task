package com.openshop.creativemindsdevtask.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.openshop.creativemindsdevtask.R;
import com.openshop.creativemindsdevtask.adapter.RoomAdapter;
import com.openshop.creativemindsdevtask.data.model.room.room.RepoItem;
import com.openshop.creativemindsdevtask.data.model.room.room.RoomDao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.openshop.creativemindsdevtask.data.model.room.room.RoomManger.getInistance;

public class CasheActivity extends AppCompatActivity {

    @BindView(R.id.pagination_rv_list)
    RecyclerView paginationRvList;
    RoomAdapter adapter;
    private RoomDao roomDao;
    List<RepoItem> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme2);
        setContentView(R.layout.activity_github_pagination);
        ButterKnife.bind(this);
        getDataFromRoom();
    }

    private void getDataFromRoom() {
        roomDao = getInistance(this).roomDao();
        paginationRvList.setLayoutManager(new LinearLayoutManager(CasheActivity.this));
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                list = roomDao.getAll();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new RoomAdapter(list , CasheActivity.this);
                        paginationRvList.setAdapter(adapter);
                    }
                });
            }
        });


    }
}