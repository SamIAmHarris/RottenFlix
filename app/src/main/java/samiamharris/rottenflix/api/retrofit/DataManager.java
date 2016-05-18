package samiamharris.rottenflix.api.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import samiamharris.rottenflix.api.gson.BoxOfficeDeserializer;
import samiamharris.rottenflix.api.gson.ReviewServiceDeserializer;
import samiamharris.rottenflix.api.retrofit.service.BoxOfficeService;
import samiamharris.rottenflix.api.retrofit.service.ReviewService;
import samiamharris.rottenflix.api.retrofit.service.SearchService;
import samiamharris.rottenflix.api.retrofit.service.MovieService;
import samiamharris.rottenflix.api.retrofit.service.SimilarService;
import samiamharris.rottenflix.util.Constants;

/**
 * Created by SamMyxer on 4/14/16.
 */
public class DataManager {

    public BoxOfficeService getBoxOfficeService() {
        Gson gson = getBoxOfficeDeserializerGson();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .client(getLoggingClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(BoxOfficeService.class);
    }

    public SearchService getSearchService() {
        Gson gson = getBoxOfficeDeserializerGson();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .client(getLoggingClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(SearchService.class);
    }

    public MovieService getMovieService() {
        Gson gson = getBoxOfficeDeserializerGson();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .client(getLoggingClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(MovieService.class);
    }

    public ReviewService getReviewService() {
        Gson gson = getReviewResponseDeserializerGson();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .client(getLoggingClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(ReviewService.class);
    }

    public SimilarService getSimilarService() {
        Gson gson = getBoxOfficeDeserializerGson();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .client(getLoggingClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(SimilarService.class);
    }

    public Gson getBoxOfficeDeserializerGson() {
        GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(
                BoxOfficeService.class, new BoxOfficeDeserializer());
        return gsonBuilder.create();
    }

    public Gson getReviewResponseDeserializerGson() {
        GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(
                ReviewService.class, new ReviewServiceDeserializer());
        return gsonBuilder.create();
    }

    public OkHttpClient getLoggingClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }


}
