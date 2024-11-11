package br.com.librastils.Service;

import br.com.librastils.models.usuario.Usuario;
import br.com.librastils.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario criar(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Email já em uso");
        }
        if(usuario.getUser()!= null){
            throw new RuntimeException("O Nome está vazio");
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarUsuarioPorCpf(String cpf){
        var aux = usuarioRepository.findById(cpf);
        if(aux.isEmpty()){
            throw new RuntimeException("Não existe esse usuário");
        }
        return aux.get();
    }

    public List<Usuario> buscarTodos(){
        return  usuarioRepository.findAll();
    }

    public void deletar(String cpf){
        usuarioRepository.deleteById(cpf);
    }

    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario findByCpf(String cpf){
        var aux = usuarioRepository.findById(cpf);
        Usuario usuario = null;
        if(aux.isPresent()){
            usuario = aux.get();
        }
        return usuario;
    }
    public void delete(String cpf){
        usuarioRepository.deleteById(cpf);
    }

    public Usuario update(Usuario usuario, String cpf){
        var aux = findByCpf(cpf);
        if(usuario.getCpf()!=null){
            aux.setCpf(usuario.getCpf());
        }
        if(usuario.getUser()!=null){
            aux.setUser(usuario.getUser());
        }
        return  usuarioRepository.save(aux);
    }
    public List<Usuario> findAll(){
        return  usuarioRepository.findAll();
    }
}
