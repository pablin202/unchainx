package com.pdm.wallet.presentation.createwallet.mvi

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.collection.emptyObjectList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm.common.model.AppError
import com.pdm.designsystems.utility.UiText
import com.pdm.wallet.domain.usecase.GenerateMnemonicUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreateWalletViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val generateMnemonicUseCase: GenerateMnemonicUseCase
) : ViewModel() {

    companion object {
        private const val MNEMONIC_KEY = "mnemonic"
    }

    private val mutableState = MutableStateFlow<UiState>(UiState.Idle)
    val state: StateFlow<UiState> = mutableState.asStateFlow()

    private val mutableEffect = MutableSharedFlow<Effects>()
    val effect: SharedFlow<Effects> = mutableEffect.asSharedFlow()

    fun event(event: Event) {
        when (event) {
            Event.CreateSeed -> createMnemonic()
            is Event.CopySeedToClipBoard -> copySeedToClipBoard(
                event.context
            )
        }
    }

    private fun copySeedToClipBoard(context: Context) {
        val seedText = (state.value as UiState.Success).seedPhrase.joinToString(separator = " ")
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Seed Phrase", seedText)
        clipboard.setPrimaryClip(clip)
        viewModelScope.launch {
            mutableEffect.emit(Effects.ShowMessage(UiText.SimpleString("Copied to clipboard")))
        }
    }

    private fun createMnemonic() {
        viewModelScope.launch {
            mutableState.value = UiState.Loading
            generateMnemonicUseCase().collect { either ->
                either.fold(
                    ifLeft = { error ->
                        when (error) {
                            is AppError.CryptoError.KeyGenerationError -> {
                                mutableState.value =
                                    UiState.Error(UiText.SimpleString(error.errorMsg))
                                mutableEffect.emit(Effects.ShowError(UiText.SimpleString(error.errorMsg)))
                            }

                            else -> {
                                mutableState.value =
                                    UiState.Error(UiText.SimpleString("Unknown error"))
                                mutableEffect.emit(Effects.ShowError(UiText.SimpleString("Unknown error")))
                            }
                        }
                        savedStateHandle[MNEMONIC_KEY] = emptyObjectList<String>()
                    },
                    ifRight = { words ->
                        mutableState.value = UiState.Success(words)
                        savedStateHandle[MNEMONIC_KEY] = words
                    }
                )
            }
        }
    }
}
