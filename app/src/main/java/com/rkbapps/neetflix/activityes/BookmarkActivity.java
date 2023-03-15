package com.rkbapps.neetflix.activityes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.db.BookmarkAdapter;
import com.rkbapps.neetflix.db.Database;
import com.rkbapps.neetflix.db.DatabaseReference;
import com.rkbapps.neetflix.db.EntityModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class BookmarkActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BookmarkAdapter adapter;
    private TextView noBookmark;
    private List<EntityModel> entityModel=new ArrayList<>();
    private Database mDatabase;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        noBookmark = findViewById(R.id.txtNoBookmark);

        recyclerView=findViewById(R.id.recyclerBookmark);

        noBookmark.setVisibility(View.GONE);

        mDatabase = DatabaseReference.getDatabase(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        adapter= new BookmarkAdapter(this,entityModel);

        loadBookmarkData();

        recyclerView.setAdapter(adapter);


    }

    private void loadBookmarkData(){
        entityModel.clear();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                if(mDatabase.getContentDao().getMyBookmarks().size()!=0)
                    entityModel.addAll(mDatabase.getContentDao().getMyBookmarks());
                else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            noBookmark.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        }
                    });
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
    private int onResumeCalled = 0;

    @Override
    protected void onResume() {
        super.onResume();
        onResumeCalled++;
        if(onResumeCalled>1)
            loadBookmarkData();


    }
}
