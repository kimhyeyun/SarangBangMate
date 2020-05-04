package com.yuni.sarangbang;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Rating;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class RatingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_menureview);

        RatingBar rb_p = (RatingBar)findViewById(R.id.rb_p);
        final RatingBar rb_m = (RatingBar)findViewById(R.id.rb_m);
        final TextView tx_r = (TextView)findViewById(R.id.r_tx) ;

        rb_p.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rb_m.setRating(rating);
                tx_r.setText("평점: "+rating+"/5.0");
            }
        });
    }
}
