package samiamharris.samlearn.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import samiamharris.samlearn.R;
import samiamharris.samlearn.model.Movie;

/**
 * Created by SamMyxer on 4/14/16.
 */
public class BoxOfficeHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener{

    private Movie movie;

    @Bind(R.id.row_box_office_titleTextView)
    TextView titleTextView;
    @Bind(R.id.row_box_office_yearTextView)
    TextView yearTextView;
    @Bind(R.id.row_box_office_criticsScoreTextView)
    TextView criticsScoreTextView;
    @Bind(R.id.row_box_office_audienceScoreTextView)
    TextView audienceScoreTextView;
    @Bind(R.id.row_box_office_thumbnailImageView)
    ImageView thumbnailImageView;

    public BoxOfficeHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        ButterKnife.bind(this, itemView);
    }

    public void bindMovie(Movie movie) {
        titleTextView.setText(movie.getTitle());
        yearTextView.setText(String.valueOf(movie.getYear()));
        criticsScoreTextView.setText(
                String.valueOf(movie.getRatings().getCriticsScore()));
        audienceScoreTextView.setText(
                String.valueOf(movie.getRatings().getAudeinceScore()));

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
