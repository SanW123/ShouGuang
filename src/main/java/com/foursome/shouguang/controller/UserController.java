package com.foursome.shouguang.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.foursome.shouguang.common.R;
import com.foursome.shouguang.entity.PhoneValidate;
import com.foursome.shouguang.entity.User;
import com.foursome.shouguang.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 登录
	 * @param request
	 * @param user
	 * @return
	 */
	@PostMapping("/login")
	@CrossOrigin(allowCredentials = "true",allowedHeaders = "*", originPatterns ="*")
	public R<User> login(HttpServletRequest request, @RequestBody User user){
		//1、将页面提交的密码password
		String password = user.getPassword();
		//2、根据页面提交的用户名username查询数据库
		LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(User::getUsername,user.getUsername());
		User usr = userService.getOne(queryWrapper);
		//3、如果没有查询到则返回登录失败结果
		if(usr == null){
			return R.error("账号不存在!");
		}
		//4、密码比对，如果不一致则返回登录失败结果
		if(!usr.getPassword().equals(password)){
			return R.error("密码错误!");
		}
		//6、登录成功，将员工id存入Session并返回登录成功结果
		request.getSession().setAttribute("user",usr.getId());
		return R.success(usr);
	}
	/**
	 * 员工登录
	 * @param request
	 * @return
	 */
	@PostMapping("/phoneLogin")
	@CrossOrigin(allowCredentials = "true",allowedHeaders = "*", originPatterns ="*")
	public R<User> phoneLogin(HttpServletRequest request,  @RequestBody PhoneValidate phoneValidate){
		String phone = phoneValidate.getPhone();
		String validate = phoneValidate.getValidate();
		LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(User::getPhone,phone);
		User usr = userService.getOne(queryWrapper);
		if(!validate.equals("1234")){
			return R.error("验证码错误");
		}
		if(usr == null){
			return R.error("手机号未注册");
		}
		request.getSession().setAttribute("user",usr.getId());
		return R.success(usr);
	}


	/**
	 * 员工退出
	 * @param request
	 * @return
	 */
	@PostMapping("/logout")
	@CrossOrigin(allowCredentials = "true",allowedHeaders = "*", originPatterns ="*")
	public R<String> logout(HttpServletRequest request){
		//清理Session中保存的当前登录员工的id
		request.getSession().removeAttribute("user");
		return R.success("退出成功");
	}



	/**
	 * 根据id查询员工信息
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	@CrossOrigin(allowCredentials = "true",allowedHeaders = "*", originPatterns ="*")
	public R<User> getById(@PathVariable Long id){
		log.info("根据id查询用户信息...");
		User user = userService.getById(id);
		if(user != null){
			return R.success(user);
		}
		return R.error("没有查询到对应用户信息");
	}



}
