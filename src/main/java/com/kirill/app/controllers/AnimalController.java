package com.kirill.app.controllers;

import com.kirill.app.dao.*;
import com.kirill.app.models.Accessories;
import com.kirill.app.models.Animals;
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
public class AnimalController {

    @Autowired
    private AnimalsDAO animalsDAO;

    @RequestMapping(value = "create_animal", method = RequestMethod.GET)
    public ModelAndView userShow() {

        return new ModelAndView("animalCreateForm");
    }

    @RequestMapping(value = "create_animal", method = RequestMethod.POST)
    public ModelAndView animalCreate(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("dataFormView");
        mav.addObject("name", request.getParameter("name"));
        mav.addObject("price", request.getParameter("price"));
        mav.addObject("food", request.getParameter("food"));
        mav.addObject("accessorie", request.getParameter("accessorie"));
        mav.addObject("type", request.getParameter("type"));

        return mav;
    }

    @RequestMapping(value = "animals_list", method = RequestMethod.GET)
    public ModelAndView animalList() {
        List<Animals> animals = this.animalsDAO.getAll();
        ModelAndView mav = new ModelAndView("animalList");
        mav.addObject(animals);

        return mav;
    }

    @RequestMapping(value = "update_animal", method = RequestMethod.GET)
    public ModelAndView updateUser(@RequestParam(value="id") Integer id) {
        ModelAndView mav = new ModelAndView("animalUpdateForm");
        mav.addObject(this.animalsDAO.getAnimal(id));

        return mav;
    }

    @RequestMapping(value = "update_animal", method = RequestMethod.POST)
    public String updateUser(HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        AnimalsDAO animalImpl = new AnimalsDAOImpl();
        Animals animal = extractAnimalFromRequest(request);
        animalImpl.update(animal);
        FoodDAO foodImpl = new FoodDAOImpl();
        Food food = extractFoodFromForm(request, animal);
        foodImpl.update(food);
        AccessoriesDAO accsImpl = new AccessoriesDAOImpl();
        Accessories accs = extractAccsFromForm(request,animal);
        accsImpl.update(accs);

        redirectAttributes.addFlashAttribute("message", String.format("Animal (%s) is successfully updated!", animal.getName()));

        return "redirect:/animal_list";
    }

    @RequestMapping(value = "delete_user", method = RequestMethod.GET)
    public String deleteUser(HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        String animalId = request.getParameter("id");
        AnimalsDAO animalsImpl = new AnimalsDAOImpl();
        String name = animalsImpl.getAnimal(Integer.valueOf(animalId)).getName();
        animalsImpl.delete(animalsImpl.getAnimal(Integer.valueOf(animalId)));
        redirectAttributes.addFlashAttribute("message", String.format("User %s %s successfully deleted!", name));

        return "redirect:/user_list";
    }

    private Animals extractAnimalFromRequest(HttpServletRequest request){
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String type = request.getParameter("type");

        Animals animal = new Animals();
        animal.setName(name);
        animal.setPrice(Integer.valueOf(price));
        animal.setType(type);

        return animal;
    }

    private Food extractFoodFromForm (HttpServletRequest request, Animals animal){
        String item = request.getParameter("item");
        Food food = new Food();
        food.setItemName(item);

        return food;
    }

    private Accessories extractAccsFromForm (HttpServletRequest request, Animals animal){
        String item = request.getParameter("item");
        Accessories accs = new Accessories();
        accs.setAccsName(item);;

        return accs;
    }
}
