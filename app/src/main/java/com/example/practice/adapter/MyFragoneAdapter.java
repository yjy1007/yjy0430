package com.example.practice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.practice.R;
import com.example.practice.bean.PoemBean;

import java.util.ArrayList;
import java.util.List;

public class MyFragoneAdapter extends RecyclerView.Adapter<MyFragoneAdapter.MyviewHolder>{
    Context  context;
    List<PoemBean.ResultBean>  list;

    public  void  refresh(List<PoemBean.ResultBean> list){
       list.addAll(list);
    }

    public MyFragoneAdapter(Context context, List<PoemBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View inflate = inflater.inflate(R.layout.item_poem, viewGroup, false);
        return new  MyviewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MyviewHolder myviewHolder, int i) {
        myviewHolder.title.setText(list.get(i).getTitle());
        myviewHolder.author.setText(list.get(i).getAuthors());
        myviewHolder.sentense.setText(list.get(i).getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class  MyviewHolder  extends RecyclerView.ViewHolder {
        TextView  title,author,sentense;
        public MyviewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.poemtitle);
            author = itemView.findViewById(R.id.poemauthor);
            sentense = itemView.findViewById(R.id.poemsentense);
        }
    }
}
