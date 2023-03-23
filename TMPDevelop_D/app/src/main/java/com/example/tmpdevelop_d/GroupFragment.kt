package com.example.tmpdevelop_d

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.viewpager.widget.ViewPager
import com.example.tmpdevelop_d.Adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout


class GroupFragment : Fragment() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_group, container, false)

        viewPager = root.findViewById(R.id.view_pager)
        viewPager.adapter = ViewPagerAdapter(childFragmentManager)

        tabLayout = root.findViewById(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)

        // 找到 ImageButton
        val imageButton = root.findViewById<ImageButton>(R.id.img_btn)
        // 設置點擊監聽器
        imageButton.setOnClickListener {
            val intent = Intent(requireContext(), AccountActivity::class.java)
            startActivity(intent)
        }

        return root
    }

    companion object {
        fun newInstance(): GroupFragment {
            return GroupFragment()
        }
    }
}