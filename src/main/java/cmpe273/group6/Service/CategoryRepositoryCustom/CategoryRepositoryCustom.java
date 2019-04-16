package cmpe273.group6.Service.CategoryRepositoryCustom;

import cmpe273.group6.Entity.Category;

import java.util.List;

public interface CategoryRepositoryCustom {

    List<Category> query(CategoryDynamicQuery categoryDynamicQuery);
}
