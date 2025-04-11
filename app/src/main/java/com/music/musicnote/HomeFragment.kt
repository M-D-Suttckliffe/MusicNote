package com.music.musicnote

import android.content.ClipData.Item
import android.media.browse.MediaBrowser.ItemCallback
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.view.menu.MenuItemImpl
import androidx.appcompat.view.menu.MenuItemWrapperICS
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.appcompat.widget.MenuItemHoverListener
import androidx.core.view.MenuItemCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mediaBtn = view.findViewById<Button>(R.id.mediaButton)
        val searchBtn = view.findViewById<Button>(R.id.searchButton)
        val settingsBtn = view.findViewById<Button>(R.id.settingsButton)

        val controller = findNavController()

        mediaBtn.setOnClickListener { controller.navigate(R.id.mediaFragment)}
        searchBtn.setOnClickListener { controller.navigate(R.id.searchFragment)}
        settingsBtn.setOnClickListener { controller.navigate(R.id.settingsFragment)}
    }
}