package edu.unicauca.taskmaster.ui.screens.reward

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    modifier: Modifier = Modifier
) {
    var rewardName by remember { mutableStateOf("") }
    var rewardsList by remember { mutableStateOf(mutableListOf<RewardItem>()) }

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
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Input para agregar nuevas recompensas
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

                    // Botón para agregar recompensas
                    Button(
                        onClick = {
                            if (rewardName.isNotBlank()) {
                                rewardsList.add(RewardItem(rewardName))
                                rewardName = ""
                            }
                        }
                    ) {
                        Text(text = "Agregar Recompensa")
                    }

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
                                onClick = { reward.achieved = true }
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