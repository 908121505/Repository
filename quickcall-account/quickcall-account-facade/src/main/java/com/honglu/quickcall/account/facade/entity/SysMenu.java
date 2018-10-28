package com.honglu.quickcall.account.facade.entity;

public class SysMenu {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 菜单名称
     */
    private String names;

    /**
     * 菜单访问地址
     */
    private String url;

    /**
     * 菜单ICON
     */
    private String imgIcon;

    /**
     * 排序
     */
    private Short orders;

    /**
     * 父菜单ID
     */
    private Integer parentId;

    /**
     * 描述
     */
    private String author;

    /**
     * 菜单类型
     */
    private String menuType;

    /**
     * 菜单状态 1有效 2失效
     */
    private Integer menuState;

    /**
     * 主键ID
     * @return id 主键ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 菜单名称
     * @return names 菜单名称
     */
    public String getNames() {
        return names;
    }

    /**
     * 菜单名称
     * @param names 菜单名称
     */
    public void setNames(String names) {
        this.names = names == null ? null : names.trim();
    }

    /**
     * 菜单访问地址
     * @return url 菜单访问地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 菜单访问地址
     * @param url 菜单访问地址
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 菜单ICON
     * @return img_icon 菜单ICON
     */
    public String getImgIcon() {
        return imgIcon;
    }

    /**
     * 菜单ICON
     * @param imgIcon 菜单ICON
     */
    public void setImgIcon(String imgIcon) {
        this.imgIcon = imgIcon == null ? null : imgIcon.trim();
    }

    /**
     * 排序
     * @return orders 排序
     */
    public Short getOrders() {
        return orders;
    }

    /**
     * 排序
     * @param orders 排序
     */
    public void setOrders(Short orders) {
        this.orders = orders;
    }

    /**
     * 父菜单ID
     * @return parent_id 父菜单ID
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 父菜单ID
     * @param parentId 父菜单ID
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 描述
     * @return author 描述
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 描述
     * @param author 描述
     */
    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    /**
     * 菜单类型
     * @return menu_type 菜单类型
     */
    public String getMenuType() {
        return menuType;
    }

    /**
     * 菜单类型
     * @param menuType 菜单类型
     */
    public void setMenuType(String menuType) {
        this.menuType = menuType == null ? null : menuType.trim();
    }

    /**
     * 菜单状态 1有效 2失效
     * @return menu_state 菜单状态 1有效 2失效
     */
    public Integer getMenuState() {
        return menuState;
    }

    /**
     * 菜单状态 1有效 2失效
     * @param menuState 菜单状态 1有效 2失效
     */
    public void setMenuState(Integer menuState) {
        this.menuState = menuState;
    }
}