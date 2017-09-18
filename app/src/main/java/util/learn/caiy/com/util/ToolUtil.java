package util.learn.caiy.com.util;

import android.content.Context;

/**
 * Created by yongc on 2017/8/10.
 */

public class ToolUtil {

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
}
