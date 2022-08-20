package com.example.profilecallchat

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class RoomViewModel {

        private val _roomName = mutableStateOf(TextFieldState())
        val roomName: State<TextFieldState> = _roomName

        private val _onJoinEvent = MutableSharedFlow<String>()
        val onJoinEvent = _onJoinEvent.asSharedFlow()

        fun onRoomEnter(name: String) {
            _roomName.value = roomName.value.copy(
                text = name
            )
        }

        fun onJoinRoom(viewModelScope: Any) {
            if(roomName.value.text.isBlank()) {
                _roomName.value = roomName.value.copy(
                    error = "The room can't be empty"
                )
                return
            }
            viewModelScope.launch {
                _onJoinEvent.emit(roomName.value.text)
            }
        }

}

private fun Any.launch(block: suspend CoroutineScope.() -> Unit) {

}
