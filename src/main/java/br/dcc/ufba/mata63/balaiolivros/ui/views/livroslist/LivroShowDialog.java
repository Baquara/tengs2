/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package br.dcc.ufba.mata63.balaiolivros.ui.views.livroslist;

import java.time.LocalDate;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.regex.Pattern;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.data.converter.StringToDoubleConverter;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.data.validator.DoubleRangeValidator;
import com.vaadin.flow.data.validator.IntegerRangeValidator;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.function.ValueProvider;
import br.dcc.ufba.mata63.balaiolivros.backend.controllers.CategoriaService;
import br.dcc.ufba.mata63.balaiolivros.backend.models.CategoriaModel;
import br.dcc.ufba.mata63.balaiolivros.backend.models.LivroModel;

/**
 * A dialog for editing {@link Review} objects.
 */
public class LivroShowDialog extends br.dcc.ufba.mata63.balaiolivros.ui.common.AbstractShowDialog<LivroModel> {

    private final transient CategoriaService categoryService = CategoriaService
            .getInstance();

    private final ComboBox<CategoriaModel> categoriaBox = new ComboBox<>();
    private final TextField nomeLivro = new TextField();
    private final TextField pesoLivro = new TextField();
    private final TextField ISBNLivro = new TextField();
    private final TextField alturaLivro = new TextField();
    private final TextField larguraLivro = new TextField();
    private final TextField profundidadeLivro = new TextField();
    private final TextField nPaginasLivro = new TextField();
    private Image imagem = new Image();

    //  public ReviewShowDialog(BiConsumer<Review, Operation> saveHandler, Consumer<Review> deleteHandler) {
    //   super("livro", saveHandler, deleteHandler);
    //     createNameField();
    //     createCategoryBox();
    //      createDatePicker();
    //      createTimesField();
    //      createScoreBox();
    //   }
    public LivroShowDialog() {
        super("livro");

        // Campo do nome do livro
        createNameField();

        // Campo de categoria do livro
        createCategoryBox();

        // Campo de peso do livro
        createPesoField();

        // Campo de inserção do ISBN
        createISBNField();

        // Campos das dimensões da página
        createDimensoesField();

        // Campo de número de páginas
        createNpaginasField();
        // Campo de imagens
        createImgBox();

        /*
	        createIdiomaField();
	        createAcabamentoField();
	        createEdicaoField();
	        createAnoedicaoField();
	        createPaisorigemField();
	        createEditoraField();
	        createAutorField();
         */

 /*
	        createDatePicker();
	        createTimesField();
	        createScoreBox();
         */
    }

    private void createImgBox() {

        imagem.setSrc("https://www.learygates.com/wp-content/uploads/2014/06/MakingIdeasHappen.jpg");
        getFormLayout().add(imagem);
    }

//.setReadOnly(true);
    private void createCategoryBox() {
        categoriaBox.setLabel("Categoria");
        categoriaBox.setRequired(true);
        categoriaBox.setItemLabelGenerator(CategoriaModel::getName);
        categoriaBox.setAllowCustomValue(false);
        categoriaBox.setItems(categoryService.findCategories(""));
        getFormLayout().add(categoriaBox);

        getBinder().forField(categoriaBox)
                .withValidator(Objects::nonNull,
                        "É preciso escolher uma categoria.")
                .bind(LivroModel::getCategory, LivroModel::setCategory).setReadOnly(true);
    }

    private void createNameField() {
        nomeLivro.setLabel("Titulo");
        nomeLivro.setRequired(true);
        getFormLayout().add(nomeLivro);

        getBinder().forField(nomeLivro)
                .withConverter(String::trim, String::trim)
                .withValidator(new StringLengthValidator(
                        "Nome do livro deve conter entre 3 e 100 caracteres",
                        3, 100))
                .bind(LivroModel::getNome, LivroModel::setNome).setReadOnly(true);
    }

    private void createPesoField() {
        pesoLivro.setLabel("Peso (g)");
        pesoLivro.setRequired(true);
        pesoLivro.setPattern("([0-9]*[.])?[0-9]*");
        pesoLivro.setPreventInvalidInput(true);
        getFormLayout().add(pesoLivro);

        getBinder().forField(pesoLivro)
                .withConverter(
                        new StringToDoubleConverter(0.0, "Precisa ser um número e maior que zero."))
                .withValidator(
                        new DoubleRangeValidator("O livro deve ter entre 10 e 10.000g (10Kg)", 10.0, 10000.00))
                .bind(LivroModel::getPeso, LivroModel::setPeso).setReadOnly(true);
    }

