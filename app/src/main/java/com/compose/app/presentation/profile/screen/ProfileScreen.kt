package com.compose.app.presentation.profile.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.compose.app.R
import com.compose.app.navigation.nav_graph.Graph
import com.compose.app.navigation.nav_graph.auth.AuthDestination
import com.compose.app.navigation.nav_graph.main.MainDestination
import com.compose.app.presentation.util.FirebaseAuthViewModel
import com.compose.app.presentation.util.UserViewModel

enum class ActionType(val actionName: String) {
    UPDATE_PROFILE("update profile"), LOGOUT("logout")
}

@Composable
fun ProfileScreen(
    userViewModel: UserViewModel,
    navController: NavHostController, modifier: Modifier = Modifier,
    firebaseAuthViewModel: FirebaseAuthViewModel = hiltViewModel()
) {

    val userModel = userViewModel.userModel!!

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        item {
            Spacer(modifier = modifier.padding(vertical = 45.dp))
            if (userModel.user?.photoUrl == null) {
                Image(
                    painter = painterResource(R.drawable.android), contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = CircleShape
                        )
                )
            } else {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(userModel.user.photoUrl)
                        .crossfade(enable = true).build(),
                    contentDescription = "Avatar Image",
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = CircleShape
                        )
                )
            }
            Spacer(modifier = modifier.padding(vertical = 5.dp))
            Text(userModel.user?.name.toString(), fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            Divider(
                modifier = modifier
                    .fillMaxWidth(0.4f)
                    .padding(top = 6.dp)
            )
            Spacer(modifier = modifier.padding(vertical = 30.dp))

        }

        items(items = ActionType.values()) { action ->
            Column(horizontalAlignment = Alignment.Start,
                modifier = modifier.clickable(
                    role = Role.Button,
                ) {
                    when (action) {
                        ActionType.UPDATE_PROFILE -> {
                            navController.navigate(MainDestination.UpdateProfile.route)
                        }

                        ActionType.LOGOUT -> {
                            firebaseAuthViewModel.signOut()
                            navController.navigate(AuthDestination.Auth.route) {
                                popUpTo(Graph.MAIN) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        }
                    }
                }) {
                Text(
                    action.actionName, fontSize = 12.sp, fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Left
                )
                Spacer(modifier = modifier.padding(vertical = 2.dp))
                Divider(modifier = modifier.fillMaxWidth(0.9f))
            }
            Spacer(modifier = modifier.padding(vertical = 15.dp))
        }

    }
}