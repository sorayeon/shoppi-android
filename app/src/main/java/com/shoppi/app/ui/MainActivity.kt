package com.shoppi.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shoppi.app.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation_main)
        bottomNavigationView.itemIconTintList = null // icon Tint 기본값을 초기화 (gradient 아이콘사용)

        val navController =
            supportFragmentManager.findFragmentById(R.id.container_main)?.findNavController()

        navController?.let {
            // NavController -> nav host fragment 에서 이동을 관리
            bottomNavigationView.setupWithNavController(it) // bottom navigation view 와 fragment container view nav host fragment 를 연결 할 수 있다
        }
    }
}