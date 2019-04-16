package cmpe273.group6.DAL;

import cmpe273.group6.Entity.Category;

public interface CategoryDAL {
    Category findCategoryByName(String name);
}
