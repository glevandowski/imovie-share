package com.levandowski.imovieshare.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.NavHostFragment
import com.levandowski.imovieshare.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFragment(
            containerRes = R.id.nav_host_fragment,
            host = NavHostFragment.create(R.navigation.nav_graph),
            transaction = supportFragmentManager.beginTransaction()
        )
    }

    private fun setFragment(
        containerRes: Int,
        host: NavHostFragment,
        transaction: FragmentTransaction
    ) {
        transaction.replace(containerRes, host).setPrimaryNavigationFragment(host).commit()
    }
}
