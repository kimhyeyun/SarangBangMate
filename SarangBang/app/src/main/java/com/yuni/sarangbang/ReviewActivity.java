package com.yuni.sarangbang;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity implements View.OnClickListener {
    ListView comment_list;
    EditText comment_edit;
    RatingBar rb_p,rb_m;
    Comment_Adapter ca;
    ArrayList<Comment_Item> c_arr = new ArrayList<Comment_Item>();
    View header,footer;
    int cnt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_menureview);
        init();
    }

    private void init() {
        comment_list = (ListView)findViewById(R.id.comment_list);
        header = getLayoutInflater().inflate(R.layout.header,null,false);
        footer = getLayoutInflater().inflate(R.layout.footer,null,false);
        comment_list.addHeaderView(header);
        comment_list.addFooterView(footer);
        setList();
        setHeader();
        setFooter();
    }

    private void setFooter() {
        comment_edit = (EditText)footer.findViewById(R.id.comment_edit);
        Button commentinput_btn = (Button)footer.findViewById(R.id.commentinput_btn);
        commentinput_btn.setOnClickListener(this);
    }

    private void setHeader() {
        rb_p = (RatingBar)header.findViewById(R.id.rb_p);
        rb_m = (RatingBar)header.findViewById(R.id.rb_m);
        final TextView tx_r = (TextView)header.findViewById(R.id.r_tx) ;

        rb_p.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rb_m.setRating(rating);
                tx_r.setText("평점: "+rating+"/5.0");
            }
        });
    }

    private void setList() {
        ca = new Comment_Adapter(getApplicationContext(),ReviewActivity.this,ReviewActivity.this,c_arr);
        comment_list.setAdapter(ca);
        comment_list.setSelection(c_arr.size()-1);
        comment_list.setDivider(null);
        comment_list.setSelectionFromTop(0,0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.commentinput_btn:
                String tmp  = comment_edit.getText().toString();
                if(tmp.equals("")){
                    Toast.makeText(ReviewActivity.this,"빈칸이 있습니다.",Toast.LENGTH_SHORT).show();
                }
                else{
                    Comment_Item ci = new Comment_Item();
                    ci.setContent(tmp);
                    ci.setNickname("닉네임");
                    c_arr.add(ci);
                    resetAdapter();
                    comment_edit.setText("");
                }
                break;
        }

    }

    public void resetAdapter(){
        ca.notifyDataSetChanged();
    }

    public void deleteArr(int p) {
        c_arr.remove(p);
        ca.notifyDataSetChanged();
    }
}
