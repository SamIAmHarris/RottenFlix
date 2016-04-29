package samiamharris.samlearn.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import samiamharris.samlearn.R;
import samiamharris.samlearn.api.gson.BoxOfficeSearchResponse;
import samiamharris.samlearn.api.retrofit.service.BoxOfficeService;
import samiamharris.samlearn.api.retrofit.DataManager;
import samiamharris.samlearn.model.Movie;
import samiamharris.samlearn.view.BoxOfficeAdapter;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_activity_RecyclerView)
    RecyclerView recyclerView;

    BoxOfficeAdapter boxOfficeAdapter;
    Subscription subscriptionReverse;
    Subscription subscriptionLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        boxOfficeAdapter = new BoxOfficeAdapter(Collections.emptyList());
        recyclerView.setAdapter(boxOfficeAdapter);

        makeCallToGetBoxOfficeMovies();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_search:
                Intent searchIntent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(searchIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void makeCallToGetBoxOfficeMovies() {
        BoxOfficeService service = DataManager.get().getBoxOfficeService();

        Call<BoxOfficeSearchResponse> call = service.getBoxOfficeMovies();
        call.enqueue(new Callback<BoxOfficeSearchResponse>() {
            @Override
            public void onResponse(Call<BoxOfficeSearchResponse> call,
                                   Response<BoxOfficeSearchResponse> response) {

                Observable<List<Movie>> listObservable =
                        response.body().getBoxOfficeMoviesObservable();

                subscriptionReverse = listObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(movies -> {
                            Collections.reverse(movies);
                            return movies;})
                        .subscribe(movies -> {
                            boxOfficeAdapter.setData(movies);
                            boxOfficeAdapter.notifyDataSetChanged();
                        });

                subscriptionLog = listObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .flatMap(movies -> Observable.from(movies))
                        .filter(movie -> !movie.getTitle().equals("Criminal"))
                        .take(7)
                        .doOnNext(movie -> Log.i("Wooo", movie.getSynopsis()))
                        .subscribe(movie -> {
                            Log.i("Wooo", movie.getTitle());
                        });
            }

            @Override
            public void onFailure(Call<BoxOfficeSearchResponse> call,
                                  Throwable t) {
                Log.i("Wooo", "no");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(subscriptionReverse != null && !subscriptionReverse.isUnsubscribed()) {
            subscriptionReverse.unsubscribe();
        }
        if(subscriptionLog != null && !subscriptionLog.isUnsubscribed()) {
            subscriptionLog.unsubscribe();
        }
    }
}
