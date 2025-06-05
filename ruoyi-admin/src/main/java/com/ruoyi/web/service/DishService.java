package com.ruoyi.web.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.pda.domain.DishMaterial;
import com.ruoyi.pda.domain.Dishes;
import com.ruoyi.pda.domain.Ingredient;

public interface DishService {
    List<Dishes> getAll(Dishes dishes);
    AjaxResult updateState(Dishes dishes);
    List<DishMaterial> getMaterialList(String id);
    void insertDish(Dishes dishes);
    AjaxResult updateDish(Dishes dishes);
    List<Ingredient> getIngredientsList(Ingredient query);
    AjaxResult addIngredient(Ingredient ingredient);
    AjaxResult updateIngredient(Ingredient ingredient);
    void deleteIngredient(Ingredient ingredient);

}
