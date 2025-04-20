package com.ruoyi.web.controller.master;

import java.util.List;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.pda.domain.DishMaterial;
import com.ruoyi.pda.domain.Dishes;
import com.ruoyi.pda.domain.Ingredient;
import com.ruoyi.web.service.DishService;

@RestController
public class MasterDishController extends BaseController {
    @Autowired
    private DishService dishService;
    @GetMapping("/master/dishes/pageList")
    public TableDataInfo getDishesList(Dishes dishes) {
        startPage();
        List<Dishes> lists = dishService.getAll(dishes);
        return getDataTable(lists);
    }
    @PostMapping("/master/dishes/updateStatus")
    public AjaxResult updateStatus(@RequestBody Dishes dishes) {
        dishService.updateState(dishes);
        return AjaxResult.success();
    }

    @GetMapping("/master/dishes/getDishesMaterialList")
    public AjaxResult getDishesMaterialList(@RequestParam String id) {
        List<DishMaterial> list = dishService.getMaterialList(id);
        return AjaxResult.success(list);
    }
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/master/dishes/updateDish")
    public AjaxResult updateDish(@RequestBody Dishes dishes) {
        dishService.updateDish(dishes);
        return AjaxResult.success();
    }

    /**
     * 食材条件查询
     * @param name
     * @return
     */
    @GetMapping("/master/ingredients/pageList")
    public TableDataInfo getIngredientsList(String name) {
        startPage();
        List<Ingredient> list = dishService.getIngredientsList(name);
        return getDataTable(list);
    }

    /**
     * 食材下拉框数据源
     * @return
     */
    @GetMapping("master/ingredients/list")
    public AjaxResult getIngredientsList( ) {
        AjaxResult ajaxResult = AjaxResult.success();
        List<Ingredient> list = dishService.getIngredientsList("");
        ajaxResult.put("rows", list);
        return ajaxResult;
    }

    /**
     * 新增食材
     * @param ingredient
     * @return
     */
    @PostMapping("/master/ingredients/add")
    public AjaxResult addIngredient(@RequestBody Ingredient ingredient) {
        AjaxResult ajaxResult = AjaxResult.success();
        dishService.addIngredient(ingredient);
        return ajaxResult;
    }

    /**
     * 修改食材
     * @param ingredient
     * @return
     */
    @PostMapping("/master/ingredients/update")
    public AjaxResult updateIngredient(@RequestBody Ingredient ingredient) {
        AjaxResult ajaxResult = AjaxResult.success();
        dishService.updateIngredient(ingredient);
        return ajaxResult;
    }

    /**
     * 删除食材
     * @param Ingredient
     * @return
     */
    @PostMapping("/master/ingredients/delete")
    public AjaxResult deleteDish(@RequestBody Ingredient Ingredient) {
        AjaxResult ajaxResult = AjaxResult.success();
        dishService.deleteIngredient(Ingredient);
        return ajaxResult;
    }
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/master/dishes/addDish")
    public AjaxResult addDish(@RequestBody Dishes dishes) {
        AjaxResult ajaxResult = AjaxResult.success();
        dishes.setStatus("0");
        dishService.insertDish(dishes);
        return ajaxResult;
    }

    
}
