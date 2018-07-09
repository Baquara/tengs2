package br.dcc.ufba.mata63.balaiolivros.ui.views.reviewslist;

import br.dcc.ufba.mata63.balaiolivros.backend.Review;
import com.vaadin.flow.templatemodel.Encode;
import com.vaadin.flow.templatemodel.TemplateModel;
import br.dcc.ufba.mata63.balaiolivros.ui.encoders.LocalDateToStringEncoder;
import br.dcc.ufba.mata63.balaiolivros.ui.encoders.LongToStringEncoder;
import java.util.List;

public interface ReviewsModel extends TemplateModel {
    @Encode(value = LongToStringEncoder.class, path = "id")
    @Encode(value = LocalDateToStringEncoder.class, path = "date")
    @Encode(value = LongToStringEncoder.class, path = "category.id")
    void setReviews(List<Review> reviews);
}