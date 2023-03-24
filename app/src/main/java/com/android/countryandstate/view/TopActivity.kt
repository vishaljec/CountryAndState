package com.android.countryandstate.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.countryandstate.databinding.ActivityTopBinding


class TopActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}


