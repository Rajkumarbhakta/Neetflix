package com.rkbapps.neetflix.activityes

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.rkbapps.neetflix.R
import com.rkbapps.neetflix.databinding.ActivityBookmarkBinding
import com.rkbapps.neetflix.db.BookmarkAdapter
import com.rkbapps.neetflix.db.DatabaseReference.getDatabase
import com.rkbapps.neetflix.db.EntityModel
import kotlinx.coroutines.DelicateCoroutinesApi

class BookmarkActivity : AppCompatActivity() {
    private lateinit var entityModel: MutableList<EntityModel>
    private lateinit var adapter: BookmarkAdapter
    private var onResumeCalled = 0
    private lateinit var binding: ActivityBookmarkBinding


    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bookmark)


        binding.txtNoBookmark.visibility = View.GONE

        entityModel = ArrayList()

        binding.recyclerBookmark.layoutManager = GridLayoutManager(this, 3)
        adapter = BookmarkAdapter(this, entityModel)
        loadBookmarkData()
        binding.recyclerBookmark.adapter = adapter
    }

    private fun loadBookmarkData() {
        entityModel.clear()
        getDatabase(this).contentDao.getAllMyBookmarks().observe(this, Observer {
            if (it.isNotEmpty()) {
                entityModel.addAll(it)
                adapter.notifyDataSetChanged()
            } else {
                runOnUiThread {
                    binding.txtNoBookmark.visibility = View.VISIBLE
                    binding.recyclerBookmark.visibility = View.GONE
                }
            }
        })


//        val executorService = Executors.newSingleThreadExecutor()
//        val handler = Handler(Looper.myLooper()!!)
//        executorService.execute {
//            if (mDatabase!!.contentDao.getAllMyBookmarks().size != 0) entityModel.addAll(
//                mDatabase!!.contentDao.getAllMyBookmarks()
//            ) else {
//                runOnUiThread {
//                    noBookmark!!.visibility = View.VISIBLE
//                    recyclerView!!.visibility = View.GONE
//                }
//            }
//            handler.post { adapter!!.notifyDataSetChanged() }
//        }
    }

//    override fun onResume() {
//        super.onResume()
//        onResumeCalled++
//        if (onResumeCalled > 1) loadBookmarkData()
//    }
}