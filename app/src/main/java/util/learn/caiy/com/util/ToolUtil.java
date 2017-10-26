package util.learn.caiy.com.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by yongc on 2017/8/10.
 */

public class ToolUtil {

    private static Screen screen = null;

    public static int getStatusBarHeight(Context context)
    {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
        {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int dip2px(Context context, float dipValue) {
        float scale = context.getResources()
                .getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources()
                .getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int getWidth(Context context) {
        initScreen(context);
        if (screen != null) {
            return screen.widthPixels;
        }
        return 0;
    }

    public static int getHeight(Context context) {
        initScreen(context);
        if (screen != null) {
            return screen.heightPixels;
        }
        return 0;
    }

    private static void initScreen(Context context) {
        if (screen == null) {
            DisplayMetrics dm = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(dm);
            if (dm.widthPixels > dm.heightPixels) {
                screen = new Screen(dm.heightPixels, dm.widthPixels);
            } else {
                screen = new Screen(dm.widthPixels, dm.heightPixels);
            }
        }
    }

    public static class Screen {
        public int widthPixels;
        public int heightPixels;

        public Screen() {
        }

        public Screen(int widthPixels, int heightPixels) {
            this.widthPixels = widthPixels;
            this.heightPixels = heightPixels;
        }

        @NonNull
        @Override
        public String toString() {
            return "(" + widthPixels + "," + heightPixels + ")";
        }

    }
}
