package com.ccsw.tutorial.prestamo;

import java.time.LocalDate;

import com.ccsw.tutorial.common.criteria.SearchCriteria;
import com.ccsw.tutorial.prestamo.model.Prestamo;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.*;

/**
 * @author Denny Montenegro
 *
 */


public class PrestamoSpecification implements Specification<Prestamo> {

    private static final Long serialVerisonUID = 1L;
    private final SearchCriteria criteria;

    public PrestamoSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Prestamo> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase(":") && criteria.getValue() != null) {
            Path<String> path = getPath(root);
            if (path.getJavaType() == String.class) {
                return builder.like(path, "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(path, criteria.getValue());
            }
        }
        if (criteria.getOperation().equalsIgnoreCase("between") && criteria.getValue() != null) {
            LocalDate date = (LocalDate) criteria.getValue();

            Path<LocalDate> date_iniPath = root.get("date_ini");
            Path<LocalDate> date_endPath = root.get("date_end");

            return builder.and(builder.lessThanOrEqualTo(date_iniPath, date), builder.greaterThanOrEqualTo(date_endPath, date));

        }
        return null;
    }

    private Path<String> getPath(Root<Prestamo> root) {
        String key = criteria.getKey();
        String[] split = key.split("[.]", 0);

        Path<String> expression = root.get(split[0]);
        for (int i = 1; i < split.length; i++) {
            expression = expression.get(split[i]);
        }

        return expression;
    }
    
}
