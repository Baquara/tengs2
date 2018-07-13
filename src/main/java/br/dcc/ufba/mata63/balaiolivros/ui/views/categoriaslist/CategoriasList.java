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
package br.dcc.ufba.mata63.balaiolivros.ui.views.categoriaslist;

import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import br.dcc.ufba.mata63.balaiolivros.backend.models.CategoriaModel;
import br.dcc.ufba.mata63.balaiolivros.backend.controllers.CategoriaService;
import br.dcc.ufba.mata63.balaiolivros.backend.models.LivroModel;
import br.dcc.ufba.mata63.balaiolivros.backend.controllers.LivroService;
import br.dcc.ufba.mata63.balaiolivros.ui.MainLayout;
import br.dcc.ufba.mata63.balaiolivros.ui.common.AbstractEditorDialog;

/**
 * Displays the list of available categories, with a search filter as well as
 * buttons to add a new category or edit existing ones.
 */
@Route(value = "categories", layout = MainLayout.class)
@PageTitle("Balaio de Livros - Lista de Categorias")
public class CategoriasList extends VerticalLayout {

    private final TextField searchField = new TextField("", "Buscar categorias");
    private final H2 header = new H2("Categorias");
    private final Grid<CategoriaModel> grid = new Grid<>();

    private final CategoriaEditorDialog form = new CategoriaEditorDialog(
            this::saveCategory, this::deleteCategory);

    public CategoriasList() {
        initView();

        addSearchBar();
        addContent();

        updateView();
    }

    private void initView() {
        addClassName("categories-list");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addSearchBar() {
        Div viewToolbar = new Div();
        viewToolbar.addClassName("view-toolbar");

        searchField.setPrefixComponent(new Icon("lumo", "search"));
        searchField.addClassName("view-toolbar__search-field");
        searchField.addValueChangeListener(e -> updateView());
        searchField.setValueChangeMode(ValueChangeMode.EAGER);

        Button newButton = new Button("Nova categoria", new Icon("lumo", "plus"));
        newButton.getElement().setAttribute("theme", "primary");
        newButton.addClassName("view-toolbar__button");
        newButton.addClickListener(e -> form.open(new CategoriaModel(),
                AbstractEditorDialog.Operation.ADD));

        viewToolbar.add(searchField, newButton);
        add(viewToolbar);
    }

    private void addContent() {
        VerticalLayout container = new VerticalLayout();
        container.setClassName("view-container");
        container.setAlignItems(Alignment.STRETCH);

        grid.addColumn(CategoriaModel::getName).setHeader("Nome").setWidth("8em").setResizable(true);
        grid.addColumn(this::getReviewCount).setHeader("Livros").setWidth("6em");
        grid.addColumn(new ComponentRenderer<>(this::createEditButton))
                .setFlexGrow(0);
        grid.setSelectionMode(SelectionMode.NONE);

        container.add(header, grid);
        add(container);
    }

    private Button createEditButton(CategoriaModel category) {
        Button edit = new Button("Editar", event -> form.open(category,
                AbstractEditorDialog.Operation.EDIT));
        edit.setIcon(new Icon("lumo", "edit"));
        edit.addClassName("livro__edit");
        edit.getElement().setAttribute("theme", "tertiary");
        return edit;
    }

    private String getReviewCount(CategoriaModel category) {
        List<LivroModel> livrosInCategory = LivroService.getInstance()
                .findReviews(category.getName());
        int sum = livrosInCategory.stream().mapToInt(LivroModel::getCount).sum();
        return Integer.toString(sum);
    }

    private void updateView() {
        List<CategoriaModel> categories = CategoriaService.getInstance()
                .findCategories(searchField.getValue());
        grid.setItems(categories);

        if (searchField.getValue().length() > 0) {
            header.setText("Procurar por “"+ searchField.getValue() +"”");
        } else {
            header.setText("Categorias");
        }
    }

    private void saveCategory(CategoriaModel category,
            AbstractEditorDialog.Operation operation) {
        CategoriaService.getInstance().saveCategory(category);

        Notification.show(
                "Categoria " + operation.getNameInText() + " com sucesso.", 3000, Position.BOTTOM_START);
        updateView();
    }

    private void deleteCategory(CategoriaModel category) {
        List<LivroModel> livrosInCategory = LivroService.getInstance()
                .findReviews(category.getName());

        livrosInCategory.forEach(livro -> {
            livro.setCategory(CategoriaService.getInstance()
                    .findCategoryOrThrow("NaoExistente"));
            LivroService.getInstance().saveReview(livro);
        });
        CategoriaService.getInstance().deleteCategory(category);

        Notification.show("Categoria deletada com sucesso.", 3000, Position.BOTTOM_START);
        updateView();
    }
}
