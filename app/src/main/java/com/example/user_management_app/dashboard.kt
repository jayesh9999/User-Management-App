package com.example.user_management_app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.user_management_app.databinding.ActivityDashboardBinding

class dashboard : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)



        replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener{

            when(it.itemId){

                R.id.Home -> replaceFragment(HomeFragment())
                R.id.Profile -> replaceFragment(ProfileFragment())
                R.id.User -> replaceFragment(UserFragment())

                else -> {

                }

            }

            true

        }

    }

    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.frameLayout.id,fragment)
        fragmentTransaction.commit()

        val getEmail = intent.getStringExtra("email").toString()
        Log.d("check data","Email is $getEmail")
        val bundle = Bundle()
        bundle.putString("email", getEmail)
        fragment.arguments = bundle

    }

}