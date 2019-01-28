package com.thanh.tran.structure;

import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.newrelic.agent.android.NewRelic;
import com.newrelic.agent.android.logging.AgentLog;
import com.thanh.tran.structure.di.retrofit.APIInterface;
import com.thanh.tran.structure.pojo.StarWars;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Inject
    APIInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NewRelic.withApplicationToken("AA2fa2034af686e0fbe9fbb08443713a73fb28c77d")
                .withLogLevel(AgentLog.AUDIT)
                .withInteractionTracing(true)
                .start(getApplication());
        NewRelic.setAttribute("Screen", "MainActivity");
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ((MyApplication) getApplication()).getApplicationComponent().inject(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                apiInterface.getPeople("json").enqueue(new Callback<StarWars>() {
                    @Override
                    public void onResponse(Call<StarWars> call, Response<StarWars> response) {
                        System.out.println("thanhtd" + response);
                    }

                    @Override
                    public void onFailure(Call<StarWars> call, Throwable t) {

                    }
                });
            }
        });

        Observable observable = Observable.fromArray(1, 2, 3, "a", 5, 6);
        Disposable disposable = observable.subscribe(number -> {
            System.out.println("thanh: " + number);
        }, num -> {
            System.out.println("thanh: second + " + num);
        });


        disposable.dispose();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
