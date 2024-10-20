package edu.unicauca.taskmaster.ui.screens.config

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import edu.unicauca.taskmaster.ui.theme.*

@Composable
fun ConfigScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        BackgroundWithCircles(blurRadius = 0.4f)
        Column(
            modifier = Modifier.fillMaxSize(),
            //verticalArrangement = Arrangement.SpaceBetween
        ) {
            HeaderTask(
                imagen = R.drawable.icono_task_master,
                textoHeader = "Configuración",
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
                ConfigSection()
            }
        }
    }
}

@Composable
fun ConfigSection() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ){
            NightSwitch()
            Spacer(modifier = Modifier.height(16.dp))
            ItemConfig("Recompensas", R.drawable.icon_settings){}
        }
    }
}

@Composable
fun ItemConfig(
    text: String,
    icon: Int,
    onClick: () -> Unit,
    //composable: Composable.() -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(blue)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            color = black,
            fontWeight = FontWeight.Bold
        )
        IconButton(
            onClick = onClick,
            modifier = Modifier.size(60.dp)
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

@Composable
fun NightSwitch(){
    var isNightModeOn by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(blue)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Modo noche",
            fontSize = 18.sp,
            color = black,
            fontWeight = FontWeight.Bold
        )
        Switch(
            checked = isNightModeOn,
            onCheckedChange = { isNightModeOn = it },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Black,
                uncheckedThumbColor = Color.Gray
            )
        )
    }
}

@Composable
fun RewardSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(blue)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Recompensas",
            fontSize = 18.sp,
            color = black,
            fontWeight = FontWeight.Bold
        )
        IconButton(
            onClick = { },
            modifier = Modifier.size(60.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_settings),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

@Preview
@Composable
fun ConfigScreenPreview() {
    ConfigScreen()
}