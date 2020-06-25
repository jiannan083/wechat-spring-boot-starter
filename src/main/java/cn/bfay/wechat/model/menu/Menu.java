package cn.bfay.lion.wechat.model.menu;

import lombok.Data;

/**
 * 菜单.
 */
@Data
public class Menu {
    /**
     * 一级菜单数组，个数应为1~3个.
     */
    private Button[] button;
}
