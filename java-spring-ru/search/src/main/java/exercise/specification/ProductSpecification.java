package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

// BEGIN
@Component
public class ProductSpecification {
    public Specification<Product> build(ProductParamsDTO params) {
        return withTitleCont(params.getTitleCont())
                .and(withCategoryId(params.getCategoryId()))
                .and(withPriceGt(params.getPriceGt()))
                .and(withPriceLt(params.getPriceLt()))
                .and(withRatingGt(params.getRatingGt()));
    }

    private Specification<Product> withTitleCont(String titleCont) {
        return (root, query, criteriaBuilder) -> titleCont == null ? criteriaBuilder.conjunction() :
                criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + titleCont.toLowerCase() + "%");
    }

    private Specification<Product> withCategoryId(Long categoryId) {
        return (root, query, criteriaBuilder) -> categoryId == null ? criteriaBuilder.conjunction() :
                criteriaBuilder.equal(root.get("category").get("id"), categoryId);
    }

    private Specification<Product> withPriceLt(Integer priceLt) {
        return (root, query, criteriaBuilder) -> priceLt == null ? criteriaBuilder.conjunction() :
                criteriaBuilder.lessThan(root.get("price"), priceLt);
    }

    private Specification<Product> withPriceGt(Integer priceGt) {
        return (root, query, criteriaBuilder) -> priceGt == null ? criteriaBuilder.conjunction() :
                criteriaBuilder.greaterThan(root.get("price"), priceGt);
    }

    private Specification<Product> withRatingGt(Double ratingGt) {
        return (root, query, criteriaBuilder) -> ratingGt == null ? criteriaBuilder.conjunction() :
                criteriaBuilder.greaterThan(root.get("rating"), ratingGt);
    }
}
//Создайте спецификацию для фильтрации товара. Реализуйте возможность фильтрации товара по следующим условиям:
//        Товар находится в определенной категории
//        Цена товара выше указанной
//        Цена товара ниже указанной
//        Рейтинг товара выше указанного
//        Название товара содержит указанную подстроку без учета регистра
//        Все эти параметры необязательны.
//        При создании спецификации опирайтесь на названия полей сущности и параметров в ProductParamsDTO
// END
