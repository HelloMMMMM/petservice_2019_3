package com.hellom.petserviceandroid.util;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

public class KeyBoardLayoutUtil {
    /**
     * @param root 最外层布局，需要调整的布局
     */
    public static void controlKeyboardLayout(final View root) {
        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                View scrollToView = root.findFocus();
                if (scrollToView == null) {
                    return;
                }
                Rect rect = new Rect();
                //获取root在窗体的可视区域
                root.getWindowVisibleDisplayFrame(rect);
                //获取root在窗体的不可视区域高度(被其他View遮挡的区域高度)
                int rootInvisibleHeight = root.getRootView().getHeight() - rect.bottom;
                //若不可视区域高度大于100，则键盘显示
                if (rootInvisibleHeight > 100) {
                    //若焦点view被遮挡了
                    if (scrollToView.getBottom() > rect.bottom) {
                        int[] location = new int[2];
                        //获取scrollToView在窗体的坐标
                        scrollToView.getLocationInWindow(location);
                        //计算root滚动高度，使scrollToView在可见区域
                        int scrollHeight = (location[1] + scrollToView.getHeight()) - rect.bottom;
                        root.scrollTo(0, scrollHeight);
                    }
                } else {
                    //键盘隐藏
                    root.scrollTo(0, 0);
                }
            }
        });
    }
}
