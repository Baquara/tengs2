package br.dcc.ufba.mata63.balaiolivros.backend.models;

import com.vaadin.flow.templatemodel.Encode;
import com.vaadin.flow.templatemodel.TemplateModel;
import br.dcc.ufba.mata63.balaiolivros.ui.encoders.LocalDateToStringEncoder;
import br.dcc.ufba.mata63.balaiolivros.ui.encoders.LongToStringEncoder;
import java.util.List;

public interface LivroViewModel extends TemplateModel {
    @Encode(value = LongToStringEncoder.class, path = "id")
    @Encode(value = LocalDateToStringEncoder.class, path = "date")
    @Encode(value = LongToStringEncoder.class, path = "category.id")
    void setReviews(List<LivroModel> livros);
}