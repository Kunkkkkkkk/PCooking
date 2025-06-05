package com.ruoyi.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.ruoyi.pda.domain.DishMaterial;
import com.ruoyi.pda.domain.Dishes;
import com.ruoyi.pda.domain.Ingredient;

@Mapper
public interface DishMapper {
    public List<Dishes> getAll(Dishes dishes);
    public List<DishMaterial> getMaterialsByDishId(Integer dishId);
    @Update("update master_dishes set status = #{status},modifyTime=NOW() where dish_id = #{dishId} ")
    public void updateState(Dishes dishes);
    public List<DishMaterial> getMaterial(String id);
    
    void updateDish(Dishes dishes);
    void deleteDish(int dishId);
    void deleteMaterial(int dishId);
    void insertDish(Dishes dishes);
    List<Ingredient> getIngredientsList(Ingredient ingredient);
    void insertMaterial(@Param("dishId") int dishId,@Param("dishMaterials") List<DishMaterial> dishMaterials);
    @Insert("insert into master_ingredients(ingredient_name,type) values (#{name},#{type})")
    void insertIngredient(Ingredient ingredient);
    @Update("update master_ingredients set ingredient_name=#{name},type=#{type} where ingredient_id=#{ingredientId}")
    void updateIngredient(Ingredient ingredient);
    @Delete("delete from master_ingredients where ingredient_id=#{ingredientId}")
    void deleteIngredient(Ingredient ingredient);

    int isCheckUpdate(Dishes dishes);
}
