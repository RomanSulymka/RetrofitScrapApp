package com.example.learnandoird


import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnandoird.`interface`.RetrofitServices
import com.example.learnandoird.adapter.MyMovieAdapter
import com.example.learnandoird.common.Common
import com.example.learnandoird.model.Movie
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var mService: RetrofitServices
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: MyMovieAdapter
    lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mService = Common.retrofitServices
        recyclerMovieList.layoutManager = layoutManager
        dialog = SpotsDialog.Builder()
            .setCancelable(true)
            .setContext(this)
            .build()

        getAllMovieList()
    }

    private fun getAllMovieList() {
        dialog.show()
        mService.getMovieList().enqueue(object : Callback<MutableList<Movie>> {
            override fun onFailure(call: Call<MutableList<Movie>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<MutableList<Movie>>,
                response: Response<MutableList<Movie>>
            ) {
                adapter = MyMovieAdapter(baseContext, response.body() as MutableList<Movie>)
                adapter.notifyDataSetChanged()
                recyclerMovieList.adapter = adapter

                dialog.dismiss()
            }
        })
    }

}