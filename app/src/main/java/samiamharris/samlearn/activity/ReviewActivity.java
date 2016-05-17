package samiamharris.samlearn.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import samiamharris.samlearn.MovieApplication;
import samiamharris.samlearn.api.gson.ReviewResponse;
import samiamharris.samlearn.api.retrofit.DataManager;
import samiamharris.samlearn.api.retrofit.service.ReviewService;

/**
 * Created by SamMyxer on 4/28/16.
 */
public class ReviewActivity extends AppCompatActivity {

    private final static String ID_EXTRA = "reviewactivity.id";
    int id;
    Subscription subscription;

    @Inject
    DataManager dataManager;

    public static Intent newIntent(Context context, int id) {
        Intent intent = new Intent(context, ReviewActivity.class);
        intent.putExtra(ID_EXTRA, id);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MovieApplication)getApplication()).getComponent().inject(this);

        id = getIntent().getIntExtra(ID_EXTRA, 0);

        ReviewService reviewService = dataManager.getReviewService();

        subscription =
                reviewService.getReviews(id)
                        .distinct()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<ReviewResponse>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("ReviewActivity", e.toString());
                            }

                            @Override
                            public void onNext(ReviewResponse reviewResponse) {
                                Toast.makeText(getBaseContext(),
                                        reviewResponse.getReviewsList().get(0).getCritic(), Toast.LENGTH_SHORT).show();
                            }
                        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

    }

}
