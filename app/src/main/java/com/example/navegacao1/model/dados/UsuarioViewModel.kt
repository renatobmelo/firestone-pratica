import androidx.lifecycle.ViewModel
import com.example.navegacao1.model.dados.Usuario
import com.example.navegacao1.model.dados.UsuarioDAO

class UsuarioViewModel : ViewModel() {
    private val usuarioDAO = UsuarioDAO()

    fun adicionarUsuario(usuario: Usuario) {
        usuarioDAO.adicionar(usuario) {
        }
    }
}
