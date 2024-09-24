package com.dk.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dk.myapplication.db.entity.NumEntity
import com.dk.myapplication.view.CustomAdapter
import com.dk.myapplication.view.MainViewModel

class MainActivity : AppCompatActivity() {

    private val vm : MainViewModel by viewModels()

    lateinit var numArrayList : ArrayList<NumEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create
        val createBtn = findViewById<Button>(R.id.createBtn)
        createBtn.setOnClickListener {
            val ranNum = (10..99).random().toString()
            val numEntity = NumEntity(0, ranNum)
            vm.create(numEntity)
        }

        val numRV = findViewById<RecyclerView>(R.id.numRV)

        //read
        vm.read()
        vm.numEntityList.observe(this, Observer {
            Log.d("MAIN", it.toString())

            numArrayList = it as ArrayList<NumEntity>
            val customAdapter = CustomAdapter(numArrayList)
            numRV.adapter = customAdapter

            onClickEventHandling(customAdapter)
        })

        numRV.layoutManager = LinearLayoutManager(this)
    }

    private fun onClickEventHandling(customAdapter: CustomAdapter) {

        // Update
        customAdapter.updateClick = object : CustomAdapter.ItemClick{

            override fun onClick(view: View, position: Int) {
                vm.update(numArrayList[position])
            }
        }

        // Delete
        customAdapter.deleteClick = object : CustomAdapter.ItemClick{

            override fun onClick(view: View, position: Int) {
                vm.delete(numArrayList[position])
                Toast.makeText(this@MainActivity, numArrayList[position].toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
