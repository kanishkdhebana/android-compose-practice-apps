package com.example.flightsearch.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.R
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.Favorite

@Composable
fun FlightSearchApp(
    viewModel: FlightSearchViewModel = viewModel(factory = FlightSearchViewModel.factory)
) {
    val airports by viewModel.getAllAirports().collectAsState(initial = emptyList())
    val favouriteAirports by viewModel.getFavouriteAirports().collectAsState(initial = emptyList())

    FlightSearchScreen(airports, favouriteAirports, "", flightSearchViewModel = viewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightSearchScreen(
    airports: List<Airport>,
    favouriteAirports: List<Favorite>,
    query: String,
    //onQueryChange: (String) -> Unit,
    //onSearch: (String) -> Unit,
    flightSearchViewModel: FlightSearchViewModel
) {
    var expanded by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        color = MaterialTheme.colorScheme.onPrimary)
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchBar(
                query = searchText,
                onQueryChange = {searchText = it},
                onSearch = {expanded = false},
                placeholder = {
                    Text(
                        text = stringResource(R.string.search_placeholder),
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                active = expanded,
                onActiveChange = { expanded = it },
                modifier = Modifier.semantics { traversalIndex = -1f },
                colors = SearchBarDefaults.colors(MaterialTheme.colorScheme.primaryContainer)
            ) {
            }
            
            Text(text = stringResource(id = R.string.favourite_routes), fontWeight = FontWeight.Bold)

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(
                    items = favouriteAirports,
                    key = { favouriteAirport -> favouriteAirport.id }
                ) { favourite ->
                    FlightListItem(favouriteAirport = favourite,
                        flightSearchViewModel = flightSearchViewModel)
                }
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun FlightListItem(favouriteAirport: Favorite,
                   flightSearchViewModel: FlightSearchViewModel) {
    Card(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(topEnd = 16.dp)
    ) {
        val Departure by flightSearchViewModel.departureName.collectAsState()
        val Arrive by flightSearchViewModel.arrivalName.collectAsState()

        LaunchedEffect(favouriteAirport) {
            flightSearchViewModel.fetchAirportNames(favouriteAirport.departureCode, favouriteAirport.destinationCode)
        }

        Row(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = stringResource(R.string.depart)
                )

                Row {
                    Text(text = favouriteAirport.departureCode, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(7.dp))
                    Departure?.let { Text(text = it) }
                }

                Text(
                    text = stringResource(R.string.arrive)
                )

                Row {
                    Text(text = favouriteAirport.destinationCode, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(7.dp))
                    Arrive?.let { Text(text = it) }
                }
            }
            FavouriteStar(isFavourite = false)
        }


    }
}



@Composable
fun FavouriteStar(
    isFavourite: Boolean,
    modifier: Modifier = Modifier,
) {
    IconToggleButton(
        checked = isFavourite,
        onCheckedChange = {}
    ) {
        Icon(
            imageVector = if (isFavourite) Icons.Filled.Star else Icons.Outlined.Star,
            contentDescription = null,
            tint = if (isFavourite) Color(0xff9b532d) else Color.Gray,
            modifier = Modifier.size(50.dp)
        )
    }
}

