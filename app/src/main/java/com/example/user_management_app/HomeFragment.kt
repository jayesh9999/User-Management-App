package com.example.user_management_app

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.example.user_management_app.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            if (data != null) {
                val selectedImageUri: Uri? = data.data
                if (selectedImageUri != null) {
                    Glide.with(this)
                        .load(selectedImageUri)
                        .into(binding.selectImage)
                }
            }
        }
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: UserDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater,container,false)

        database = UserDatabase.getDatabase(requireContext())

        binding.submitForm.setOnClickListener {
            val username = binding.Username.text.toString()
            val email = binding.EmailAddress.text.toString()
            val phone = binding.PhoneNumber.text.toString()
            val newuser = user(username = username, email = email, phone = phone)
            GlobalScope.launch {
                database.dao().addUser((newuser))
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(activity, "User added successfully", Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.selectImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            pickImageLauncher.launch(intent)
        }

        return binding.root
    }


}