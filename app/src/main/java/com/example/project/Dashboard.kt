package com.example.project

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class Dashboard : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_drawer)

        val navView: NavigationView = findViewById(R.id.navigation_view)
        val toolbar: Toolbar = findViewById(R.id.toolbar)

        // Set the toolbar
        setSupportActionBar(toolbar)

        // Set up the navigation drawer
        drawerLayout = findViewById(R.id.Drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.Open_Drawer, R.string.Close_Drawer
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
                    supportActionBar?.title = "Home"
                    true
                }
                R.id.logout -> {
                    Toast.makeText(this, "Logout clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        // Set up the bottom navigation
        val bottomNavView: BottomNavigationView = findViewById(R.id.bottom_nav)
        bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_home -> {
                    replaceFragment(HomeFragment())
                    supportActionBar?.title = "Home"
                    Log.d("BottomNav", "Home selected")
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}
