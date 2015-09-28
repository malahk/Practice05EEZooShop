package com.kirill.app.controllers;

import com.kirill.app.dao.FoodDAO;
import com.kirill.app.dao.FoodDAOImpl;
import com.kirill.app.models.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Admin
 *
 * @author Admin
 * @since 24.09.2015
 */
@Controller
public class FoodController {

    @Autowired
    private FoodDAO foodDAO;

    @RequestMapping(value = "create_food", method = RequestMethod.GET)
    public ModelAndView foodShow() {

        return new ModelAndView("foodCreate");
    }

    @RequestMapping(value = "create_food", method = RequestMethod.POST)
    public String foodCreate(HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        FoodDAOImpl foodImpl = new FoodDAOImpl();
        Food food = new Food();
        String foodName = request.getParameter("foodName");
        food.setItemName(foodName);
        foodImpl.create(food);
        redirectAttributes.addFlashAttribute("message", String.format("New food %s successfully created!", food.getItemName()));

        return "redirect:/food_list";
    }

    @RequestMapping(value = "food_list", method = RequestMethod.GET)
    public ModelAndView foodList() {
        List<Food> feed = this.foodDAO.getAll();
        ModelAndView mav = new ModelAndView("foodList");
        mav.addObject(feed);

        return mav;
    }

    @RequestMapping(value = "update_food", method = RequestMethod.GET)
    public ModelAndView updateFood (@RequestParam(value="id") Integer id) {
        ModelAndView mav = new ModelAndView("foodUpdate");
        mav.addObject(this.foodDAO.getFood(id));

        return mav;
    }

    @RequestMapping(value = "update_food", method = RequestMethod.POST)
    public String updateRole (HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        Food food = new Food();
        String id =  request.getParameter("id");
        String itemName = request.getParameter("itemName");
        food.setId(Integer.valueOf(id));
        food.setItemName(itemName);
        FoodDAOImpl foodImpl = new FoodDAOImpl();
        foodImpl.update(food);
        redirectAttributes.addFlashAttribute("message", "Item is successfully updated!");

        return "redirect:/food_list";
    }

    @RequestMapping(value = "delete_food", method = RequestMethod.GET)
    public String deleteFood(HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        String id = request.getParameter("id");
        FoodDAOImpl foodImpl = new FoodDAOImpl();
        Food food = foodImpl.getFood(Integer.valueOf(id));
        foodImpl.delete(food);
        redirectAttributes.addFlashAttribute("message", String.format("Item %s successfully deleted!", food.getItemName()));

        return "redirect:/food_list";
    }

}
