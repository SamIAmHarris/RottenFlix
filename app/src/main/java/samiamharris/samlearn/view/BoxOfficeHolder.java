package samiamharris.samlearn.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import samiamharris.samlearn.R;
import samiamharris.samlearn.model.Movie;

/**
 * Created by SamMyxer on 4/14/16.
 */
public class BoxOfficeHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener{

    private Movie movie;

    @BindView(R.id.row_box_office_titleTextView)
    TextView titleTextView;
    @BindView(R.id.row_box_office_yearTextView)
    TextView yearTextView;
    @BindView(R.id.row_box_office_criticsScoreTextView)
    TextView criticsScoreTextView;
    @BindView(R.id.row_box_office_audienceScoreTextView)
    TextView audienceScoreTextView;
    @BindView(R.id.row_box_office_thumbnailImageView)
    ImageView thumbnailImageView;

    public BoxOfficeHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        ButterKnife.bind(this, itemView);
    }

    public void bindMovie(Movie movie) {
        titleTextView.setText(movie.getTitle());
        yearTextView.setText(String.valueOf(movie.getYear()));
        criticsScoreTextView.setText("Critics Score: " +
                String.valueOf(movie.getRatings().getCriticsScore()) + "%");
        audienceScoreTextView.setText("Audience Score " +
                String.valueOf(movie.getRatings().getAudeinceScore()) + "%");

        Glide
                .with(itemView.getContext())
                .load(movie.getPosters().getThumbnail())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(thumbnailImageView);
    }

    @Override
    public void onClick(View v) {
    }
}
