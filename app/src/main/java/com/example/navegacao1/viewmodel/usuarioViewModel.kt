package com.example.navegacao1.viewmodel

import androidx.lifecycle.ViewModel
import com.example.navegacao1.model.dados.Usuario
import com.example.navegacao1.model.dados.UsuarioDAO

class UsuarioViewModel : ViewModel() {

    private val usuarioDAO = UsuarioDAO()

    fun adicionarUsuario(usuario: Usuario, callback: (Usuario?) -> Unit) {
        usuarioDAO.adicionar(usuario) { usuarioAdicionado ->
            callback(usuarioAdicionado)
        }
    }
}
