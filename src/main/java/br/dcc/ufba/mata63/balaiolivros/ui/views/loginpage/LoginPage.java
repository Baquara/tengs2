/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.dcc.ufba.mata63.balaiolivros.ui.views.loginpage;

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
public class LoginPage extends VerticalLayout{
    private final H1 title = new H1("Balaio de Livros");
    private final H2 header = new H2("Login ");

    public LoginPage() {
        initView();
        addContent();
    }
    
    private void initView() {
        addClassName("login-page");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }
    
    private void addContent(){
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
	
        // Campo usuario
        TextField username = new TextField("Usuario");
        username.setClassName("login-field");
        username.setRequired(true);
	formContent.add(username);
	
        // Campo senha
        PasswordField password = new PasswordField("Senha");
        password.setClassName("login-field");
	password.setRequired(true);
        formContent.add(password);
        
        // Fazer login
        Button fazerLogin = new Button("Login");
        fazerLogin.setClassName("login-button");
        loginPanel.add(fazerLogin);
        
        //save.addClickListener(event ->
        //    Notification.show("Valid: " + binder.isValid()));
        
        // Adiciona form a página
        container.add(loginPanel);
        
        // Adiciona container a página
        add(container);
    }
    
}
