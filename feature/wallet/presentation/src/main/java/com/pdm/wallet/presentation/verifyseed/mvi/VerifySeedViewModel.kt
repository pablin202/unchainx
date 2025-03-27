package com.pdm.wallet.presentation.verifyseed.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm.wallet.domain.usecase.DeriveWalletGroupsUseCase
import com.pdm.wallet.domain.usecase.SaveSeedAndPassPhraseInSecureStore
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class VerifySeedViewModel(
    private val deriveWalletUseCase: DeriveWalletGroupsUseCase,
    private val saveSeedAndPassPhraseInSecureStore: SaveSeedAndPassPhraseInSecureStore
) : ViewModel() {

    private val mutableEffect = MutableSharedFlow<Effects>()
    val effect: SharedFlow<Effects> = mutableEffect.asSharedFlow()

    fun event(event: Event) {
        when (event) {
            is Event.GetDerivedWallet -> {
                viewModelScope.launch {
                    val wallets = deriveWalletUseCase(event.mnemonic, event.passphrase)
                    if (wallets.isNotEmpty()) {
                        saveSeedAndPassPhraseInSecureStore(event.mnemonic, event.passphrase)
                    }
                }
            }
        }
    }
}
