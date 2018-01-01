package com.dymao.service;

import com.dymao.common.Utils.RSAUtils;
import com.dymao.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.security.interfaces.RSAPrivateKey;

/**
 * @author Mervin
 * @Description:
 * @date 2017-12-25 00:52
 */
public abstract class AbstractUserManagerService implements UserManagerService {

    protected static final Logger logger = LoggerFactory.getLogger(AbstractUserManagerService.class);

    @Override
    public final String authenticate(User user,HttpServletRequest request) throws Exception {
        decryptUserPwd(user,request);
        return checkUser(user);
    }

    public abstract void initUser(User user, HttpServletRequest request) throws Exception;

    /**
     * 解密用户的密码方法
     * @param user
     * @throws Exception
     */
    protected abstract void  decryptUserPwd(User user,HttpServletRequest request) throws Exception;

    /**
     * 校验用户信息的方法
     * @param user
     * @return
     * @throws Exception
     */
    protected abstract String  checkUser(User user) throws Exception;
}
