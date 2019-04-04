package com.smart.sales.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smart.sales.manager.SSMApplication;
import com.smart.sales.manager.entity.model.PasswordResetToken;
import com.smart.sales.manager.entity.model.User;
import com.smart.sales.manager.repository.PasswordResetTokenRepository;
import com.smart.sales.manager.request.model.Mail;
import com.smart.sales.manager.request.model.PasswordResetDto;
import com.smart.sales.manager.request.model.UserDto;
import com.smart.sales.manager.response.model.ApiResponse;
import com.smart.sales.manager.service.EmailService;
import com.smart.sales.manager.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;
    
    @Autowired private PasswordResetTokenRepository tokenRepository;
    @Autowired private EmailService emailService;
    
    @Autowired private BCryptPasswordEncoder passwordEncoder;
  
    @PostMapping(value="/signup")
    public ApiResponse<User> signup(@RequestHeader(name="Accept-language",required=false) Locale locale, @RequestBody UserDto user){
            return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("user.saved", null, locale),userService.save(user));
    }
    @PostMapping(value="/signup-admin")
    public ApiResponse<User> signupAdmin(@RequestHeader(name="Accept-language",required=false) Locale locale, @RequestBody UserDto user){
            return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("user.saved", null, locale),userService.saveAdmin(user));
    }
    @PostMapping(value="/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<User> saveUser(@RequestHeader(name="Accept-language",required=false) Locale locale, @RequestBody UserDto user){
    
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("user.saved", null, locale),userService.saveUser(user));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value="/users/{page}/{order}/{field}")
    public ApiResponse<List<User>> listUser(@RequestHeader(name="Accept-language",required=false) Locale locale, @PathVariable int page, @PathVariable String order, @PathVariable String field){
    	return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("user.list.fetched", null, locale),userService.findAll(SSMApplication.getPageRequest(page, order, field)));
    }
    
    @GetMapping("/users/me")
    public ApiResponse<User> getyProfile(@RequestHeader(name="Accept-language",required=false) Locale locale, Authentication authentication){
    	String username= authentication.getName();
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("user.fetched", null, locale),userService.findOne(username));
    }
    
    @PutMapping("/users/update/me")
    public ApiResponse<User> updateMe(@RequestHeader(name="Accept-language",required=false) Locale locale,@RequestBody UserDto userDto,Authentication authentication) {
    	String username= authentication.getName();    	
        return new ApiResponse<User>(HttpStatus.OK.value(), messageSource.getMessage("user.profile.updated", null, locale),userService.updateMe(userDto,username));
    }
    
    @GetMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<User> getOne(@RequestHeader(name="Accept-language",required=false) Locale locale, @PathVariable long id){
    	User user=userService.findById(id);
    	/*SimpleBeanPropertyFilter beanPropertyFilter= SimpleBeanPropertyFilter.filterOutAllExcept("id","firstName","lastName", "age", "salary");
    	FilterProvider filterProvider= new SimpleFilterProvider().addFilter("findbyid",beanPropertyFilter);
    	MappingJacksonValue mapping = new MappingJacksonValue(user);
    	mapping.setFilters(filterProvider);*/
    	return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("user.fetched", null, locale),user);
    }

    @PutMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse update(@RequestHeader(name="Accept-language",required=false) Locale locale, @RequestBody UserDto userDto) {
        return new ApiResponse(HttpStatus.OK.value(), messageSource.getMessage("user.updated", null, locale),userService.update(userDto));
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Void> delete(@RequestHeader(name="Accept-language",required=false) Locale locale, @PathVariable int id) {
        
    	userService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("user.deleted", null, locale), null);
    }
    
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public @ResponseBody ApiResponse logout(HttpServletRequest request, HttpServletResponse response,Locale locale){
       Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
       if (auth != null){    
       String username = auth.getName();
       //Remove the recently used OTP from server. 
      // otpService.clearOTP(username);
       new SecurityContextLogoutHandler().logout(request, response, auth);
       }
       return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("logout.success", null, locale), null);
    }
    
  @PostMapping(path="/forgot-password",produces= {MediaType.APPLICATION_JSON_VALUE},consumes= {MediaType.APPLICATION_JSON_VALUE})
  public ApiResponse<String> sendResetPasswordLink(@RequestHeader(name="Accept-language",required=false) Locale locale, @RequestBody UserDto userDto,HttpServletRequest request){
  	User user = userService.findByEmail(userDto.getEmail());
  	String[] args= {userDto.getEmail()};
      if (user == null){        	
      	return new ApiResponse<String>(HttpStatus.NOT_FOUND.value(), messageSource.getMessage("user.notfound",args, locale),"");
      }
      PasswordResetToken token = new PasswordResetToken();
      token.setToken(UUID.randomUUID().toString());
      token.setUser(user);
      token.setExpiryDate(30);
      tokenRepository.save(token);

      Mail mail = new Mail();
      mail.setFrom("no-reply@logwintech.com");
      mail.setTo(user.getEmail());
      mail.setSubject("Password reset request");

      Map<String, Object> model = new HashMap<>();
      model.put("token", token);
      model.put("user", user);
      model.put("signature", "https://logwintech.com");
      String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
      model.put("resetUrl", url + "/api/reset-password?token=" + token.getToken());
      mail.setModel(model);
      emailService.sendEmail(mail);
		return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("password.reset.email.sent",args, locale),"");
  	
      //return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("user.saved", null, locale),userService.saveUser(user));
  }
  
  
  
}
