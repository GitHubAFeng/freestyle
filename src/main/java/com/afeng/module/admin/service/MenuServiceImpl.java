package com.afeng.module.admin.service;

import com.afeng.common.cache.JedisUtil;
import com.afeng.module.admin.dao.MenuMapper;
import com.afeng.module.admin.dao.RoleMenuMapper;
import com.afeng.module.admin.model.Menu;
import com.afeng.module.admin.model.Role;
import com.afeng.module.admin.model.User;
import com.afeng.module.admin.model.Ztree;
import com.afeng.module.common.constant.UserConstants;
import com.afeng.module.common.utils.AdminUtils;
import com.afeng.module.common.utils.StringUtils;
import com.afeng.module.common.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.*;

/**
 * 菜单 业务层处理
 *
 * @author ruoyi
 */
@Service
public class MenuServiceImpl implements IMenuService {
    public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private JedisUtil jedisUtil;

    /**
     * 根据用户查询菜单
     *
     * @param user 用户信息
     * @return 菜单列表
     */
    @Override
    public List<Menu> selectMenusByUser(User user) {
        List<Menu> menus = new LinkedList<Menu>();
        // 管理员显示所有菜单信息
        if (user.isAdmin()) {
            menus = menuMapper.selectMenuNormalAll();
        } else {
            menus = menuMapper.selectMenusByUserId(user.getUserId());
            // 设置用户菜单权限，如果是管理员用户就没必要设置了，默认拥有全部权限
            AdminUtils.setSysUserPermissionCache(jedisUtil, menus);
        }
        return TreeUtils.getChildPerms(menus, 0);
    }

    /**
     * 查询菜单集合
     *
     * @return 所有菜单信息
     */
    @Override
    public List<Menu> selectMenuList(Menu menu) {
        List<Menu> menuList = null;
        User user = AdminUtils.getSysUser();
        if (user.isAdmin()) {
            menuList = menuMapper.selectMenuList(menu);
        } else {
            menu.getParams().put("userId", user.getUserId());
            menuList = menuMapper.selectMenuListByUserId(menu);
        }
        return menuList;
    }

    /**
     * 查询菜单集合
     *
     * @return 所有菜单信息
     */
    @Override
    public List<Menu> selectMenuAll() {
        List<Menu> menuList = null;
        User user = AdminUtils.getSysUser();
        if (user.isAdmin()) {
            menuList = menuMapper.selectMenuAll();
        } else {
            menuList = menuMapper.selectMenuAllByUserId(user.getUserId());
        }
        return menuList;
    }

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectPermsByUserId(Long userId) {
        List<String> perms = menuMapper.selectPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotEmpty(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 根据角色ID查询菜单
     *
     * @param role 角色对象
     * @return 菜单列表
     */
    @Override
    public List<Ztree> roleMenuTreeData(Role role) {
        Long roleId = role.getRoleId();
        List<Ztree> ztrees = new ArrayList<Ztree>();
        List<Menu> menuList = selectMenuAll();
        if (StringUtils.isNotNull(roleId)) {
            List<String> roleMenuList = menuMapper.selectMenuTree(roleId);
            ztrees = initZtree(menuList, roleMenuList, true);
        } else {
            ztrees = initZtree(menuList, null, true);
        }
        return ztrees;
    }

    /**
     * 查询所有菜单
     *
     * @return 菜单列表
     */
    @Override
    public List<Ztree> menuTreeData() {
        List<Menu> menuList = selectMenuAll();
        List<Ztree> ztrees = initZtree(menuList);
        return ztrees;
    }

    /**
     * 查询系统所有权限
     *
     * @return 权限列表
     */
    @Override
    public LinkedHashMap<String, String> selectPermsAll() {
        LinkedHashMap<String, String> section = new LinkedHashMap<>();
        List<Menu> permissions = selectMenuAll();
        if (StringUtils.isNotEmpty(permissions)) {
            for (Menu menu : permissions) {
                section.put(menu.getUrl(), MessageFormat.format(PREMISSION_STRING, menu.getPerms()));
            }
        }
        return section;
    }

    /**
     * 对象转菜单树
     *
     * @param menuList 菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<Menu> menuList) {
        return initZtree(menuList, null, false);
    }

    /**
     * 对象转菜单树
     *
     * @param menuList     菜单列表
     * @param roleMenuList 角色已存在菜单列表
     * @param permsFlag    是否需要显示权限标识
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<Menu> menuList, List<String> roleMenuList, boolean permsFlag) {
        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(roleMenuList);
        for (Menu menu : menuList) {
            Ztree ztree = new Ztree();
            ztree.setId(menu.getMenuId());
            ztree.setpId(menu.getParentId());
            ztree.setName(transMenuName(menu, permsFlag));
            ztree.setTitle(menu.getMenuName());
            if (isCheck) {
                ztree.setChecked(roleMenuList.contains(menu.getMenuId() + menu.getPerms()));
            }
            ztrees.add(ztree);
        }
        return ztrees;
    }

    public String transMenuName(Menu menu, boolean permsFlag) {
        StringBuffer sb = new StringBuffer();
        sb.append(menu.getMenuName());
        if (permsFlag) {
            sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + menu.getPerms() + "</font>");
        }
        return sb.toString();
    }

    /**
     * 删除菜单管理信息
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public int deleteMenuById(Long menuId) {
        AdminUtils.cleanSysUserPermissionCache(jedisUtil);
        return menuMapper.deleteMenuById(menuId);
    }

    /**
     * 根据菜单ID查询信息
     *
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    @Override
    public Menu selectMenuById(Long menuId) {
        return menuMapper.selectMenuById(menuId);
    }

    /**
     * 查询子菜单数量
     *
     * @param parentId 菜单ID
     * @return 结果
     */
    @Override
    public int selectCountMenuByParentId(Long parentId) {
        return menuMapper.selectCountMenuByParentId(parentId);
    }

    /**
     * 查询菜单使用数量
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public int selectCountRoleMenuByMenuId(Long menuId) {
        return roleMenuMapper.selectCountRoleMenuByMenuId(menuId);
    }

    /**
     * 新增保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public int insertMenu(Menu menu) {
        menu.setCreateBy(AdminUtils.getSysUser().getLoginName());
        AdminUtils.cleanSysUserPermissionCache(jedisUtil);
        return menuMapper.insertMenu(menu);
    }

    /**
     * 修改保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public int updateMenu(Menu menu) {
        menu.setUpdateBy(AdminUtils.getSysUser().getLoginName());
        AdminUtils.cleanSysUserPermissionCache(jedisUtil);
        return menuMapper.updateMenu(menu);
    }

    /**
     * 校验菜单名称是否唯一
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public String checkMenuNameUnique(Menu menu) {
        Long menuId = StringUtils.isNull(menu.getMenuId()) ? -1L : menu.getMenuId();
        Menu info = menuMapper.checkMenuNameUnique(menu.getMenuName(), menu.getParentId());
        if (StringUtils.isNotNull(info) && info.getMenuId().longValue() != menuId.longValue()) {
            return UserConstants.MENU_NAME_NOT_UNIQUE;
        }
        return UserConstants.MENU_NAME_UNIQUE;
    }
}
