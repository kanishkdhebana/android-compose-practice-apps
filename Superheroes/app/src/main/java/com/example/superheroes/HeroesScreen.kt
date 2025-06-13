package com.example.superheroes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.model.Hero
import com.example.superheroes.model.HeroesDataSource


@Composable
fun HeroesList(
    heroes: List<Hero>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(16.dp)
    ) {
        itemsIndexed(heroes) {
                _, hero ->
            HeroesListItem(
                hero = hero,
                modifier = Modifier
                    .padding(bottom = 8.dp, top = 8.dp)
            )
        }
    }
}

@Composable
fun HeroesListItem(
    modifier: Modifier = Modifier,
    hero: Hero
) {
    Card(
        elevation = cardElevation(defaultElevation = 2.dp),
        modifier = modifier
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .size(72.dp)
        ) {
            Column(
                modifier = modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(hero.nameRes),
                    style = MaterialTheme.typography.displaySmall
                )

                Text(
                    text = stringResource(hero.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Image(
                    painter = painterResource(hero.imageRes),
                    contentDescription = null
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHeroesList() {
    HeroesList(HeroesDataSource.heroes)
}


@Preview(showSystemUi = true)
@Composable
fun PreviewHeroesListItem() {
    val hero = Hero(
        R.string.hero2,
        R.string.description2,
        R.drawable.android_superhero2
    )

    HeroesListItem(hero = hero)
}