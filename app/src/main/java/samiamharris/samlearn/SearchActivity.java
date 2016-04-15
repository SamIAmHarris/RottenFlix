package samiamharris.samlearn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Action1;
import samiamharris.samlearn.api.gson.BoxOfficeDeserializer;
import samiamharris.samlearn.api.gson.BoxOfficeSearchResponse;
import samiamharris.samlearn.api.retrofit.BoxOfficeService;
import samiamharris.samlearn.api.retrofit.SearchService;
import samiamharris.samlearn.util.Constants;

/**
 * Created by SamMyxer on 4/15/16.
 */
public class SearchActivity extends AppCompatActivity {

    @Bind(R.id.activity_search_searchEditText)
    EditText searchEditText;
    @Bind(R.id.search_recyclerView)
    RecyclerView recyclerView;

    Observable<String> textObservable = Observable.just(searchEditText.getText().toString());


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Debounce operator to wait 3 seconds before we search
                textObservable.subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        searchMovies(s);
                    }
                });

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void searchMovies(String query) {
        GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(
                BoxOfficeService.class, new BoxOfficeDeserializer());
        Gson gson = gsonBuilder.create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        SearchService service = retrofit.create(SearchService.class);

        Call<BoxOfficeSearchResponse> call = service.searchMovies(query, 30);
        call.enqueue(new Callback<BoxOfficeSearchResponse>() {
            @Override
            public void onResponse(Call<BoxOfficeSearchResponse> call,
                                   Response<BoxOfficeSearchResponse> response) {
                response.body().getBoxOfficeMovies();
                Log.i("Wooo", "yes");
            }

            @Override
            public void onFailure(Call<BoxOfficeSearchResponse> call,
                                  Throwable t) {
                Log.i("Wooo", "no");
            }
        });
    }
}
