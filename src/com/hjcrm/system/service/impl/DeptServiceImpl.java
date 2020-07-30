package com.hjcrm.system.service.impl;

import com.hjcrm.publics.util.PageBean;
import com.hjcrm.system.entity.Dept;
import com.hjcrm.system.entity.Menu;
import com.hjcrm.system.entity.Role;
import com.hjcrm.system.entity.User;
import com.hjcrm.system.mapper.DeptDao;
import com.hjcrm.system.mapper.MenuDao;
import com.hjcrm.system.mapper.RoleDao;
import com.hjcrm.system.mapper.UserDao;
import com.hjcrm.system.service.IDeptService;
import com.hjcrm.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service("deptService")
public class DeptServiceImpl implements IDeptService {

    @Autowired
    DeptDao deptDao;

    @Autowired
    RoleDao roleDao;

    @Override
    public List<Dept> queryAllDept() {
        return deptDao.queryAllDept();
    }

    @Override
    public List<Role> queryRoleByDeptId(String deptId) {
        return roleDao.queryRoleByDeptId(deptId);
    }
}
