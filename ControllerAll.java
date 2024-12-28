package com.tap5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tap5.dao.DaoImpI;
import com.tap5.module.EntityObj;
import com.tap5.service.ServiceInterfaceImpI;

@Controller
public class ControllerAll {

	
	ServiceInterfaceImpI serviceInterfaceImpI;
	
	@Autowired
	public ControllerAll(ServiceInterfaceImpI serviceInterfaceImpI) {
		this.serviceInterfaceImpI = serviceInterfaceImpI;
	}

	public ControllerAll() {

		System.out.println("ioc is created perfectly ........");
	}

	@RequestMapping(value = "/handlerFirstRequest")
	public String FirstRequestHandler(@RequestParam int id , @RequestParam String dept, @RequestParam String email,
			@RequestParam String name, @RequestParam Double salary, Model model) {

		System.out.println("inside first handler method is called()");
		System.out.println("id is:" + id);
		System.out.println("dept is:" + dept);
		System.out.println("email is:" + email);
		System.out.println("name is:" + name);
		System.out.println("salary is:" + salary);
		boolean isvalid = serviceInterfaceImpI.SaveValidation(id ,dept, email, name, salary);
		if (isvalid) {
			model.addAttribute("message", "thankyou your registraion is confirmed");
		} else {
			model.addAttribute("message", "sorry!! your registraion is not  confirmed");
		}
		return "/index.jsp";
	}
	
	@RequestMapping(value= "/checkEntityExistenceById")
	public String checkEntityExistenceById(@RequestParam int id , Model model) {
		
		System.out.println("entity id" +":" + id);
		
		boolean isvalid=serviceInterfaceImpI.theEntityPresent(id);
		
		if(isvalid) {
			
			EntityObj entity=serviceInterfaceImpI.isEntityPresent(id);
			
			if(entity !=null) {
			
			model.addAttribute("ID" , entity.getId()  );
			model.addAttribute("dept", entity.getDept());
			model.addAttribute("email", entity.getEmail());
			model.addAttribute("name", entity.getName());
			model.addAttribute("salary", entity.getSalary());	
			}
			else {
				model.addAttribute( "message" ,"id does not exist in the data base.....");
			}
		}
		else {
			model.addAttribute("message", "Invalid ID given. Check once and try again...");

 		}
		return "/index.jsp";
	}
	
}
