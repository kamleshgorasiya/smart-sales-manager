package com.smart.sales.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.smart.sales.manager.entity.model.Product;
import com.smart.sales.manager.response.model.ApiResponse;
import com.smart.sales.manager.service.ProductService;

import java.util.List;
import java.util.Locale;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private MessageSource messageSource;
  
    @PostMapping(value="/product/add-product")
    public ApiResponse<Product> addNewProduct(@RequestHeader(name="Accept-language",required=false) Locale locale, @RequestBody Product product, Authentication authentication){
    	String username= authentication.getName();
    	String[] args= {"Product details"};
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.created",args , locale),productService.save(product,username));
        
    }
   
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value="/product/{page}/{order}/{field}")
    public ApiResponse<List<Product>> getAllProduct(@RequestHeader(name="Accept-language",required=false) Locale locale, @PathVariable int page, @PathVariable String order, @PathVariable String field){
    	String[] args= {"Products"};
    	return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.list.fetched", args, locale),productService.findAll(page, order, field));
    }
    
   
    
    @PutMapping("/product/me")
    public ApiResponse<Product> updateMyProduct(@RequestHeader(name="Accept-language",required=false) Locale locale,@RequestBody Product product,Authentication authentication) {
    	String username=authentication.getName();
    	String[] args= {"Product details"};
   		return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.updated", args, locale),productService.update(product,username, locale));
    	
    }
    
    @GetMapping("/product/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Product> getOne(@RequestHeader(name="Accept-language",required=false) Locale locale, @PathVariable long id){
    	Product product=productService.findById(id);
    	String[] args= {"Product details"};
    	return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.fetched", args, locale),product);
    }
    @GetMapping("/business/{id}/product")
    public ApiResponse<Product> getMyProductByBusiness(@RequestHeader(name="Accept-language",required=false) Locale locale,Authentication authentication, @PathVariable long id) {
    	String username=authentication.getName();
    	String[] args= {"Product details"};
   		return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.fetched", args, locale),productService.findByBusinessId(id,username));    	
    }
    
    @PutMapping("/product")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Product> update(@RequestHeader(name="Accept-language",required=false) Locale locale, @RequestBody Product product) {
    	
    	String[] args= {"Product details"};
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.updated", args, locale),productService.update(product,locale));
    }

    @DeleteMapping("/product/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Void> delete(@RequestHeader(name="Accept-language",required=false) Locale locale, @PathVariable int id) {
    	String[] args= {"Specified Product"};
    	productService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.deleted", args, locale), null);
    }

    @PutMapping("/product/{id}")
    @PreAuthorize("hasRole('USER')")
    public ApiResponse<Void> deleteProduct(@RequestHeader(name="Accept-language",required=false) Locale locale, @PathVariable int id, Authentication authentication) {
    	authentication.getName();
    	String[] args= {"Specified Product"};
    	productService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.deleted", args, locale), null);
    }
}
