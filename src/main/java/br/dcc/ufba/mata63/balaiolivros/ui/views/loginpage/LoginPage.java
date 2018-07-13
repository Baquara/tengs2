/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.dcc.ufba.mata63.balaiolivros.ui.views.loginpage;

import br.dcc.ufba.mata63.balaiolivros.backend.controllers.LoginService;
import br.dcc.ufba.mata63.balaiolivros.backend.models.LivroModel;
import br.dcc.ufba.mata63.balaiolivros.backend.models.UsuarioLoginModel;
import br.dcc.ufba.mata63.balaiolivros.backend.models.UsuarioModel;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *
 * @author jeferson
 */
@Route(value = "login")
@PageTitle("Balaio de Livros - Página de Login")
@HtmlImport("frontend://styles/shared-styles.html")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class LoginPage extends VerticalLayout {

    private final H1 title = new H1("Balaio de Livros");
    private final H2 header = new H2("Login ");

    private final Binder<UsuarioLoginModel> binder = new Binder<>();

    private static final Div ERROR_NOTIFICATION_DIV = new Div(
            new Text("Erro ao efetuar login!"));
    private static final Notification ERROR_NOTIFICATION = new Notification(ERROR_NOTIFICATION_DIV);

    private static final Div SUCCESS_NOTIFICATION_DIV = new Div(
            new Text("Login efetuado com sucesso!"));
    private static final Notification SUCCESS_NOTIFICATION = new Notification(SUCCESS_NOTIFICATION_DIV);

    private static final int NOTIFICATION_TIMEOUT = 5000;

    public LoginPage() {
        initView();
        addContent();
    }

    private void initView() {
        addClassName("login-page");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
        SUCCESS_NOTIFICATION_DIV.setClassName("success-notification");
        ERROR_NOTIFICATION_DIV.setClassName("error-notification");
        SUCCESS_NOTIFICATION.setDuration(NOTIFICATION_TIMEOUT);
        ERROR_NOTIFICATION.setDuration(NOTIFICATION_TIMEOUT);
    }

    private void addContent() {
        VerticalLayout container = new VerticalLayout();
        container.setClassName("view-container");
        container.setAlignItems(Alignment.CENTER);
        container.setSizeFull();

        // Criar uma div e centralizar ela no meio da página
        Div loginPanel = new Div();
        loginPanel.setClassName("login-panel");

        // Adiciona cabeçalho
        title.setClassName("login-header");
        header.setClassName("login-header");
        loginPanel.add(title, header);

        // Cria form e adiciona a div
        VerticalLayout formContent = new VerticalLayout();
        loginPanel.add(formContent);

        // Inicializa o binder
        getBinder().setBean(new UsuarioLoginModel("",""));
        
        // Campo usuario
        TextField username = new TextField("Usuario");
        username.setClassName("login-field");
        username.setRequired(true);
        binder.forField(username)
                .withConverter(String::trim, String::trim)
                .withNullRepresentation("")
                .withValidator(new StringLengthValidator(
                        "Insira um username válido válido",
                        3, 100))
                .bind(UsuarioLoginModel::getUsername, UsuarioLoginModel::setUsername);
        formContent.add(username);

        // Campo senha
        PasswordField password = new PasswordField("Senha");
        password.setClassName("login-field");
        password.setRequired(true);
        binder.forField(password)
                .withConverter(String::trim, String::trim)
                .withNullRepresentation("")
                .withValidator(new StringLengthValidator(
                        "Insira uma senha válida",
                        3, 100))
                .bind(UsuarioLoginModel::getSenha, UsuarioLoginModel::setSenha);
        formContent.add(password);

        // Fazer login
        Button fazerLogin = new Button("Login");
        fazerLogin.setClassName("login-button");
        loginPanel.add(fazerLogin);
        
        // Tenta realizar o login
        fazerLogin.addClickListener(
                e -> {
                    // Pega instância do serviço de Login
                    LoginService loginService = LoginService.getInstance();

                    // Pega os dados do usuario
                    UsuarioLoginModel usuario = getBinder().getBean();

                    // Tenta autenticar com a plataforma
                    if (loginService.authenticateUser(usuario)) {
                        // Mostra notificação
                        SUCCESS_NOTIFICATION.open();

                        // Redireciona usuario para página inicial
                        getUI().ifPresent(ui -> ui.navigate(""));
                    } else {
                        // Notifica que não foi possível realizar a ação
                        ERROR_NOTIFICATION.open();
                    }
                });

        // Adiciona form a página
        container.add(loginPanel);

        // Adiciona container a página
        add(container);
    }

    /**
     *
     * Get binder de model usuario
     *
     * @return Binder modificador do model Usuario
     *
     */
    public Binder<UsuarioLoginModel> getBinder() {
        return binder;
    }

}
