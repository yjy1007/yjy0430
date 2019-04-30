package com.example.practice.fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.practice.Contant;
import com.example.practice.Myhelper;
import com.example.practice.Myservice;
import com.example.practice.R;
import com.example.practice.adapter.MyFragoneAdapter;
import com.example.practice.bean.PoemBean;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment_one extends Fragment {

    SwipeRefreshLayout  swipe;
     RecyclerView recycler_fragone;
    List<PoemBean.ResultBean> result;
    MyFragoneAdapter  myFragoneAdapter;
    public Fragment_one() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_fragment_one, container, false);
        initView(inflate);
        initDownload();
        //将数据添加到数据库中
//        Myhelper myhelper = new Myhelper(getContext());
//        SQLiteDatabase database = myhelper.getReadableDatabase();
//        database.execSQL("insert  into user values(?,?,?)",new String[] {result.get(0).getTitle(),result.get(0).getAuthors(),result.get(0).getContent()});
        //todo  对recyclerview进行刷新
//        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                Timer timer = new Timer();
//                timer.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        swipe.setRefreshing(false);
//                    }
//                },3000);
//            }
//        },3000);
        return inflate;
    }

    private void initDownload() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_fragone.setLayoutManager(manager);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Contant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Myservice myservice = retrofit.create(Myservice.class);
        Call<PoemBean> call = myservice.getBean();
        call.enqueue(new Callback<PoemBean>() {
            @Override
            public void onResponse(Call<PoemBean> call, Response<PoemBean> response) {
                result = response.body().getResult();
                myFragoneAdapter = new MyFragoneAdapter(getContext(),result);
                recycler_fragone.setAdapter(myFragoneAdapter);
                myFragoneAdapter.refresh(result);
                Log.i("result:",result.toString());
            }

            @Override
            public void onFailure(Call<PoemBean> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    private void initView(View inflate) {
        recycler_fragone = (RecyclerView) inflate.findViewById(R.id.recycler_fragone);
        swipe = inflate.findViewById(R.id.swipe);
    }
}
