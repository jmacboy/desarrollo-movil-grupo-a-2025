package com.example.practicageolocalizacion

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicageolocalizacion.databinding.ActivityMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private var currentMarkerCount = 1
    private var isLocationUpdating = false
    private val locationRequest = LocationRequest.Builder(
        Priority.PRIORITY_HIGH_ACCURACY,
        5000
    )
        .setMinUpdateDistanceMeters(20f)
        .setMinUpdateIntervalMillis(5000)
        .build()
    private var polyline: Polyline? = null
    private val loadedLocations: ArrayList<LatLng> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        setupEventListeners()

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                onLocationUpdated(locationResult)
            }
        }
    }

    private fun onLocationUpdated(locationResult: LocationResult) {
        for (location in locationResult.locations) {
            Log.d("Location", "Location updated: ${location.latitude}, ${location.longitude}")
            val theLatLng = LatLng(location.latitude, location.longitude)
            mMap.addMarker(
                MarkerOptions().position(
                    theLatLng
                ).title("Marcador $currentMarkerCount")
            )
            currentMarkerCount++

            loadedLocations.add(theLatLng)
            refreshPolylines()
            zoomToAllMarkers()
        }
    }

    private fun zoomToAllMarkers() {
        val builder = LatLngBounds.Builder()
        for (location in loadedLocations) {
            builder.include(location)
        }
        mMap.animateCamera(
            CameraUpdateFactory.newLatLngBounds(
                builder.build(), 150
            )
        )
    }

    private fun setupEventListeners() {
        binding.btnShowLocation.setOnClickListener {
            getLastLocation()
        }
        binding.btnUpdateLocationContinuous.setOnClickListener {
            var buttonText = "Actualizar la ubicación periódicamente"
            if (isLocationUpdating) {
                stopLocationUpdates()
                isLocationUpdating = false
            } else {
                startLocationUpdates()
                isLocationUpdating = true
                buttonText = "Detener Actualización"
            }

            binding.btnUpdateLocationContinuous.text = buttonText

        }
        binding.btnShowCurrentLocation.setOnClickListener {
            showCurrentLocation()
        }
    }

    private fun showCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                ),
                REQUEST_CURRENT_LOCATION
            )
            return
        }
        fusedLocationClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            null
        ).addOnSuccessListener { location: Location? ->
            if (location == null) {
                return@addOnSuccessListener
            }
            val latLng = LatLng(location.latitude, location.longitude)
            mMap.addMarker(MarkerOptions().position(latLng).title("You are here!"))
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
        }.addOnFailureListener {
            Toast.makeText(
                this,
                "No se pudo obtener la ubicación actual",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                ),
                REQUEST_LOCATION_PERIODICAL
            )
            return
        }
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    private fun refreshPolylines() {
        if (polyline != null) {
            polyline?.remove()
        }
        polyline = mMap.addPolyline(
            PolylineOptions().apply {
                width(2f)
                color(Color.RED)
                add(*loadedLocations.toTypedArray())
            }

        )
    }

    private fun getLastLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                ),
                REQUEST_LAST_LOCATION
            )
            return
        }
        fusedLocationClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            null
        ).addOnSuccessListener { location: Location? ->
            if (location == null) {
                return@addOnSuccessListener
            }
            val latLng = LatLng(location.latitude, location.longitude)
            mMap.addMarker(MarkerOptions().position(latLng).title("You are here!"))
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isZoomControlsEnabled = true

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                ),
                REQUEST_LOCATION_MAP
            )
            return
        }
        mMap.isMyLocationEnabled = true
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_LOCATION_MAP && grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            mMap.isMyLocationEnabled = true
        } else if (requestCode == REQUEST_LAST_LOCATION && grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            getLastLocation()
        } else if (requestCode == REQUEST_LOCATION_PERIODICAL && grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            startLocationUpdates()
        } else if (
            requestCode == REQUEST_CURRENT_LOCATION && grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            showCurrentLocation()
        } else {
            Toast.makeText(
                this,
                "La app necesita permisos de ubicación para funcionar",
                Toast.LENGTH_SHORT
            )
                .show()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    companion object {
        private const val REQUEST_LOCATION_MAP = 1
        private const val REQUEST_LAST_LOCATION = 2
        private const val REQUEST_LOCATION_PERIODICAL = 3
        private const val REQUEST_CURRENT_LOCATION = 4
    }
}