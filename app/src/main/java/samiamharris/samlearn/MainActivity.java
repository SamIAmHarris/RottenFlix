package samiamharris.samlearn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import samiamharris.samlearn.api.gson.BoxOfficeDeserializer;
import samiamharris.samlearn.api.gson.BoxOfficeSearchResponse;
import samiamharris.samlearn.api.retrofit.BoxOfficeService;
import samiamharris.samlearn.model.Movie;
import samiamharris.samlearn.util.Constants;
import samiamharris.samlearn.view.BoxOfficeAdapter;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.main_activity_RecyclerView)
    RecyclerView recyclerView;

    BoxOfficeAdapter boxOfficeAdapter;

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

    public void makeCallToGetBoxOfficeMovies() {
        GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(
                BoxOfficeService.class, new BoxOfficeDeserializer());
        Gson gson = gsonBuilder.create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        BoxOfficeService service = retrofit.create(BoxOfficeService.class);

        Call<BoxOfficeSearchResponse> call = service.getBoxOfficeMovies();
        call.enqueue(new Callback<BoxOfficeSearchResponse>() {
            @Override
            public void onResponse(Call<BoxOfficeSearchResponse> call,
                                   Response<BoxOfficeSearchResponse> response) {
                response.body().getBoxOfficeMovies().map(movies -> {
                    Collections.reverse(movies);
                    return movies;
                }).subscribe(movies -> {
                    boxOfficeAdapter.setData(movies);
                    boxOfficeAdapter.notifyDataSetChanged();
                });
            }

            @Override
            public void onFailure(Call<BoxOfficeSearchResponse> call,
                                  Throwable t) {
                Log.i("Wooo", "no");
            }
        });
    }
}
