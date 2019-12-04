package com.jeanboy.component.location.core;

/**
 * @author caojianbo
 * @since 2019/12/4 10:08
 */
public class Strategy {

    /**
     * 有缓存先返回缓存位置，然后获取网络实时位置
     */
    public static final int NETWORK_PRIORITY = 1;
    /**
     * 有缓存先返回缓存位置，
     * 网络定位可用时先获取网络位置，
     * 最后获取实时 GPS 位置
     */
    public static final int GPS_PRIORITY = 2;
}
