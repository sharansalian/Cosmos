package com.example.cosmos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import com.example.cosmos.databinding.ActivityCosmosBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CosmosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityCosmosBinding>(this, R.layout.activity_cosmos)
    }

}