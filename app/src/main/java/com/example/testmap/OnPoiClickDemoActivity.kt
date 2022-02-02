package com.example.testmap
//
//import android.os.Bundle
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.google.android.gms.maps.GoogleMap
//import com.google.android.gms.maps.OnMapReadyCallback
//import com.google.android.gms.maps.SupportMapFragment
//import com.google.android.gms.maps.model.PointOfInterest
//
//internal class OnPoiClickDemoActivity : AppCompatActivity(), OnMapReadyCallback,
//    GoogleMap.OnPoiClickListener {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.poi_click_demo)
//        val mapFragment = supportFragmentManager.findFragmentById(R.id.map)
//                as SupportMapFragment
//        mapFragment.getMapAsync(this)
//    }
//
//    override fun onMapReady(map: GoogleMap) {
//        map.setOnPoiClickListener(this)
//    }
//
//    override fun onPoiClick(poi: PointOfInterest) {
//        Toast.makeText(this, """Clicked: ${poi.name}
//            Place ID:${poi.placeId}
//            Latitude:${poi.latLng.latitude} Longitude:${poi.latLng.longitude}""",
//            Toast.LENGTH_SHORT
//        ).show()
//    }
//}