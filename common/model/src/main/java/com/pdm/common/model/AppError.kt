package com.pdm.common.model

sealed class AppError {
    sealed class NetworkError(message: String) : AppError() {
        data class ConnectivityError(val errorMsg: String) : NetworkError(errorMsg)
        data class Timeout(val errorMsg: String) : NetworkError(errorMsg)
        data class ServerError(val errorMsg: String, val code: Int) : NetworkError(errorMsg)
        data class UnknownNetwork(val errorMsg: String, val throwable: Throwable? = null) : NetworkError(errorMsg)
    }

    sealed class LocalError(message: String) : AppError() {
        data class DatabaseError(val errorMsg: String, val throwable: Throwable? = null) : LocalError(errorMsg)
        data class FileSystemError(val errorMsg: String, val throwable: Throwable? = null) : LocalError(errorMsg)
        data class CacheError(val errorMsg: String) : LocalError(errorMsg)
        data class DataNotFound(val errorMsg: String) : LocalError(errorMsg)
    }

    sealed class CryptoError(message: String) : AppError() {
        data class EncryptionError(val errorMsg: String, val throwable: Throwable? = null) : CryptoError(errorMsg)
        data class DecryptionError(val errorMsg: String, val throwable: Throwable? = null) : CryptoError(errorMsg)
        data class KeyGenerationError(val errorMsg: String, val throwable: Throwable? = null) : CryptoError(errorMsg)
        data class InvalidKey(val errorMsg: String, val throwable: Throwable? = null) : CryptoError(errorMsg)
    }

    data class UnknownError(val errorMsg: String, val throwable: Throwable? = null) : AppError()
}
