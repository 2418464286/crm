package com.hjcrm.system.controller;

import com.hjcrm.publics.contstants.JumpViewConstants;
import com.hjcrm.publics.contstants.ReturnConstants;
import com.hjcrm.publics.util.JsonUtil;
import com.hjcrm.publics.util.PageBean;
import com.hjcrm.publics.util.UserContext;
import com.hjcrm.system.entity.User;
import com.hjcrm.system.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping("/system/userMang.do")
    /**
     * 加载用户管理页面到主页面
     */
    public String userManger() {
        if (UserContext.getLoginUser() != null) {
            return JumpViewConstants.SYSTEM_USER_MANAGER;
        }
        return JumpViewConstants.SYSTEM_LOGIN;
    }

    @RequestMapping("/system/userlist.do")
    /**
     * 查询用户信息列表
     */
    public @ResponseBody
    String queryAllUserInfo(Integer pageSize, Integer currentPage) {
        if (UserContext.getLoginUser() != null) {
            //用户登陆状态
            //查询所有用户信息
            System.out.println("请求用户 信息：" + pageSize + "/" + currentPage);
            //封装分页实体对象
            PageBean pb = new PageBean();
            pb.setCurrentPage((currentPage - 1) * pageSize); //limit 参数一，起始查询位置
            pb.setPageSize(pageSize); //limit 参数二，查询条数
            List<User> userList = userService.queryAllUserInfo(pb);
            for (User u : userList) {
                System.out.println("查询到的用户信息：" + u);
            }
            //获取总条数
            int total = userService.queryAllUserInfoCount();
            //返回结果数据到ajax
            return PageBean.jsonUserInfoToPage(userList, total, currentPage);
        }
        return ReturnConstants.PARAM_NULL;
    }

    @RequestMapping("/system/saveOrUpdate.do")
    public @ResponseBody
    String saveOrUpdateUserInfo(User user) {
        if (user != null) {
            System.out.println("需要添加的用户信息：" + user);
            //请求userService实现用户添加
            userService.saveOrUpdateUserInfo(user);
            return ReturnConstants.SUCCESS;
        }
        return ReturnConstants.PARAM_NULL;
    }

    /**
     * 删除用户信息（支持批量删除）
     *
     * @param ids 一个字符串（多个用户id使用逗号分割）
     * @return
     */
    @RequestMapping("/system/deleteUser.do")
    public @ResponseBody
    String deleteUserInfoByIds(String ids) {
        if (StringUtils.isNotBlank(ids)) {
            System.out.println("需要删除的用户信息字符串：" + ids);
            //请求userService实现用户添加
            int delResult = userService.deleteUserInfoByIds(ids); //批量删除
            System.out.println("删除的结果：" + delResult);
            return ReturnConstants.SUCCESS;
        }
        return ReturnConstants.PARAM_NULL;
    }

}


