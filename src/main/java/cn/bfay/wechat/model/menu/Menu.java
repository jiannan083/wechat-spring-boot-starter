package cn.bfay.wechat.model.menu;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 菜单.
 */
public class Menu implements Serializable {
    private static final long serialVersionUID = -4241907984367924371L;

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
