package com.android.picart_thibaut_tpandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.picart_thibaut_tpandroid.databinding.ActivityMainBinding
import com.android.picart_thibaut_tpandroid.view.RecyclerViewActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get the xml layout to attach to this activity
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // We can get the TextView directly from it's id and interact with it
        binding.button.setOnClickListener {
            goToRecyclerViewActiviy()
        }
    }

    private fun goToRecyclerViewActiviy() {
        val intent = Intent(this, RecyclerViewActivity::class.java)
        startActivity(intent)
    }
}