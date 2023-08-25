package com.example.user_management_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.user_management_app.databinding.FragmentHomeBinding
import com.example.user_management_app.databinding.FragmentUserBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: UserDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(layoutInflater,container,false)

        database = UserDatabase.getDatabase(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            val user = withContext(Dispatchers.IO) {
                database.dao().getUSer()
            }
            binding.text.text = user.toString()
        }

        return binding.root
    }



}