package com.hjcrm.system.mapper;


import com.hjcrm.system.entity.Menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MenuDao {
	public List<Menu> queryMenuByRoleId(HashMap<String,String> hashMap);


}
