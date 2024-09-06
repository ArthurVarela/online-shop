package com.example.onlineshop.Activity

import android.content.Intent
import android.os.Bundle
import com.example.onlineshop.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {
    private val binding by lazy {
        ActivityIntroBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            btnStart.setOnClickListener {
                startActivity(Intent(this@IntroActivity, MainActivity::class.java))
            }
        }
    }
}