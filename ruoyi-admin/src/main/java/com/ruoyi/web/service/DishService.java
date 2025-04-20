package com.ruoyi.web.service;

import java.util.List;

import com.ruoyi.pda.domain.DishMaterial;
import com.ruoyi.pda.domain.Dishes;
import com.ruoyi.pda.domain.Ingredient;

public interface DishService {
    List<Dishes> getAll(Dishes dishes);
    void updateState(Dishes dishes);
    List<DishMaterial> getMaterialList(String id);
    void insertDish(Dishes dishes);
    void updateDish(Dishes dishes);
    List<Ingredient> getIngredientsList(String name);
    void addIngredient(Ingredient ingredient);
    void updateIngredient(Ingredient ingredient);
    void deleteIngredient(Ingredient ingredient);

}
