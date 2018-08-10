package com.yangonion.springbootrestfulapi.service;

import com.yangonion.springbootrestfulapi.domain.User;

import java.util.List;

/** 用户接口
 * @author Yang
 */
public interface UserService {

    /**添加用户
     * @param user
     */
    void addUser(User user);

    /**删除用户
     * @param id
     */
    void removeUser(Long id);

    /**删除所有用户
     *
     */
    void removeAllUsers();


    /**更新用户
     * @param user
     */
    void updateUser(User user);

    /**获取用户列表
     *
     */
    List<User> getUserList();

    /**获取单个用户
     * @param id
     */
    User getUser(Long id);



}
