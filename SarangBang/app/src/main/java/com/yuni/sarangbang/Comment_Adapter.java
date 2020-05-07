package com.yuni.sarangbang;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Comment;

import java.nio.file.Files;
import java.util.ArrayList;

public class Comment_Adapter extends BaseAdapter implements View.OnClickListener {

    private ArrayList<Comment_Item> arr;
    private Activity mActivity;
    private ReviewActivity ra;
    private Context mContext;
    private int pos;

    public Comment_Adapter(Context mContext, Activity mActivity, ReviewActivity rc, ArrayList<Comment_Item> arr_item){
        this.mContext = mContext;
        this.mActivity = mActivity;
        this.arr = arr_item;
        this.ra = rc;
    }

    @Override
    public int getCount() {
        return this.arr.size();
    }

    @Override
    public Object getItem(int position) {
        return this.arr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long)position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       if(convertView==null){
           boolean res = false;
           int ras = 2130903041;
           LayoutInflater mInflater = (LayoutInflater)this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView = mInflater.inflate(ras,parent,false);
       }
       this.pos = position;
       if(this.arr.size()!=0){
           TextView ci_nickname_text = (TextView)convertView.findViewById(R.id.ci_nickname_txt);
           ci_nickname_text.setText(((Comment_Item)this.arr.get(this.pos)).getNickname());
           TextView ci_context_text = (TextView)convertView.findViewById(R.id.ci_content_text);
           ci_context_text.setText(((Comment_Item)this.arr.get(this.pos)).getContent());
           Button delete_btn = (Button)convertView.findViewById(R.id.ci_delete_btn);
           delete_btn.setOnClickListener(this);
           delete_btn.setTag(String.valueOf(this.pos));
           delete_btn.setTag(2131230725, delete_btn.getTag());

       }
       return convertView;
    }

    @Override
    public void onClick(View v) {
        final int tag = Integer.parseInt(v.getTag().toString());
        switch (v.getId()){
            case 2131230725:
                AlertDialog.Builder alertDlg = new AlertDialog.Builder(this.mActivity);
                alertDlg.setPositiveButton("삭제", new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Comment_Adapter.this.ra.deleteArr(tag);
                        Toast.makeText(Comment_Adapter.this.mContext,"댓글이 삭제되었습니다.",Toast.LENGTH_SHORT).show();
                    }
                });
                alertDlg.setNegativeButton("취소",(android.content.DialogInterface.OnClickListener)null);
                alertDlg.setTitle("댓글 삭제");
                alertDlg.setMessage("정ㅁㄹ 삭제 하시겠습니까?");
                alertDlg.show();
            default:
        }
    }
}
