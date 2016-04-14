package samiamharris.samlearn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import samiamharris.samlearn.api.gson.BoxOfficeDeserializer;
import samiamharris.samlearn.api.gson.BoxOfficeSearchResponse;
import samiamharris.samlearn.api.retrofit.BoxOfficeService;
import samiamharris.samlearn.model.Movie;
import samiamharris.samlearn.util.Constants;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.main_activity_boxOfficeButton)
    Button boxOfficeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(
                BoxOfficeService.class, new BoxOfficeDeserializer());
        Gson gson = gsonBuilder.create();

        boxOfficeButton.setOnClickListener(v -> {
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
                    Log.i("Main", "success");
                    List<Movie> movies = response.body().getBoxOfficeMovies();
                }

                @Override
                public void onFailure(Call<BoxOfficeSearchResponse> call, Throwable t) {
                    Log.i("Main", "success");
                }
            });
        });
    }
}
