package br.dcc.ufba.mata63.balaiolivros.backend.controllers;

import br.dcc.ufba.mata63.balaiolivros.backend.StaticData;
import br.dcc.ufba.mata63.balaiolivros.backend.models.UsuarioModel;

/**
 *
 * @author jeferson
 */
public class LoginService {

    /**
     * Helper class to initialize the singleton Service in a thread-safe way and
     * to keep the initialization ordering clear between the two services. See
     * also: https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom
     */
    private static class SingletonHolder {
        static final LoginService INSTANCE = createLoginService();

        /** This class is not meant to be instantiated. */
        private SingletonHolder() {
        }

        private static LoginService createLoginService() {
            final LoginService reviewService = new LoginService();
         
            return reviewService;
        }

    }

    private UsuarioModel usuarioAutenticado = null;

    /**
     * Declared private to ensure uniqueness of this Singleton.
     */
    private LoginService() {
    }

    /**
     * Gets the unique instance of this Singleton.
     *
     * @return the unique instance of this Singleton
     */
    public static LoginService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * Deletes the given review from the review store.
     *
     * @param username
     *          Nome do usuario a ser autenticado
     * @param senha
     *          Senha do usuario a ser autenticado
     * @return true if the operation was successful, otherwise false
     */
    public boolean authenticateUser(String username, String senha) {
        // Tenta autenticar o usuario e salva a referência no serviço
        if(StaticData.USUARIOS.containsKey(username) &&
                StaticData.USUARIOS.get(username).authenticate(senha)){
            usuarioAutenticado = StaticData.USUARIOS.get(username);
            return true;
        }
        return false;
    }

    /**
     * 
     * Checa se existe um usuario autenticado.
     * 
     * @return true se o usuario está autenticado, caso contrário falso
     */
    public boolean isUserAuthenticated(){
        return usuarioAutenticado != null;
    }
    
    /**
     * 
     * Retorna o usuario autenticado no momento.
     * 
     * @return retorna o usuario autenticado
     */
    public UsuarioModel getUserAuthenticated(){
        return usuarioAutenticado;
    }
    
}
