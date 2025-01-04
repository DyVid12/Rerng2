package com.example.rerngapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rerngapp.databinding.FragmentAboutBinding

class AboutFragment: Fragment() {
    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)

        // Handle Facebook link click
        binding.tvFacebook.setOnClickListener {
            val facebookUrl = "https://www.facebook.com/share/15UNfgMPPc/?mibextid=wwXIfr"
            openLink(facebookUrl)
        }

        // Handle Gmail link click
        binding.tvEmail.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:vitra1812@gmail.com")
            }
            startActivity(emailIntent)
        }

        // Handle Phone number click
        binding.tvPhone.setOnClickListener {
            val phoneIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:0889646724")
            }
            startActivity(phoneIntent)
        }

        return binding.root
    }

    private fun openLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
