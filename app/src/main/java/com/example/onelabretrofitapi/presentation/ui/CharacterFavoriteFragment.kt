package com.example.onelabretrofitapi.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.fragment.findNavController
import coil.compose.rememberAsyncImagePainter
import com.example.onelabretrofitapi.R
import com.example.onelabretrofitapi.core.functional.Resource
import com.example.onelabretrofitapi.presentation.model.Character
import com.example.onelabretrofitapi.presentation.viewmodel.CharacterSavedListViewModel

class CharacterFavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    CharacterFavouriteScreen()
                }
            }
        }
    }

    @Composable
    fun CircularProgressBar() {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(50.dp),
                color = Color.LightGray
            )
        }
    }

    @Composable
    fun CharacterFavouriteScreen(viewModel: CharacterSavedListViewModel = hiltViewModel()) {
        val uiState = viewModel.characterLiveData.collectAsState()
        Column {
            Text(
                text = "Saved Character List",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .background(Color.LightGray)
                    .padding(10.dp)
            )
            when (uiState.value) {
                is Resource.Success -> {
                    CharacterFavoriteList(characters = (uiState.value as Resource.Success<List<Character>>).data,
                        onItemClick = {
                            val action = CharacterFavoriteFragmentDirections.actionCharacterFavoriteFragmentToChaarcterInfoFragment(it)
                            findNavController().navigate(action)
                        }, onClick = {character ->
                            character.id?.let {
                                viewModel.deleteCharacterById(it)
                            }
                        })
                }

                is Resource.Loading -> {
                    CircularProgressBar()
                }

                else -> Unit
            }
        }
    }

    @Composable
    fun CharacterFavoriteList(
        characters: List<Character>,
        onItemClick: (id: Int) -> Unit,
        onClick: (Character) -> Unit
    ) {
        LazyColumn {
            items(characters) { character ->
                CharacterCard(
                    character,
                    onItemClick = { onItemClick.invoke(it) },
                    onIconClick = { onClick.invoke(character) })
            }
        }
    }

    @Composable
    fun CharacterCard(
        character: Character,
        onIconClick: (Character) -> Unit,
        onItemClick: (id: Int) -> Unit
    ) {
        Card(
            modifier = Modifier
                .clickable { character.id?.let { onItemClick.invoke(it) } }
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .background(Color.DarkGray)
                    .padding(8.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = character.image),
                    contentDescription = "image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(150.dp, 150.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = character.name ?: "",
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = character.species ?: "",
                    fontSize = 20.sp,
                    color = Color.LightGray,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
                IconButton(
                    onClick = { onIconClick.invoke(character) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_star_border),
                        contentDescription = "",
                        tint = Color.Yellow,
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(30.dp)
                            .align(Alignment.End)
                    )
                }
            }
        }
    }

    @Composable
    @Preview
    fun PreviewCharacterFavouriteScreen() {
//        CharacterCard(Character(1, "Name", "Alive", "man", "man", ""), )
    }

}