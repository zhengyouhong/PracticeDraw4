package com.example.recyclerdemo;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcv;
    private ArrayList<Integer> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcv = findViewById(R.id.rcv);

        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcv.setLayoutManager(lm);

        rcv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                int position = parent.getChildAdapterPosition(view);
                if(position != parent.getChildCount() - 1){
                    outRect.bottom = (int) (getResources().getDisplayMetrics().density * 8);
                }
            }
        });

        datas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            datas.add(i);
        }

        rcv.setAdapter(new MyAdapter());

    }

    private class MyAdapter extends RecyclerView.Adapter<MyHolder>{


        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.from(MainActivity.this).inflate(
                    R.layout.item_rcv, viewGroup, false
            );
            return new MyHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
            myHolder.mTv.setText("这是第" + datas.get(i) + "条数据");

            myHolder.mTv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    datas.remove(i);
                    notifyItemRemoved(i);
                    notifyItemRangeChanged(i, datas.size());
                    return false;
                }
            });
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }
    }

    static class MyHolder extends RecyclerView.ViewHolder{

        public TextView mTv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.tv);
        }
    }



}
