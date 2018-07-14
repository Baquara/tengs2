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
package br.dcc.ufba.mata63.balaiolivros.ui.views.Perfil;

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
import br.dcc.ufba.mata63.balaiolivros.ui.views.categoriaslist.CategoriaEditorDialog;

/**
 * Displays the list of available categories, with a search filter as well as
 * buttons to add a new category or edit existing ones.
 */
@Route(value = "perfil", layout = MainLayout.class)
@PageTitle("Balaio de Livros - Perfil")
public class Perfil extends VerticalLayout {

   
    private final H2 header = new H2("Perfil");
    private final Grid<CategoriaModel> grid = new Grid<>();

    public Perfil() {
        initView();
        addContent();
    }

    private void initView() {
        addClassName("categories-list");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }



    private void addContent() {
        VerticalLayout container = new VerticalLayout();
        container.setClassName("view-container");
        container.setAlignItems(Alignment.STRETCH);

        grid.addColumn(CategoriaModel::getName).setHeader("Nome").setWidth("8em").setResizable(true);
        grid.addColumn(this::getReviewCount).setHeader("Livros").setWidth("6em");
        grid.setSelectionMode(SelectionMode.NONE);

        container.add(header, grid);
        add(container);
    }


    private String getReviewCount(CategoriaModel category) {
        List<LivroModel> livrosInCategory = LivroService.getInstance()
                .findReviews(category.getName());
        int sum = livrosInCategory.stream().mapToInt(LivroModel::getCount).sum();
        return Integer.toString(sum);
    }




}
