package com.example.lab12_maps

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.mapapp.R
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker
import androidx.compose.material3.*

@Composable
fun MapScreen() {
    val ArequipaLocation = LatLng(-16.4040102, -71.559611) // Arequipa, Perú
    val cameraPositionState = rememberCameraPositionState {
        position = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(ArequipaLocation, 12f)
    }
    val context = LocalContext.current
    val originalBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.cordillera)
    val scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, 100, 100, false)

    // Variable para cambiar el tipo de mapa
    var mapType by remember { mutableStateOf(com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL) }

    Column(modifier = Modifier.fillMaxSize()) {
        // Botones para cambiar el tipo de mapa
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Button(onClick = { mapType = com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL }) {
                Text("Normal")
            }
            Button(onClick = { mapType = com.google.android.gms.maps.GoogleMap.MAP_TYPE_HYBRID }) {
                Text("Híbrido")
            }
            Button(onClick = { mapType = com.google.android.gms.maps.GoogleMap.MAP_TYPE_SATELLITE }) {
                Text("Satélite")
            }
            Button(onClick = { mapType = com.google.android.gms.maps.GoogleMap.MAP_TYPE_TERRAIN }) {
                Text("Terreno")
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            // Añadir GoogleMap al layout
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                uiSettings = MapUiSettings(
                    mapToolbarEnabled = false // Desactivar herramientas de mapa
                )
            ) {
                // Añadir marcador en Arequipa Perú
                Marker(
                    state = rememberMarkerState(position = ArequipaLocation),
                    icon = BitmapDescriptorFactory.fromBitmap(scaledBitmap),
                    title = "Arequipa, Perú"
                )
            }
        }
    }
}
