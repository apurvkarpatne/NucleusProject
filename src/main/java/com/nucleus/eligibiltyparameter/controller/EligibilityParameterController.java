package com.nucleus.eligibiltyparameter.controller;

import com.nucleus.eligibilitypolicy.model.EligibilityPolicy;
import com.nucleus.eligibiltyparameter.database.EligibilityParameterDAO;
import com.nucleus.eligibiltyparameter.model.EligibilityParameter;
import com.nucleus.eligibiltyparameter.service.EligibilityParameterService;
import com.nucleus.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/main")
public class EligibilityParameterController {

    @Autowired
    private EligibilityParameterService eligibilityParameterService;

    /**
     * Getting All eligibility parameters from database in maker and checker screens
     */
    @RequestMapping("/eligibilityparameter")
    public String getParameters(Model model) {
        System.out.println("kirtika");
        List<EligibilityParameter>list= eligibilityParameterService.getAll();
        model.addAttribute("parameters",list);
        System.out.println(list);
        return "views/eligibilityparameters/viewEligibilityParameters";
    }

    /**
     * Creating a new Eligibility Parameter in maker screen
     */
    @GetMapping("/createparameter")
    public String createParameter(Model model){
        EligibilityParameter eligibilityParameter = new EligibilityParameter();

        model.addAttribute("eligibilityParameter", eligibilityParameter);
        return "views/eligibilityparameters/createParameter";
    }

    /**
     * Saving a Parameter in database
     */
    @RequestMapping(value = "/insertparameter", params = "action1",method = RequestMethod.POST)
    public String saveParameter(@Valid @ModelAttribute("eligibilityParameter") EligibilityParameter eligibilityParameter, BindingResult br){
        if(br.hasErrors())
        {
            return "views/eligibilityparameters/createParameter";
        }
        else
        {
            eligibilityParameter.setCreatedBy("Kirtika");
            eligibilityParameter.setCreateDate(LocalDate.now());
            eligibilityParameter.setStatus("Inactive");
            eligibilityParameterService.insertParameter(eligibilityParameter);
            return "views/eligibilityparameters/eligibilityparametersuccess";
        }

    }

    /**
     * Saving parameter in database and requesting for approval by checker
     */
    @RequestMapping(value = "/insertparameter", params = "action2",method = RequestMethod.POST)
    public String saveAndRequestApproval(@Valid @ModelAttribute("eligibilityParameter")EligibilityParameter eligibilityParameter,BindingResult br){
        if(br.hasErrors())
        {
            return "views/eligibilityparameters/createParameter";
        }
        else
        {
            eligibilityParameter.setCreatedBy("Kirtika");
            eligibilityParameter.setCreateDate(LocalDate.now());
            eligibilityParameter.setStatus("Pending");
            eligibilityParameterService.insertParameterAndRequestApproval(eligibilityParameter);
            return "views/eligibilityparameters/eligibilityparametersuccess";
        }

    }

    /**
     * Editing an Eligibility Parameter and saving it into database
     */
    @RequestMapping(value = "/edit/editparameter", params = "action1",method = RequestMethod.POST)
    public String editParameter1(@Valid @ModelAttribute("eligibilityParameter1")EligibilityParameter eligibilityParameter,BindingResult br){

        if(br.hasErrors())
        {
            return "views/eligibilityparameters/editParameter";
        }
        else
        {
            eligibilityParameter.setModifiedBy("Kirtika");
            eligibilityParameter.setStatus("Inactive");
            boolean valid=eligibilityParameterService.editParameter(eligibilityParameter);

            if(valid==true)
            {
                System.out.println("true");
                return "views/eligibilityparameters/eligibilityparametersuccess";
            }

            else
            {
                System.out.println("false");
                return "views/eligibilityparameters/eligibilityparameterfailure";
            }
        }

    }

    /**
     * Editing an eligibility parameter and saving it into database and requesting a approval by checker
     */
    @RequestMapping(value = "/edit/editparameter", params = "action2",method = RequestMethod.POST)
    public String editParameter2(@Valid @ModelAttribute("eligibilityParameter1")EligibilityParameter eligibilityParameter,BindingResult br){
        if(br.hasErrors())
        {
            return "views/eligibilityparameters/editParameter";
        }
        else
        {
            eligibilityParameter.setModifiedBy("Kirtika");
            eligibilityParameter.setStatus("Pending");
            boolean valid=eligibilityParameterService.editParameter(eligibilityParameter);

            if(valid==true)
            {
                System.out.println("true");
                return "views/eligibilityparameters/eligibilityparametersuccess";
            }

            else
            {
                System.out.println("false");
                return "views/eligibilityparameters/eligibilityparameterfailure";
            }
        }

    }

    /**
     * Deleting a particular eligibility parameter from database
     */
    @RequestMapping("/delete/{parameterCode}")
    public String deleteParameter(@PathVariable("parameterCode") String parameterCode) {
        String pcode = eligibilityParameterService.deleteEligibilityParameter(parameterCode);
        return "redirect:/main/eligibilityparameter/";

    }

    /**
     * Edit parameter screen
     */
    @RequestMapping("/edit/{parameterCode}")
    public ModelAndView editParameter(@PathVariable("parameterCode") String parameterCode,Model model){

        ModelAndView modelAndView = new ModelAndView();
        EligibilityParameter eligibilityParameter = eligibilityParameterService.getOneEligibilityParameter(parameterCode);
        modelAndView.addObject("eligibilityParameter1", eligibilityParameter);
        modelAndView.setViewName("views/eligibilityparameters/editParameter");
        return modelAndView;


    }

    /**
     * Getting a single parameter from database for approval or rejection
     */
    @GetMapping(value = {"/get/{parameterCode}"})
    public ModelAndView showOneEligibilityParameter(@PathVariable("parameterCode") String parameterCode) {
        ModelAndView modelAndView = new ModelAndView();
        EligibilityParameter eligibilityParameter = eligibilityParameterService.getOneEligibilityParameter(parameterCode);
        modelAndView.addObject("eligibilityParameter2", eligibilityParameter);
        modelAndView.setViewName("views/eligibilityparameters/viewOneEligibilityParameter");
        return modelAndView;
    }

    /**
     * Updating status of a particular eligibility parameter as updated or rejected into database
     */
    @PostMapping(value = {"/updateStatus/{parameterCode}"})
    public String updateStatus(@PathVariable("parameterCode") String parameterCode, @RequestParam("action")String action, Model model) {
        String newStatus;
        if(action.equalsIgnoreCase("approve")) {
            newStatus = "Approved";
        } else if (action.equalsIgnoreCase("reject")) {
            newStatus = "Rejected";
        } else
            newStatus = "Pending";
        System.out.println(newStatus);
        boolean updateStatus = eligibilityParameterService.updateStatus(parameterCode, newStatus);
        return "redirect:/main/eligibilityparameter/";
    }

}
