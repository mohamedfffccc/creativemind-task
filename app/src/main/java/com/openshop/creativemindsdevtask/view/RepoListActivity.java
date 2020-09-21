package com.openshop.creativemindsdevtask.view;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.openshop.creativemindsdevtask.R;
import com.openshop.creativemindsdevtask.adapter.GithubAdapter;
import com.openshop.creativemindsdevtask.data.model.githubSquare.GithubSquare;
import com.openshop.creativemindsdevtask.data.service.MyService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.openshop.creativemindsdevtask.data.helper.HelperMethod.disappearKeypad;
import static com.openshop.creativemindsdevtask.data.api.RetrofitClient.getClient;

public class RepoListActivity extends AppCompatActivity {

    GithubAdapter adapter;
    ArrayList<GithubSquare> data;

    RecyclerView mainRvList;
    @BindView(R.id.main_iv_search)
    ImageView mainIvSearch;
    @BindView(R.id.main_ed_query)
    EditText mainEdQuery;
    @BindView(R.id.main_iv_delete)
    ImageView mainIvDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainRvList = findViewById(R.id.main_rv_list);

        getGithubList();
        sendBroadcast();
        mainEdQuery.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    filterData(v.getText().toString());
                    disappearKeypad(RepoListActivity.this, v);

                    return true;
                }
                return true;
            }
        });
    }

    private void getGithubList() {
        data = new ArrayList<>();
        mainRvList.setLayoutManager(new LinearLayoutManager(RepoListActivity.this));
        getClient().getSquare().enqueue(new Callback<List<GithubSquare>>() {
            @Override
            public void onResponse(Call<List<GithubSquare>> call, Response<List<GithubSquare>> response) {
                try {
                    data.addAll(response.body());
                    adapter = new GithubAdapter(response.body(), RepoListActivity.this);
                    mainRvList.setAdapter(adapter);
                } catch (Exception e) {
                    Toast.makeText(RepoListActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<GithubSquare>> call, Throwable t) {
                Toast.makeText(RepoListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_pagination:
                startActivity(new Intent(RepoListActivity.this, GithubPagination.class));

                break;
            case R.id.main_design_task:

                startActivity(new Intent(RepoListActivity.this, DesignTaskActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //TODO notify updates
    void sendBroadcast() {
        Intent i = new Intent(RepoListActivity.this, MyService.class);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, i, 0);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        String time = simpleDateFormat.format(new Date());
        String[] times = time.split(":");
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(times[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(times[1]));
        calendar.set(Calendar.SECOND, 00);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_HOUR, pendingIntent);
    }
    //TODO filter
    void filterData(String text)
    {
        for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getName().contains(text))
                {
  //                  data.clear();
//                    data.add(data.get(i));
                    adapter.notifyDataSetChanged();
                }
        }
    }

    @OnClick(R.id.main_iv_delete)
    public void onViewClicked() {
        mainEdQuery.setText("");
    }
}