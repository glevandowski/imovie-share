package com.levandowski.imovieshare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val host = NavHostFragment.create(R.navigation.nav_graph)
        supportFragmentManager.beginTransaction().run {
            replace(R.id.nav_host_fragment, host).setPrimaryNavigationFragment(host).commit()
        }
    }
}
