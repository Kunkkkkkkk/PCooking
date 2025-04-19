package com.ruoyi.pda.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.service.ISysUserService;

/**
 * 注册相关控制器
 */
@RestController
@RequestMapping("/Register")
@Anonymous
public class RegisterController extends BaseController {
    
    @Autowired
    private ISysUserService userService;
    
    @Autowired
    private RedisCache redisCache;
    
    @Autowired
    private TokenService tokenService;
    
    /**
     * 获取手机验证码
     */
    @GetMapping("/code")
    public AjaxResult getVerificationCode(@RequestParam String phone) {
        if (StringUtils.isEmpty(phone)) {
            return AjaxResult.error("手机号不能为空");
        }
        
        // 检查手机号格式
        if (!phone.matches("^1[3-9]\\d{9}$")) {
            return AjaxResult.error("手机号格式不正确");
        }
        
        // 生成6位随机验证码
        String code = generateVerificationCode();
        
        // 存储验证码到Redis，设置5分钟过期时间
        String codeKey = getCacheKey(phone);
        redisCache.setCacheObject(codeKey, code, 5, TimeUnit.MINUTES);
        
        // TODO: 发送短信验证码，这里仅打印到控制台
        System.out.println("向手机号 " + phone + " 发送验证码: " + code);
        
        return AjaxResult.success("验证码发送成功");
    }
    
    /**
     * 用户注册
     */
    @PostMapping
    public AjaxResult register(@RequestBody Map<String, String> registerData) {
        String phone = registerData.get("phone");
        String password = registerData.get("password");
        String code = registerData.get("code");
        
        // 参数校验
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(password) || StringUtils.isEmpty(code)) {
            return AjaxResult.error("注册信息不完整");
        }
        
        // 从Redis获取验证码
        String codeKey = getCacheKey(phone);
        String storedCode = redisCache.getCacheObject(codeKey);
        
        // 验证码校验
        if (StringUtils.isEmpty(storedCode)) {
            return AjaxResult.error("验证码已过期");
        }
        
        if (!storedCode.equals(code)) {
            return AjaxResult.error("验证码错误");
        }
        
        // 检查用户是否已存在
        SysUser existUser = userService.selectUserByUserName(phone);
        if (existUser != null) {
            return AjaxResult.error("手机号已注册");
        }
        
        // 创建用户对象
        SysUser user = new SysUser();
        user.setUserName(phone); // 用户名使用手机号
        user.setPhonenumber(phone); // 手机号
        user.setNickName("用户" + generateRandomString(6)); // 随机昵称
        user.setPassword(SecurityUtils.encryptPassword(password)); // 加密密码
        
        // 设置默认值
        user.setDeptId(100L); // 默认部门
        user.setStatus("0"); // 正常状态
        user.setDelFlag("0"); // 未删除
        
        // 保存用户
        if (userService.insertUser(user) > 0) {
            // 清除验证码
            redisCache.deleteObject(codeKey);
            
            // 注册成功后自动登录，生成token
            LoginUser loginUser = new LoginUser();
            loginUser.setUserId(user.getUserId());
            loginUser.setUser(user);
            
            // 生成token
            String token = tokenService.createToken(loginUser);
            
            // 返回结果
            Map<String, Object> result = new HashMap<>();
            result.put("token", token);
            result.put("msg", "注册成功");
            result.put("code", 200);
            
            return AjaxResult.success(result);
        } else {
            return AjaxResult.error("注册失败");
        }
    }
    
    /**
     * 生成6位数字验证码
     */
    private String generateVerificationCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }
    
    /**
     * 生成指定长度的随机字符串
     */
    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        
        return sb.toString();
    }
    
    /**
     * 获取缓存键值
     */
    private String getCacheKey(String phone) {
        return CacheConstants.CAPTCHA_CODE_KEY + "REGISTER_" + phone;
    }
} 