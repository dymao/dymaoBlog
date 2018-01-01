package com.dymao.service.impl;

import com.dymao.common.Utils.MD5Util;
import com.dymao.common.Utils.RSAUtils;
import com.dymao.common.constants.Constant;
import com.dymao.dao.mapper.AdminUserMapper;
import com.dymao.model.AdminUser;
import com.dymao.model.User;
import com.dymao.service.AbstractUserManagerService;
import com.dymao.service.UserManagerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mervin
 * @Description:
 * @date 2017-12-24 23:54
 */
@Service
public class AdminUserManagerServiceImpl extends AbstractUserManagerService {


    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public void decryptUserPwd(User user,HttpServletRequest request) throws Exception {
        RSAUtils rsa = new RSAUtils();
        String Modulus = (String) request.getSession().getAttribute("Modulus");
        String private_exponent = (String)request.getSession().getAttribute("private_exponent");
        //根据模和私钥指数获取私钥
        RSAPrivateKey prkey = RSAUtils.getPrivateKey(Modulus,private_exponent);
        //解密
        String afterDecrypt = rsa.decrypttoStr(prkey,user.getPassword());
        logger.debug("解密后的密码是："+afterDecrypt);
        String stroePassword = MD5Util.encrypt(afterDecrypt);
        if(StringUtils.isEmpty(stroePassword)){
            throw new Exception("密码加密错误！！！");
        }
        user.setPassword(stroePassword);
    }

    @Override
    public String checkUser(User user) throws Exception {
        Map paramMap = new HashMap();
        paramMap.put("loginId",user.getLoginId());
        List<AdminUser> list = adminUserMapper.queryUserByMap(paramMap);
        if(list == null || list.isEmpty() || list.size() > 1){
            return  Constant.LOGIN_VRRFY_STATUS_4;
        }
        AdminUser adminUser = list.get(0);
        if(!StringUtils.equals(adminUser.getPassword(),user.getPassword())){
            return  Constant.LOGIN_VRRFY_STATUS_4;
        }
        user.setId(adminUser.getId());
        return Constant.LOGIN_VRRFY_STATUS_0;
    }

    @Override
    public void initUser(User user,HttpServletRequest request) throws Exception {
        // 查询用户信息，初始化user并存到session中
        if(user == null || user.getId() == null){
            throw new Exception("初始化用户信息失败！！！");
        }
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(user.getId());
        HttpSession session = request.getSession();
        session.setAttribute("adminUser",adminUser);
    }
}
