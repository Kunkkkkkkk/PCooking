package com.ruoyi.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.pda.domain.DishMaterial;
import com.ruoyi.pda.domain.Dishes;
import com.ruoyi.pda.domain.Ingredient;
import com.ruoyi.web.mapper.DishMapper;
import com.ruoyi.web.service.DishService;

@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;
    @Override
    public List<Dishes> getAll(Dishes dishes) {
        return dishMapper.getAll(dishes);
    }

    @Override
    public void updateState(Dishes dishes) {
        dishMapper.updateState(dishes);
    }

    @Override
    public List<DishMaterial> getMaterialList(String id) {
        return dishMapper.getMaterial(id);
    }
    @Transactional
    @Override
    public void updateDish(Dishes dishes) {
        dishMapper.updateDish(dishes);
        dishMapper.deleteMaterial(dishes.getDishId());
        dishMapper.insertMaterial(dishes.getDishId(),dishes.getDishMaterials());
    }
    @Override
    public void insertDish(Dishes dishes) {
        dishMapper.insertDish(dishes);
        dishMapper.insertMaterial(dishes.getDishId(),dishes.getDishMaterials());
    }
   @Override
    public List<Ingredient> getIngredientsList(String name) {
        return dishMapper.getIngredientsList(name);
    }

    @Override
    public void addIngredient(Ingredient ingredient) {
        dishMapper.insertIngredient(ingredient);
    }

    @Override
    public void updateIngredient(Ingredient ingredient) {
        dishMapper.updateIngredient(ingredient);
    }

    @Override
    public void deleteIngredient(Ingredient ingredient) {
        dishMapper.deleteIngredient(ingredient);
    }
}
