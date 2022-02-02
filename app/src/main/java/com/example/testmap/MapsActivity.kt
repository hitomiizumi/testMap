package com.example.testmap

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.testmap.PermissionUtils.PermissionDeniedDialog.Companion.newInstance
import com.example.testmap.PermissionUtils.requestPermission
import com.example.testmap.PermissionUtils.isPermissionGranted

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.*
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.testmap.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapClickListener, OnMyLocationButtonClickListener, OnMyLocationClickListener,
    ActivityCompat.OnRequestPermissionsResultCallback, OnMapReadyCallback {

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

    @SuppressLint("MissingPermission")
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

        // 現在地レイヤ
        mMap.isMyLocationEnabled = true
        mMap.setOnMyLocationButtonClickListener(this)
        mMap.setOnMyLocationClickListener(this)
    }

    // 現在地パーミッションここから
    override fun onMyLocationClick(location: Location) {
        Toast.makeText(this, "現在地は：$location", Toast.LENGTH_LONG)
            .show()
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this,"現在地へ移動します",Toast.LENGTH_SHORT)
            .show()
        return false
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (isPermissionGranted(permissions, grantResults, Manifest.permission.ACCESS_FINE_LOCATION)) {
            enableMyLocation()
        } else {
            permissionDenied = true
        }
    }
    override fun onResumeFragments() {
        super.onResumeFragments()
        if (permissionDenied) {
            showMissingPermissionError()
            permissionDenied = false
        }
    }
    // 現在地パーミッションここまで

    // マーカークリック後の動き
    override fun onMapClick(point: LatLng) {
//        binding.cardView.visibility = View.VISIBLE
        binding.icon.setImageResource(R.drawable.nickou_tousyougu)
        binding.title.text = "日光"
        binding.address.text = "栃木県日光市山内２３０１"
    }

    // 現在地パーミッションここから
    private fun showMissingPermissionError() {
        newInstance(true).show(supportFragmentManager, "dialog")
    }

    companion object {
        /**
         * Request code for location permission request.
         *
         * @see .onRequestPermissionsResult
         */
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
    // 現在地パーミッションここまで
}