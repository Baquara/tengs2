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

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.data.validator.DateRangeValidator;
import com.vaadin.flow.data.validator.IntegerRangeValidator;
import com.vaadin.flow.data.validator.StringLengthValidator;
import br.dcc.ufba.mata63.balaiolivros.backend.models.CategoriaModel;
import br.dcc.ufba.mata63.balaiolivros.backend.controllers.CategoriaService;
import br.dcc.ufba.mata63.balaiolivros.backend.models.LivroModel;
import br.dcc.ufba.mata63.balaiolivros.ui.common.AbstractEditorDialog;
import com.vaadin.flow.data.converter.StringToDoubleConverter;

/**
 * A dialog for editing {@link LivroModel} objects.
 */
public class LivroEditorDialog extends AbstractEditorDialog<LivroModel> {

    private final transient CategoriaService categoryService = CategoriaService
            .getInstance();

    private final ComboBox<CategoriaModel> categoriaBox = new ComboBox<>();
    private final TextField nomeLivro = new TextField();
    private final TextField pesoLivro = new TextField();
        
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
        //createISBNField();
        
        /*
        createAlturaField();
        createLarguraField();
        createProfundidadeField();
        createNpaginasField();
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
    private void createCategoryBox() {
        categoryBox.setLabel("Categoria");
        categoryBox.setRequired(true);
        categoryBox.setItemLabelGenerator(CategoriaModel::getName);
        categoryBox.setAllowCustomValue(false);
        categoryBox.setItems(categoryService.findCategories(""));
        getFormLayout().add(categoryBox);

        getBinder().forField(categoryBox)
                .withValidator(Objects::nonNull,
                        "The category should be defined.")
                .bind(LivroModel::getCategory, LivroModel::setCategory);
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
                        "Nome do livro deve conter ao menos 3 caracteres",
                        3, null))
                .bind(LivroModel::getNome, LivroModel::setNome);
    }

    @Override
    protected void confirmDelete() {
        openConfirmationDialog("Deletar livro",
                "Você tem certeza que deseja deletar “" + getCurrentItem().getNome() + "”?", "");
    }

    private void createPesoField() {
        pesoLivro.setLabel("Peso (kg)");
        pesoLivro.setRequired(true);
        pesoLivro.setPreventInvalidInput(true);
        getFormLayout().add(pesoLivro);

        getBinder().forField(pesoLivro)
                .withConverter(
                        new StringToDoubleConverter(0.0, "Precisa ser um número e maior que zero."))
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
                        "The category should be defined.")
                .bind(LivroModel::getCategory, LivroModel::setCategory);
     }
    
    private void createISBNField() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void createAlturaField() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void createLarguraField() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void createProfundidadeField() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void createNpaginasField() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}
