package com.dymao.service;

import com.dymao.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mervin
 * @Description:
 * @date 2017-12-24 23:45
 */
public interface UserManagerService {

    String authenticate(User user,HttpServletRequest request)throws Exception;

    void initUser(User user,HttpServletRequest request) throws Exception;
}
