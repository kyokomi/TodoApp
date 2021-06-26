package dev.kyokomi.todoapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.kyokomi.todoapp.data.repository.AccountSettingRepository
import dev.kyokomi.todoapp.model.AccountSettingEntity
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val accountSettingRepository: AccountSettingRepository,
) : ViewModel() {
    val accountSetting = accountSettingRepository.resolve()

    fun editAccountSetting(entity: AccountSettingEntity) = viewModelScope.launch {
        accountSettingRepository.update(entity)
    }
}
