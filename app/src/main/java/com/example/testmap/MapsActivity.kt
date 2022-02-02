package com.example.testmap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.*
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.testmap.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapClickListener, OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val sydneyLatLng = LatLng(-34.0, 151.0)
        val nickoLatLng = LatLng(36.7581114, 139.5987496)

        // マーカー設置
        mMap.addMarker(MarkerOptions().position(sydneyLatLng).title("シドニー").snippet("ここに住所"))
        mMap.addMarker(MarkerOptions().position(nickoLatLng).title("日光").snippet("ここに住所"))

        // マーカークリックリスナ設置
        mMap.setOnMapClickListener(this)

        // カメラの初期位置：日光の都市エリア
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nickoLatLng, 10f))
    }

    override fun onMapClick(point: LatLng) {
//        binding.cardView.visibility = View.VISIBLE
        binding.icon.setImageResource(R.drawable.nickou_tousyougu)
        binding.title.text = "日光"
        binding.address.text = "栃木県日光市山内２３０１"
    }
}