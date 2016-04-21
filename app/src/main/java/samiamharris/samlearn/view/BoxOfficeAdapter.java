package samiamharris.samlearn.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import samiamharris.samlearn.R;
import samiamharris.samlearn.model.Movie;

/**
 * Created by SamMyxer on 4/14/16.
 */
public class BoxOfficeAdapter extends RecyclerView.Adapter<BoxOfficeHolder> {

    private List<Movie> boxOfficeMovies;

    public BoxOfficeAdapter(List<Movie> boxOfficeMovies) {
        this.boxOfficeMovies = boxOfficeMovies;
    }

    @Override
    public BoxOfficeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_box_office, parent, false);
        return new BoxOfficeHolder(view);
    }

    @Override
    public void onBindViewHolder(BoxOfficeHolder holder, int position) {
        Movie movie = boxOfficeMovies.get(position);
        holder.bindMovie(movie);
    }

    @Override
    public int getItemCount() {
        if(boxOfficeMovies != null) {
            return boxOfficeMovies.size();
        }
        return 0;
    }

    public void setData(List<Movie> movies) {
        boxOfficeMovies = movies;
    }

}
