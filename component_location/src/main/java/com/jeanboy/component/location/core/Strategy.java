package com.jeanboy.component.location.core;

/**
 * @author caojianbo
 * @since 2019/12/4 10:08
 */
public class Strategy {

    /**
     * 先获取缓存位置，没有则获取一次网络定位
     */
    public static final int ONLY_ONCE = 1;

    /**
     * 先获取缓存位置，然后持续获取网络位置
     */
    public static final int SUSTAINED_LOW = 2;

    /**
     * 先获取缓存位置，没有优先获取网络位置，最后持续获取 GPS 位置
     */
    public static final int SUSTAINED_HIGH = 3;
}
