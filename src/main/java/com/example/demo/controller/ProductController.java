package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.ProductRepository.ProductRepository;
import com.example.demo.model.Product;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductRepository productrepo;
	
	@RequestMapping("/")
	public String getProducts(Model model)
	{
		model.addAttribute("listofproducts",productrepo.findAll() );
		return "ProductPage";
	}
	
	@RequestMapping("/new")
	public String CreatePage(Model model)
	{
		Product product = new Product();
		model.addAttribute("newproduct", product);
		return "CreateProduct";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String SaveProduct(@ModelAttribute("product")Product product)
	{
		productrepo.save(product);
		return "redirect:/products/";
	}
	
	@RequestMapping("/edit/{id}")
	public String ShoeEditProduct(Model model,@PathVariable(name = "id") long id)
	{
		model.addAttribute("productupdate", productrepo.findById(id));
		return "UpdateProduct";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") Long id) {
		productrepo.deleteById(id);
		return "redirect:/products/";		
	}
}
