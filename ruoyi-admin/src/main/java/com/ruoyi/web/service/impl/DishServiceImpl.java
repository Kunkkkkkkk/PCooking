package com.ruoyi.web.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
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
    public AjaxResult updateState(Dishes dishes) {
        if(dishMapper.isCheckUpdate(dishes)>0){
        dishMapper.updateState(dishes);
        return AjaxResult.success();
        }else {
        return AjaxResult.error("数据已被他人修改，请刷新重试");
        }
    }

    @Override
    public List<DishMaterial> getMaterialList(String id) {
        return dishMapper.getMaterial(id);
    }
    @Transactional
    @Override
    public AjaxResult updateDish(Dishes dishes) {
        if(dishMapper.isCheckUpdate(dishes)>0) {
            dishMapper.updateDish(dishes);
            dishMapper.deleteMaterial(dishes.getDishId());
            dishMapper.insertMaterial(dishes.getDishId(), dishes.getDishMaterials());
            return AjaxResult.success();
        }else {
            return AjaxResult.error("数据已被他人修改，请刷新重试");
        }
    }
    @Override
    public void insertDish(Dishes dishes) {
        dishMapper.insertDish(dishes);
        dishMapper.insertMaterial(dishes.getDishId(),dishes.getDishMaterials());
    }
   @Override
    public List<Ingredient> getIngredientsList(Ingredient query) {
        return dishMapper.getIngredientsList(query);
    }

    @Override
    public AjaxResult addIngredient(Ingredient ingredient) {
        if(dishMapper.getIngredientsList(ingredient).size()>0){
            return AjaxResult.error("该食材已存在");
        }else {
            dishMapper.insertIngredient(ingredient);
            return AjaxResult.success();
        }
    }

    @Override
    public AjaxResult updateIngredient(Ingredient ingredient) {
        dishMapper.updateIngredient(ingredient);
        return AjaxResult.success();
    }

    @Override
    public void deleteIngredient(Ingredient ingredient) {
        dishMapper.deleteIngredient(ingredient);
    }


}
