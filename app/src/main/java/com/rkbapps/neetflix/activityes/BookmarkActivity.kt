package com.rkbapps.neetflix.activityes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.db.BookmarkAdapter;
import com.rkbapps.neetflix.db.Database;
import com.rkbapps.neetflix.db.DatabaseReference;
import com.rkbapps.neetflix.db.EntityModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BookmarkActivity extends AppCompatActivity {

    private final List<EntityModel> entityModel = new ArrayList<>();
    private RecyclerView recyclerView;
    private BookmarkAdapter adapter;
    private TextView noBookmark;
    private Database mDatabase;
    private int onResumeCalled = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        noBookmark = findViewById(R.id.txtNoBookmark);

        recyclerView = findViewById(R.id.recyclerBookmark);

        noBookmark.setVisibility(View.GONE);

        mDatabase = DatabaseReference.INSTANCE.getDatabase(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        adapter = new BookmarkAdapter(this, entityModel);

        loadBookmarkData();

        recyclerView.setAdapter(adapter);


    }

    private void loadBookmarkData() {
        entityModel.clear();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                if (mDatabase.getContentDao().getAllMyBookmarks().size() != 0)
                    entityModel.addAll(mDatabase.getContentDao().getAllMyBookmarks());
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

    @Override
    protected void onResume() {
        super.onResume();
        onResumeCalled++;
        if (onResumeCalled > 1)
            loadBookmarkData();
    }
}
