package samiamharris.samlearn.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import samiamharris.samlearn.R;
import samiamharris.samlearn.api.gson.BoxOfficeDeserializer;
import samiamharris.samlearn.api.gson.BoxOfficeSearchResponse;
import samiamharris.samlearn.api.retrofit.BoxOfficeService;
import samiamharris.samlearn.api.retrofit.SearchService;
import samiamharris.samlearn.util.Constants;
import samiamharris.samlearn.view.BoxOfficeAdapter;

/**
 * Created by SamMyxer on 4/15/16.
 */
public class SearchActivity extends AppCompatActivity {

    @Bind(R.id.activity_search_searchEditText)
    EditText searchEditText;
    @Bind(R.id.search_recyclerView)
    RecyclerView recyclerView;

    BoxOfficeAdapter boxOfficeAdapter;

    Observable<CharSequence> textObservable;

    Subscription subscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        boxOfficeAdapter = new BoxOfficeAdapter(Collections.EMPTY_LIST);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(boxOfficeAdapter);

        GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(
                BoxOfficeService.class, new BoxOfficeDeserializer());
        Gson gson = gsonBuilder.create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        SearchService service = retrofit.create(SearchService.class);

        textObservable = RxTextView.textChanges(searchEditText);
        subscription = textObservable
                .debounce(1, TimeUnit.SECONDS)
                .filter(query -> query.toString().length() > 2)
                .flatMap(charSequence -> service.searchMovies(charSequence.toString(), 7))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BoxOfficeSearchResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("SearchActivity", e.toString());
                    }

                    @Override
                    public void onNext(BoxOfficeSearchResponse boxOfficeSearchResponse) {
                        boxOfficeAdapter.setData(boxOfficeSearchResponse.getBoxOfficeMovies());
                        boxOfficeAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