    private void createISBNField() {
        ISBNLivro.setLabel("ISBN");
        ISBNLivro.setRequired(true);
        ISBNLivro.setPattern("[0-9\\-]*");
        ISBNLivro.setPreventInvalidInput(true);
        ISBNLivro.setClassName("");
        getFormLayout().add(ISBNLivro);

        getBinder().forField(ISBNLivro)
                .withConverter(String::trim, String::trim)
                .withNullRepresentation("")
                .withValidator(
                        isbn -> {
                            isbn = isbn.replaceAll("-", "");
                            return (isbn.length() == 10 || isbn.length() == 13);
                        },
                        "O ISBN deve ter 10 ou 13 números (ignorando os hífens)")
                .withValidator(
                        isbn -> !Pattern.compile("[^0-9\\-]+").matcher(isbn).find(),
                        "O ISBN deve conter apenas números e hífens")
                .withValidator(
                        isbn -> !Pattern.compile("^-|-$").matcher(isbn).find(),
                        "O ISBN não pode começar nem terminar com um hifen"
                )
                .withValidator(
                        isbn -> {
                            String[] campos_isbn = isbn.split("-");
                            return campos_isbn[campos_isbn.length - 1].length() == 1;
                        },
                        "Digito verificador não pode ter mais que 1 caractere"
                )
                .withValidator(
                        isbn -> {
                            String isbn_puro = isbn.replace("-", "");
                            boolean verificacao;

                            // Checa a quantidade de caracteres a serem verificados
                            int tam_verificacao = 0;
                            if (isbn_puro.length() == 10) {
                                tam_verificacao = 9;
                            } else if (isbn_puro.length() == 13) {
                                tam_verificacao = 12;
                            }

                            // Digito verificador
                            int digito_verificador = isbn_puro.charAt(tam_verificacao) - '0';

                            // Realiza o checksum
                            int checksum = 0;
                            for (int i = 0; i < tam_verificacao; i++) {
                                int digito = isbn_puro.charAt(i) - '0';
                                checksum += (i % 2 == 0) ? digito * 1 : digito * 3;
                            }

                            // Finaliza o checksum. O mesmo deve estar entre 0-9
                            checksum = (10 - (checksum % 10)) % 10;

                            // Verifica se o digito verificador tem o mesmo valor do checksum
                            verificacao = checksum == digito_verificador;

                            return verificacao;
                        },
                        "Digito verificador inválido, verificar ISBN"
                )
                .bind(LivroModel::getISBN, LivroModel::setISBN).setReadOnly(true);
    }

    private void createDimensaoField(String nome_dimensao,
            TextField dimensaoInput,
            ValueProvider<LivroModel, Double> get_dimensao,
            Setter<LivroModel, Double> set_dimensao) {
        dimensaoInput.setLabel(nome_dimensao + " (cm)");
        dimensaoInput.setRequired(true);
        dimensaoInput.setPattern("([0-9]*[.])?[0-9]*");
        dimensaoInput.setPreventInvalidInput(true);
        getFormLayout().add(dimensaoInput);

        getBinder().forField(dimensaoInput)
                .withConverter(
                        new StringToDoubleConverter(0.0, "Precisa ser um número e maior que zero."))
                .withValidator(
                        new DoubleRangeValidator("O livro deve ter entre 1 e 100cm", 1.00, 100.00))
                .bind(get_dimensao, set_dimensao).setReadOnly(true);
    }

    private void createDimensoesField() {
        createDimensaoField(
                "Altura",
                alturaLivro,
                LivroModel::getAltura,
                LivroModel::setAltura);
        createDimensaoField(
                "Largura",
                larguraLivro,
                LivroModel::getLargura,
                LivroModel::setLargura);
        createDimensaoField(
                "Profundidade",
                profundidadeLivro,
                LivroModel::getProfundidade,
                LivroModel::setProfundidade);
    }

    private void createNpaginasField() {
        nPaginasLivro.setLabel("Numero de páginas");
        nPaginasLivro.setRequired(true);
        nPaginasLivro.setPattern("[0-9]*");
        nPaginasLivro.setPreventInvalidInput(true);
        getFormLayout().add(nPaginasLivro);

        getBinder().forField(nPaginasLivro)
                .withConverter(
                        new StringToIntegerConverter(0, "Precisa ser um número."))
                .withValidator(
                        new IntegerRangeValidator("O livro precisa ter ao menos uma página", 1, null))
                .bind(LivroModel::getNpaginas, LivroModel::setNpaginas).setReadOnly(true);
    }

    private void createIdiomaField() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void createAcabamentoField() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void createEdicaoField() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void createAnoedicaoField() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void createPaisorigemField() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void createEditoraField() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void createAutorField() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void confirmDelete() {
        // TODO Auto-generated method stub

    }

}
