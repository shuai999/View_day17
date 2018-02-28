package com.jackchen.view_day17;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * @email :  2185134304@qq.com
 * @date :2018/2/26
 * @author : Jack-Chen
 * @Description: 设置状态栏工具类
 *
 */
public class StatusBarUtils {

    /**
     *  4.4 -> API19  5.0 -> API21
     *
     * 给我们的 Activity 的状态栏设置颜色
     * @param activity
     * @param color
     */
    public static void setStatusBarColor(Activity activity , int color){
        // 5.0 以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            //直接调用系统提供的方法  setStatusBarColor()
            activity.getWindow().setStatusBarColor(color);
        }
        // 4.4 - 5.0 之间  采用一个技巧，首先把它弄成一个全屏，然后在状态栏的部分添加一个布局
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            //首先把它弄成一个全屏
            //电量、时间、网络状态都还在
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            //在状态栏的部分加一个布局 具体实现方法可以参照setContentView源码分析 自己加一个布局(高度是状态栏的高度)
            View view = new View(activity) ;
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT ,
                    getStatusBarHeight(activity)) ;
            view.setLayoutParams(params);
            view.setBackgroundColor(color);

            //  也可以直接在布局文件中的根布局 写这个 如果你给状态栏设置了颜色，但是布局中的内容不见了，比如里边只有一个HelloWorld，不见了，可以写下边属性
            //  android:fitsSystemWindows="true"  这个属性是自适应Window 它的缺点就是如果你需要给每个Activity的布局都设置状态栏颜色，
            //  那么你就必须给每个布局都要写 ， 太麻烦了，如果不想给每个布局都去写，可以这样做
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            decorView.addView(view);

            // 获取activity中setContentView布局的根布局
            ViewGroup contentView = (ViewGroup) activity.findViewById(android.R.id.content);
            contentView.setPadding(0,getStatusBarHeight(activity),0,0);
        }

    }


    /**
     * 获取状态栏的高度
     * @param activity
     * @return
     */
    private static int getStatusBarHeight(Activity activity) {
        // 实现方式可以参考  插件式换肤：怎么获取资源的，先获取资源id，根据id获取资源
        Resources resources = activity.getResources();
        /*public int getIdentifier(String name, String defType, String defPackage)
          下边固定3个值是根据源码中参考的  参考插件式换肤*/
        int statusBarHeightId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelOffset(statusBarHeightId);
    }


    /**
     *  设置Activity全屏
     * @param activity
     */
    public static void setActivityTranslucent(Activity activity){
        // 5.0 以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            View decorView = activity.getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        // 4.4 - 5.0 之间
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
