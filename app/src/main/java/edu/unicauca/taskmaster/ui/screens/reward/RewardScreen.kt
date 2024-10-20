package edu.unicauca.taskmaster.ui.screens.reward

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import edu.unicauca.taskmaster.R
import edu.unicauca.taskmaster.ui.screens.components.BackgroundWithCircles
import edu.unicauca.taskmaster.ui.screens.components.HeaderTask
import edu.unicauca.taskmaster.ui.screens.components.NavBar
import edu.unicauca.taskmaster.ui.theme.red

data class RewardItem(
    val name: String,
    var achieved: Boolean = false
)

@Composable
fun RewardScreen(
    onSettingsClicked: () -> Unit,
    onHomeClicked: () -> Unit,
    onCalendarClicked: () -> Unit,
    onAddClicked: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RewardViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    var showAddRewardDialog by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        BackgroundWithCircles(blurRadius = 0.4f)
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            HeaderTask(
                imagen = R.drawable.icono_task_master,
                textoHeader = "Recompensas",
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(1f)
            )

            Box(
                contentAlignment = Alignment.TopStart,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                listReward(viewModel)

                // Círculo que abre el diálogo
                Canvas(
                    modifier = Modifier
                    .fillMaxSize()
                    .clickable { showAddRewardDialog = true } // Abrir diálogo al hacer clic
                ) {
                    drawCircle(
                        color = red,
                        radius = size.width * (0.13f),
                        center = Offset(
                            x = size.width * (0.85f),
                            y = size.height * (0.91f)
                        )
                    )

                }
                Image(
                    painter = painterResource(id = R.drawable.icon_add_box),  // Agrega tu ícono aquí
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.BottomEnd)
                        .padding(15.dp),
                    contentScale = ContentScale.Crop
                )

            }

            NavBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(1f),
                onSettingsClicked = { onSettingsClicked() },
                onHomeClicked = { onHomeClicked() },
                onCalendarClicked = { onCalendarClicked() },
                onAddClicked = { onAddClicked() }
            )
        }

        // Mostrar el diálogo para agregar recompensas
        if (showAddRewardDialog) {
            AddRewardDialog(
                onDismiss = { showAddRewardDialog = false },
                onAddReward = { rewardName ->
                    viewModel.addReward(rewardName)
                    showAddRewardDialog = false
                }
            )
        }
    }
}


@Composable
fun AddRewardDialog(
    onDismiss: () -> Unit,
    onAddReward: (String) -> Unit
) {
    var rewardName by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Campo de texto para la recompensa
            BasicTextField(
                value = rewardName,
                onValueChange = { rewardName = it },
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        if (rewardName.isEmpty()) {
                            Text(text = "Escribe una recompensa", color = Color.Gray)
                        }
                        innerTextField()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            // Botón para agregar la recompensa
            Button(
                onClick = {
                    if (rewardName.isNotBlank()) {
                        onAddReward(rewardName)
                    }
                }
            ) {
                Text(text = "Agregar Recompensa")
            }

            // Botón para cerrar el diálogo
            Button(
                onClick = { onDismiss() }
            ) {
                Text(text = "Cancelar")
            }
        }
    }
}

@Composable
fun listReward(viewModel: RewardViewModel) {
    val rewardsList = viewModel.rewardsList

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Mostrar recompensas pendientes
        Text(
            text = "Por Lograr",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        rewardsList.filter { !it.achieved }.forEach { reward ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = reward.name, modifier = Modifier.weight(1f))
                Button(
                    onClick = { viewModel.markAsAchieved(reward) }
                ) {
                    Text(text = "Logrado")
                }
            }
        }

        // Mostrar recompensas conseguidas
        Text(
            text = "Conseguido",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        rewardsList.filter { it.achieved }.forEach { reward ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = reward.name, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Preview
@Composable
fun PreviewRewardScreen() {
    RewardScreen(
        onSettingsClicked = {},
        onHomeClicked = {},
        onCalendarClicked = {},
        onAddClicked = {}
    )
}