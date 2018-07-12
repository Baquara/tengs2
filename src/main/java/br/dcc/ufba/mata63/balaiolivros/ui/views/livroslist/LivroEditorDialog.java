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

import java.util.Objects;
import java.util.regex.Pattern;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.data.converter.StringToDoubleConverter;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.data.validator.DoubleRangeValidator;
import com.vaadin.flow.data.validator.IntegerRangeValidator;
import com.vaadin.flow.function.ValueProvider;
import br.dcc.ufba.mata63.balaiolivros.backend.models.CategoriaModel;
import br.dcc.ufba.mata63.balaiolivros.backend.controllers.CategoriaService;
import br.dcc.ufba.mata63.balaiolivros.backend.models.LivroModel;
import br.dcc.ufba.mata63.balaiolivros.ui.common.AbstractEditorDialog;

/**
 * A dialog for editing {@link LivroModel} objects.
 */
public class LivroEditorDialog extends AbstractEditorDialog<LivroModel> {

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
    private final TextField acabamentoLivro = new TextField();
    private final TextField edicaoLivro = new TextField();
    private final TextField anoEdicaoLivro = new TextField();
    private final TextField editoraLivro = new TextField();
    private final TextField autorLivro = new TextField();
    
    public LivroEditorDialog(BiConsumer<LivroModel, Operation> saveHandler,
            Consumer<LivroModel> deleteHandler) {
        super("livro", saveHandler, deleteHandler);

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
        
        // Campo editora
        createEditoraField();
        
        // Campo autor
        createAutorField();
        
        // Campo Acabamento 
        createAcabamentoField();
        
        // Campo Edicao
        createEdicaoField();
        
        // Campo ano edicao
        createAnoedicaoField();
        
        /*
        createIdiomaField()
        createPaisorigemField();
         */

        /*
        createDatePicker();
        createTimesField();
        createScoreBox();
         */
    }

    /*
    private void createScoreBox() {
        scoreBox.setLabel("Avaliacao");
        scoreBox.setRequired(true);
        scoreBox.setAllowCustomValue(false);
        scoreBox.setItems("1", "2", "3", "4", "5");
        getFormLayout().add(scoreBox);
        
        
        getBinder().forField(scoreBox)
                .withConverter(new StringToIntegerConverter(0,
                        "The score should be a number."))
                .withValidator(new IntegerRangeValidator(
                        "The tasting count must be between 1 and 5.", 1, 5))
                .bind(LivroModel::getScore, LivroModel::setScore);
        
    }
     */

 /*
    private void createDatePicker() {
        lastTasted.setLabel("Ultimo adicionado");
        lastTasted.setRequired(true);
        lastTasted.setMax(LocalDate.now());
        lastTasted.setMin(LocalDate.of(1, 1, 1));
        lastTasted.setValue(LocalDate.now());
        getFormLayout().add(lastTasted);

        getBinder().forField(lastTasted)
                .withValidator(Objects::nonNull,
                        "The date should be in MM/dd/yyyy format.")
                .withValidator(new DateRangeValidator(
                        "The date should be neither Before Christ nor in the future.",
                        LocalDate.of(1, 1, 1), LocalDate.now()))
                .bind(LivroModel::getDate, LivroModel::setDate);

    }
     */

 /*
    private void createTimesField() {
        timesTasted.setLabel("Numero de exemplares");
        timesTasted.setRequired(true);
        timesTasted.setPattern("[0-9]*");
        timesTasted.setPreventInvalidInput(true);
        getFormLayout().add(timesTasted);

        getBinder().forField(timesTasted)
                .withConverter(
                        new StringToIntegerConverter(0, "Must enter a number."))
                .withValidator(new IntegerRangeValidator(
                        "The tasting count must be between 1 and 99.", 1, 99))
                .bind(LivroModel::getCount, LivroModel::setCount);
    }
     */
    private void createNameField() {
        nomeLivro.setLabel("Titulo");
        nomeLivro.setRequired(true);
        getFormLayout().add(nomeLivro);

        getBinder().forField(nomeLivro)
                .withConverter(String::trim, String::trim)
                .withValidator(new StringLengthValidator(
                        "Nome do livro deve conter entre 3 e 100 caracteres",
                        3, 100))
                .bind(LivroModel::getNome, LivroModel::setNome);
    }

