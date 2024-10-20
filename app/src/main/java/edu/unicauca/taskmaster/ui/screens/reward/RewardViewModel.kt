package edu.unicauca.taskmaster.ui.screens.reward

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

class RewardViewModel : ViewModel() {

    // Lista de recompensas
    private val _rewardsList = mutableStateListOf<RewardItem>()
    val rewardsList: SnapshotStateList<RewardItem> = _rewardsList

    // Agregar una nueva recompensa
    fun addReward(rewardName: String) {
        if (rewardName.isNotBlank()) {
            _rewardsList.add(RewardItem(rewardName))
        }
    }

    // Marcar recompensa como lograda
    fun markAsAchieved(reward: RewardItem) {
        val index = _rewardsList.indexOf(reward)
        if (index != -1) {
            _rewardsList[index] = _rewardsList[index].copy(achieved = true)
        }
    }
}
