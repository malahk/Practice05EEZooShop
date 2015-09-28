package com.kirill.app.controllers;

import com.kirill.app.dao.AccessoriesDAO;
import com.kirill.app.dao.AccessoriesDAOImpl;
import com.kirill.app.dao.FoodDAO;
import com.kirill.app.dao.FoodDAOImpl;
import com.kirill.app.models.Accessories;
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
public class AccessoriesController {

    @Autowired
    private AccessoriesDAO accsDAO;

    @RequestMapping(value = "create_accs", method = RequestMethod.GET)
    public ModelAndView accsShow() {

        return new ModelAndView("accessorieCreate");
    }

    @RequestMapping(value = "create_accs", method = RequestMethod.POST)
    public String accsCreate(HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        AccessoriesDAOImpl accsImpl = new AccessoriesDAOImpl();
        Accessories accs = new Accessories();
        String accsName = request.getParameter("accsName");
        accs.setAccsName(accsName);
        accsImpl.create(accs);
        redirectAttributes.addFlashAttribute("message", String.format("New food %s successfully created!", accs.getAccsName()));

        return "redirect:/accs_list";
    }

    @RequestMapping(value = "accs_list", method = RequestMethod.GET)
    public ModelAndView accsList() {
        List<Accessories> accs = this.accsDAO.getAll();
        ModelAndView mav = new ModelAndView("accessoriesList");
        mav.addObject(accs);

        return mav;
    }

    @RequestMapping(value = "update_accs", method = RequestMethod.GET)
    public ModelAndView updateAccs (@RequestParam(value="id") Integer id) {
        ModelAndView mav = new ModelAndView("accessorieUpdate");
        mav.addObject(this.accsDAO.getAccs(id));

        return mav;
    }

    @RequestMapping(value = "update_accs", method = RequestMethod.POST)
    public String updateAccs (HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        Accessories accs = new Accessories();
        String id = request.getParameter("id");
        String itemName = request.getParameter("itemName");
        accs.setId(Integer.valueOf(id));
        accs.setAccsName(itemName);
        AccessoriesDAOImpl accsImpl = new AccessoriesDAOImpl();
        accsImpl.update(accs);
        redirectAttributes.addFlashAttribute("message", "Item is successfully updated!");

        return "redirect:/accs_list";
    }

    @RequestMapping(value = "delete_accs", method = RequestMethod.GET)
    public String deleteFood(HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        String id = request.getParameter("id");
        AccessoriesDAOImpl accsImpl = new AccessoriesDAOImpl();
        Accessories accs = accsImpl.getAccs(Integer.valueOf(id));
        accsImpl.delete(accs);
        redirectAttributes.addFlashAttribute("message", String.format("Item %s successfully deleted!", accs.getAccsName()));

        return "redirect:/accs_list";
    }

}
