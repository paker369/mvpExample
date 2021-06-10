/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pakersite.example.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;



import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.utils.LogUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshInitializer;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;


import butterknife.ButterKnife;

import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import timber.log.Timber;



/**
 * ================================================
 * 展示 {@link AppLifecycles} 的用法
 * <p>
 * Created by JessYan on 04/09/2017 17:12
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class AppLifecyclesImpl implements AppLifecycles {

    @Override
    public void attachBaseContext(@NonNull Context base) {
//          MultiDex.install(base);  //这里比 onCreate 先执行,常用于 MultiDex 初始化,插件化框架的初始化
    }

    @Override
    public void onCreate(@NonNull Application application) {
//        if (LeakCanary.isInAnalyzerProcess(application)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
        LogUtils.debugInfo("创建了activity");
        new Thread(new Runnable() {
            @Override
            public void run() {
//                if (BuildConfig.LOG_DEBUG) {//Timber初始化
//                    Timber.plant(new Timber.DebugTree());
//                    ButterKnife.setDebug(true);
//                }

//                setDebugMode(true);

                setRxJavaErrorHandler();

            }
        }).start();

        //LeakCanary 内存泄露检查
        //使用 IntelligentCache.KEY_KEEP 作为 key 的前缀, 可以使储存的数据永久存储在内存中
        //否则存储在 LRU 算法的存储空间中, 前提是 extras 使用的是 IntelligentCache (框架默认使用)
//        ArmsUtils.obtainAppComponentFromContext(application).extras()
//                .put(IntelligentCache.getKeyOfKeep(RefWatcher.class.getName())
//                        , BuildConfig.USE_CANARY ? LeakCanary.install(application) : RefWatcher.DISABLED);
        //激光初始化


    }

    @Override
    public void onTerminate(@NonNull Application application) {

    }

    static {
        //启用矢量图兼容
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        //设置全局默认配置（优先级最低，会被其他设置覆盖）
        SmartRefreshLayout.setDefaultRefreshInitializer(new DefaultRefreshInitializer() {
            @Override
            public void initialize(@NonNull Context context, @NonNull RefreshLayout layout) {
                //全局设置（优先级最低）https://github.com/scwang90/SmartRefreshLayout/blob/master/art/md_property.md
                layout.setEnableAutoLoadMore(false);//使上拉加载具有弹性效果/在列表滚动到底部时自动加载更多
                layout.setEnableOverScrollDrag(false);//禁止越界拖动（1.0.4以上版本）
                layout.setEnableOverScrollBounce(false);//关闭越界回弹功能
                layout.setEnableLoadMoreWhenContentNotFull(true);//是否在列表不满一页时候开启上拉加载功能
                layout.setEnableScrollContentWhenRefreshed(false);//是否在全部加载结束之后Footer跟随内容1.0.4

            }
        });

        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
//                //全局设置主题颜色（优先级第二低，可以覆盖 DefaultRefreshInitializer 的配置，与下面的ClassicsHeader绑定）
//                layout.setPrimaryColorsId(android.R.color.white, R.color.colorText_a);

                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));
//                return new GifRefreshHeader(context);
            }

        });

        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(18);
            }
        });
    }

    /**
     * RxJava2 当取消订阅后(dispose())，
     * RxJava 抛出的异常后续无法接收(此时后台线程仍在跑，可能会抛出IO等异常),
     * 全部由 RxJavaPlugin 接收，需要提前设置 ErrorHandler.
     */
    private void setRxJavaErrorHandler() {
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                // throwable.printStackTrace();
                if (null != throwable)
                    LogUtils.debugInfo("测试Rxjava有错误 " + throwable.getMessage());
            }
        });
    }
}
