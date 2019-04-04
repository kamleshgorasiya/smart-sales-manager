package com.smart.sales.manager.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smart.sales.manager.entity.model.PasswordResetToken;
import com.smart.sales.manager.entity.model.User;
import com.smart.sales.manager.repository.PasswordResetTokenRepository;
import com.smart.sales.manager.request.model.Mail;
import com.smart.sales.manager.request.model.PasswordForgotDto;
import com.smart.sales.manager.request.model.UserDto;
import com.smart.sales.manager.response.model.ApiResponse;
import com.smart.sales.manager.service.EmailService;
import com.smart.sales.manager.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class PasswordForgotController {

//    @Autowired private UserService userService;
//    @Autowired private PasswordResetTokenRepository tokenRepository;
//    @Autowired private EmailService emailService;
//    @Autowired
//    private MessageSource messageSource;
//
//    @ModelAttribute("forgotPasswordForm")
//    public PasswordForgotDto forgotPasswordDto() {
//        return new PasswordForgotDto();
//    }
//
//    @GetMapping(path="/forgot-password")
//    public String displayForgotPasswordPage() {
//        return "forgot-password";
//    }
//    @PostMapping(path="/forgot-password",produces= {MediaType.APPLICATION_JSON_VALUE},consumes= {MediaType.APPLICATION_JSON_VALUE})
//    public ApiResponse<String> sendResetPasswordLink(@RequestHeader(name="Accept-language",required=false) Locale locale, @RequestBody UserDto userDto,HttpServletRequest request){
//    	User user = userService.findByEmail(userDto.getEmail());
//    	String[] args= {userDto.getEmail()};
//        if (user == null){        	
//        	return new ApiResponse<String>(HttpStatus.NOT_FOUND.value(), messageSource.getMessage("user.notfound",args, locale),"");
//        }
//        PasswordResetToken token = new PasswordResetToken();
//        token.setToken(UUID.randomUUID().toString());
//        token.setUser(user);
//        token.setExpiryDate(30);
//        tokenRepository.save(token);
//
//        Mail mail = new Mail();
//        mail.setFrom("no-reply@logwintech.com");
//        mail.setTo(user.getEmail());
//        mail.setSubject("Password reset request");
//
//        Map<String, Object> model = new HashMap<>();
//        model.put("token", token);
//        model.put("user", user);
//        model.put("signature", "https://logwintech.com");
//        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
//        model.put("resetUrl", url + "/api/reset-password?token=" + token.getToken());
//        mail.setModel(model);
//        emailService.sendEmail(mail);
//		return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("password.reset.email.sent",args, locale),"");
//    	
//        //return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("user.saved", null, locale),userService.saveUser(user));
//    }
	
	
//    @PostMapping(path="/forgot-password")
//    public String processForgotPasswordForm(@ModelAttribute("forgotPasswordForm") @Valid PasswordForgotDto form,
//                                            BindingResult result,
//                                            HttpServletRequest request) {
//
//        if (result.hasErrors()){
//            return "forgot-password";
//        }
//
//        User user = userService.findByEmail(form.getEmail());
//        if (user == null){
//            result.rejectValue("email", null, "We could not find an account for that e-mail address.");
//            return "forgot-password";
//        }
//
//        PasswordResetToken token = new PasswordResetToken();
//        token.setToken(UUID.randomUUID().toString());
//        token.setUser(user);
//        token.setExpiryDate(30);
//        tokenRepository.save(token);
//
//        Mail mail = new Mail();
//        mail.setFrom("no-reply@logwintech.com");
//        mail.setTo(user.getEmail());
//        mail.setSubject("Password reset request");
//
//        Map<String, Object> model = new HashMap<>();
//        model.put("token", token);
//        model.put("user", user);
//        model.put("signature", "https://logwintech.com");
//        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
//        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
//        mail.setModel(model);
//        emailService.sendEmail(mail);
//
//        return "redirect:/api/forgot-password?success";
//
//    }

}
