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
package com.vaadin.starter.beveragebuddy.ui.views.reviewslist;

import java.time.LocalDate;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.data.validator.DateRangeValidator;
import com.vaadin.flow.data.validator.IntegerRangeValidator;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.starter.beveragebuddy.backend.Category;
import com.vaadin.starter.beveragebuddy.backend.CategoryService;
import com.vaadin.starter.beveragebuddy.backend.Review;
import com.vaadin.starter.beveragebuddy.ui.common.AbstractShowDialog;

/**
 * A dialog for editing {@link Review} objects.
 */
public class ReviewShowDialog extends AbstractShowDialog<Review> {

    private transient CategoryService categoryService = CategoryService
            .getInstance();

    private ComboBox<Category> categoryBox = new ComboBox<>();
    private ComboBox<String> scoreBox = new ComboBox<>();
    private DatePicker lastTasted = new DatePicker();
    private TextField beverageName = new TextField();
    private TextField timesTasted = new TextField();
    private Image imagem = new Image();

  //  public ReviewShowDialog(BiConsumer<Review, Operation> saveHandler, Consumer<Review> deleteHandler) {
     //   super("livro", saveHandler, deleteHandler);

   //     createNameField();
   //     createCategoryBox();
  //      createDatePicker();
  //      createTimesField();
  //      createScoreBox();
 //   }


	public ReviewShowDialog() {
		   super("livro");

	        createNameField();
	        createCategoryBox();
	        createDatePicker();
	        createTimesField();
	        createScoreBox();
	        createImgBox();
	}




	private void createImgBox() {
		

		imagem.setSrc("https://www.learygates.com/wp-content/uploads/2014/06/MakingIdeasHappen.jpg");
		  getFormLayout().add(imagem);
}




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
                .bind(Review::getScore, Review::setScore).setReadOnly(true);
    }

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
                .bind(Review::getDate, Review::setDate).setReadOnly(true);

    }

    private void createCategoryBox() {
        categoryBox.setLabel("Categoria");
        categoryBox.setRequired(true);
        categoryBox.setItemLabelGenerator(Category::getName);
        categoryBox.setAllowCustomValue(false);
        categoryBox.setItems(categoryService.findCategories(""));
        getFormLayout().add(categoryBox);

        getBinder().forField(categoryBox)
                .withValidator(Objects::nonNull,
                        "The category should be defined.")
                .bind(Review::getCategory, Review::setCategory).setReadOnly(true);
    }

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
                .bind(Review::getCount, Review::setCount).setReadOnly(true);
    }

    private void createNameField() {
        beverageName.setLabel("Titulo");
        beverageName.setRequired(true);
        getFormLayout().add(beverageName);


        getBinder().forField(beverageName).withConverter(String::trim, String::trim).withValidator(new StringLengthValidator("Beverage name must contain at least 3 printable characters",3, null)).bind(Review::getName, Review::setName).setReadOnly(true);
    }

	@Override
	protected void confirmDelete() {
		// TODO Auto-generated method stub
		
	}



}
