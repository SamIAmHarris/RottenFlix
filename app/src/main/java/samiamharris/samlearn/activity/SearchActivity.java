package samiamharris.samlearn.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import samiamharris.samlearn.MovieApplication;
import samiamharris.samlearn.R;
import samiamharris.samlearn.api.retrofit.DataManager;
import samiamharris.samlearn.api.retrofit.service.SearchService;
import samiamharris.samlearn.util.SharedPrefManager;
import samiamharris.samlearn.view.BoxOfficeAdapter;

/**
 * Created by SamMyxer on 4/15/16.
 */
public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.activity_search_searchEditText)
    EditText searchEditText;
    @BindView(R.id.search_recyclerView)
    RecyclerView recyclerView;

    @Inject
    DataManager dataManager;

    @Inject
    SharedPrefManager sharedPrefManager;

    BoxOfficeAdapter boxOfficeAdapter;

    Observable<CharSequence> textObservable;

    Subscription subscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        ((MovieApplication)getApplication()).getComponent().inject(this);

        boxOfficeAdapter = new BoxOfficeAdapter(Collections.EMPTY_LIST);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(boxOfficeAdapter);

        SearchService service = dataManager.getSearchService();

        textObservable = RxTextView.textChanges(searchEditText);
        subscription = textObservable
                .debounce(1, TimeUnit.SECONDS)
                .filter(query -> query.toString().length() > 2)
                .doOnNext(new Action1<CharSequence>() {
                    @Override
                    public void call(CharSequence charSequence) {
                        sharedPrefManager.setMostRecentSearch(charSequence.toString());
                    }
                })
                .flatMap(charSequence -> service.searchMovies(charSequence.toString(), 7))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(boxOfficeSearchResponse -> {
                    boxOfficeAdapter.setData(boxOfficeSearchResponse.getBoxOfficeMovies());
                    boxOfficeAdapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), sharedPrefManager.getMostRecentSearch(),
                            Toast.LENGTH_LONG).show();
                }, throwable -> {
                    Log.i("SearchActivity", throwable.toString());
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
