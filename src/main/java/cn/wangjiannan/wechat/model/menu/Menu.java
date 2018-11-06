package cn.wangjiannan.wechat.model.menu;

import java.util.Arrays;

/**
 * 菜单.
 */
public class Menu {
    /**
     * 一级菜单数组，个数应为1~3个.
     */
    private Button[] button;

    public Button[] getButton() {
        return button;
    }

    public void setButton(Button[] button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "button=" + Arrays.toString(button) +
                '}';
    }
}
