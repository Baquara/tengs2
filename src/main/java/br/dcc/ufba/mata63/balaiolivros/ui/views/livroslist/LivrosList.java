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

import java.util.List;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.ModelItem;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import br.dcc.ufba.mata63.balaiolivros.backend.models.LivroModel;
import br.dcc.ufba.mata63.balaiolivros.backend.controllers.LivroService;
import br.dcc.ufba.mata63.balaiolivros.ui.MainLayout;
import br.dcc.ufba.mata63.balaiolivros.ui.common.AbstractEditorDialog;
import br.dcc.ufba.mata63.balaiolivros.ui.common.AbstractShowDialog;
import br.dcc.ufba.mata63.balaiolivros.backend.models.LivroViewModel;

/**
 * Displays the list of available categories, with a search filter as well as
 * buttons to add a new category or edit existing ones.
 *
 * Implemented using a simple template.
 */
@Route(value = "", layout = MainLayout.class)
@PageTitle("Balaio de Livro - Lista de Livros")
@Tag("livros-list")
@HtmlImport("frontend://src/views/livroslist/livros-list.html")
public class LivrosList extends PolymerTemplate<LivroViewModel> {

    @Id("search")
    private TextField search;
    @Id("newReview")
    private Button addReview;
    @Id("header")
    private H2 header;

    private final LivroEditorDialog livroEditForm = new LivroEditorDialog(
            this::saveUpdate, this::deleteUpdate);
    private final LivroShowDialog livroShowForm = new LivroShowDialog();

    public LivrosList() {
        search.setPlaceholder("Buscar livros");
        search.addValueChangeListener(e -> updateList());
        search.setValueChangeMode(ValueChangeMode.EAGER);

        addReview.addClickListener(e -> openForm(new LivroModel(),
                AbstractEditorDialog.Operation.ADD));

        updateList();

    }

    public void saveUpdate(LivroModel livro,
            AbstractEditorDialog.Operation operation) {
        LivroService.getInstance().saveReview(livro);
        updateList();
        Notification.show(
                "Livro " + operation.getNameInText() + " com sucesso",
                3000, Position.BOTTOM_START);
    }

    public void deleteUpdate(LivroModel livro) {
        LivroService.getInstance().deleteReview(livro);
        updateList();
        Notification.show("Livro deletado com sucesso.", 3000,
                Position.BOTTOM_START);
    }

    private void updateList() {
        List<LivroModel> livros = LivroService.getInstance()
                .findReviews(search.getValue());
        if (search.isEmpty()) {
            header.setText("Livros");
            header.add(new Span("Total: " + livros.size()));
        } else {
            header.setText("Procurar por “" + search.getValue() + "”");
            if (!livros.isEmpty()) {
                header.add(new Span(livros.size() + " resultados"));
            }
        }
        getModel().setReviews(livros);
    }

    @EventHandler
    private void edit(@ModelItem LivroModel livro) {
        openForm(livro, AbstractEditorDialog.Operation.EDIT);
    }

    @EventHandler
    private void show(@ModelItem LivroModel review) {
        openForm2(review, AbstractShowDialog.Operation.SHOW);
    }

    private void openForm(LivroModel livro, AbstractEditorDialog.Operation operation) {
        // Add the form lazily as the UI is not yet initialized when
        // this view is constructed
        if (livroEditForm.getElement().getParent() == null) {
            getUI().ifPresent(ui -> ui.add(livroEditForm));
        }
        livroEditForm.open(livro, operation);
    }

    private void openForm2(LivroModel livro, AbstractShowDialog.Operation operation) {
        // Add the form lazily as the UI is not yet initialized when
        // this view is constructed
        if (livroShowForm.getElement().getParent() == null) {
            getUI().ifPresent(ui -> ui.add(livroShowForm));
        }
        livroShowForm.open(livro, operation);
    }

}
