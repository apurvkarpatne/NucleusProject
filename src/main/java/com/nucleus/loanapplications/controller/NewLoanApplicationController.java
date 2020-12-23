package com.nucleus.loanapplications.controller;

import com.nucleus.customer.model.Address;
import com.nucleus.customer.model.Customer;
import com.nucleus.customer.service.AddressService;
import com.nucleus.customer.service.NewCustomerService;
import com.nucleus.loanapplications.model.LoanApplications;
import com.nucleus.loanapplications.service.NewLoanApplicationService;
import com.nucleus.login.logindetails.LoginDetailsImpl;
import com.nucleus.payment.service.DateEditor;
import com.nucleus.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class NewLoanApplicationController {



    @Autowired
    NewLoanApplicationService newLoanApplicationService;

    @Autowired
    NewCustomerService newCustomerService;

    @Autowired
    AddressService addressService;

    @Autowired
    LoginDetailsImpl loginDetails;


    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(LocalDate.class , new DateEditor());
    }

    @GetMapping(value = "/newLoanApplication")
    public ModelAndView addNewLoanApplication(){
        ModelAndView modelAndView= new ModelAndView("views/loanapplication/loanInformation");
        LoanApplications loanApplications = new LoanApplications();



        modelAndView.addObject("loanApplications",loanApplications);
        return modelAndView;
    }

    @PostMapping(value = "/newLoanApplication")
    public ModelAndView addCustomer(@Valid @ModelAttribute LoanApplications loanApplications , HttpServletRequest request){
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        Address address = (Address) session.getAttribute("address");


        Product product = new Product();
        product.setProductCode("P101");
        product.setProductName("homeLoan");
        product.setProductType("property");

        loanApplications.setCustomerCode(customer);
        List<LoanApplications> loanApplications1 = new ArrayList<>();
        loanApplications1.add(loanApplications);
        customer.setLoanApplications(loanApplications1);

        loanApplications.setStatus("Pending");
        loanApplications.setCreateDate(LocalDate.now());
        loanApplications.setCreatedBy(loginDetails.getUserName());

     /*   loanApplications.setProductCode(product);*/

       boolean a =  newCustomerService.createNewCustomer(customer);
       if(a){
           customer.id++;
       }
        boolean b =addressService.insertAddress(address);
        boolean c = newLoanApplicationService.addLoanApplication(loanApplications);


        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("a" ,a);
        modelAndView.addObject("b",b);
        modelAndView.addObject("c",c);

        modelAndView.setViewName("redirect:/loanApplication");
        return modelAndView;
    }
}
