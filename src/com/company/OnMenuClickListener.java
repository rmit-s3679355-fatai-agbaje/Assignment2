package com.company;

import javax.swing.*;

/**
 * A Menu click listener
 */
public interface OnMenuClickListener {
    /**
     * This method is fired when a menu item is clicked
     * @param panel the new JPanel UI Component to be displayed
     */
    void onMenuClicked(JPanel panel);
}
