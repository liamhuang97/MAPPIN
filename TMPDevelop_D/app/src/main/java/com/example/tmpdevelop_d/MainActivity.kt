package com.example.tmpdevelop_d

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth

        if (auth.currentUser == null) {
            // 未登入，跳轉至登入畫面
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // 結束 MainActivity，避免使用者按返回鍵回到此畫面
        } else {
            // 已登入，留在 MainActivity
            // TODO: 在此處顯示已登入用戶的內容
        }

        val mapFragment = MapFragment()
        val groupFragment = GroupFragment()
        val accountFragment = CostFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, mapFragment)
            .commit()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_groups -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, groupFragment).commit()
                    return@setOnItemSelectedListener true
                }
                R.id.menu_map -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, mapFragment).commit()
                    return@setOnItemSelectedListener true
                }
                R.id.menu_costs -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, accountFragment).commit()
                    return@setOnItemSelectedListener true
                }
                else -> false
            }
        }

        bottomNavigationView.selectedItemId = R.id.menu_map // 設置預設選中的項目
    }
}