    @Override
    protected void confirmDelete() {
        openConfirmationDialog("Deletar livro",
                "Você tem certeza que deseja deletar “" + getCurrentItem().getNome() + "”?", "");
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
                .bind(LivroModel::getPeso, LivroModel::setPeso);
    }

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
                .bind(LivroModel::getCategory, LivroModel::setCategory);
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
                        if(isbn_puro.length() == 10)
                            tam_verificacao = 9;
                        else if(isbn_puro.length() == 13)
                            tam_verificacao = 12;
                        
                        // Digito verificador
                        int digito_verificador = isbn_puro.charAt(tam_verificacao) - '0';
                        
                        // Realiza o checksum
                        int checksum = 0;
                        for(int i = 0; i < tam_verificacao; i++){
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
                .bind(LivroModel::getISBN, LivroModel::setISBN);
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
                .bind(get_dimensao, set_dimensao);
    }

    
    private void createDimensoesField(){
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
                .bind(LivroModel::getNpaginas, LivroModel::setNpaginas);
    }

    private void createIdiomaField() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void createAcabamentoField() {
        acabamentoLivro.setLabel("Acabamento");
        acabamentoLivro.setRequired(false);
        getFormLayout().add(acabamentoLivro);

        getBinder().forField(acabamentoLivro)
                .withConverter(String::trim, String::trim)
                .withNullRepresentation("")
                .bind(LivroModel::getAcabamento, LivroModel::setAcabamento);
    }

    private void createEdicaoField() {
        edicaoLivro.setLabel("Edicao");
        edicaoLivro.setRequired(true);
        getFormLayout().add(edicaoLivro);

        getBinder().forField(edicaoLivro)
                .withConverter(String::trim, String::trim)
                .withNullRepresentation("")
                .withValidator(new StringLengthValidator(
                        "Edição deve conter entre 3 e 100 caracteres",
                        3, 100))
                .bind(LivroModel::getEdicao, LivroModel::setEdicao);
    }

    private void createAnoedicaoField() {
        anoEdicaoLivro.setLabel("Ano edição");
        anoEdicaoLivro.setRequired(true);
        anoEdicaoLivro.setPattern("[0-9]*");
        anoEdicaoLivro.setPreventInvalidInput(true);
        getFormLayout().add(anoEdicaoLivro);

        getBinder().forField(anoEdicaoLivro)
                .withConverter(
                        new StringToIntegerConverter(0, "Precisa ser um número."))
                .withValidator(
                        new IntegerRangeValidator("O ano de edição tem que ser válido", 1400, null))
                .bind(LivroModel::getAnoedicao, LivroModel::setAnoedicao);
    }

    private void createPaisorigemField() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void createEditoraField() {
        editoraLivro.setLabel("Editora");
        editoraLivro.setRequired(true);
        getFormLayout().add(editoraLivro);

        getBinder().forField(editoraLivro)
                .withConverter(String::trim, String::trim)
                .withNullRepresentation("")
                .withValidator(new StringLengthValidator(
                        "O Nome da editora deve ser válido",
                        1, 100))
                .bind(LivroModel::getEditora, LivroModel::setEditora);
    }

    private void createAutorField() {
        autorLivro.setLabel("Autor");
        autorLivro.setRequired(true);
        getFormLayout().add(autorLivro);

        getBinder().forField(autorLivro)
                .withConverter(String::trim, String::trim)
                .withNullRepresentation("")
                .withValidator(new StringLengthValidator(
                        "O nome do autor deve estar completo",
                        3, 200))
                .bind(LivroModel::getAutor, LivroModel::setAutor);
    }

}
