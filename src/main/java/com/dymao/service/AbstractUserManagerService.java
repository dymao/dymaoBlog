package com.dymao.service;

import com.dymao.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mervin
 * @Description:
 * @date 2017-12-25 00:52
 */
public abstract class AbstractUserManagerService implements UserManagerService {

    @Override
    public final String authenticate(User user) throws Exception {
        decryptUserPwd(user);
        return checkUser(user);
    }

    @Override
    public void initUser(User user, HttpServletRequest request) throws Exception {

    }

    /**
     * 解密用户的密码方法
     * @param user
     * @throws Exception
     */
    protected abstract void  decryptUserPwd(User user) throws Exception;

    /**
     * 校验用户信息的方法
     * @param user
     * @return
     * @throws Exception
     */
    protected abstract String  checkUser(User user) throws Exception;
}
