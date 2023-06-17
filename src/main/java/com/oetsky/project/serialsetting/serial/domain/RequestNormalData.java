package com.oetsky.project.serialsetting.serial.domain;


/**
 * 请求类型拆份
 * @author zhangw
 */
public class RequestNormalData {
    /**
     * 请求类型
     * 01：预连接请求
     * 02：应用连接请求
     * 03：断开应用连接请求
     * 04：
     * 05：读取请求{01读取一个对象属性请求  02 读取若干个对象属性请求}
     * 06：设置请求
     * 07：操作请求
     * 08：上报响应{01上报若干个对象属性的响应，02 上报若干个记录型对象属性的响应}
     * 09：代理请求{01代理读取若干个服务器的若干个对象属性请求  02 代码读取一个服务器的一个记录型对象属性请求}
     */
    public String type;

    /**
     * 请求项
     */
    public String number;

    /**
     * 获取 请求类型
     * 01：预连接请求
     * 02：应用连接请求
     * 03：断开应用连接请求
     * 04：
     * 05：读取请求{01读取一个对象属性请求  02 读取若干个对象属性请求}
     * 06：设置请求
     * 07：操作请求
     * 08：上报响应{01上报若干个对象属性的响应，02 上报若干个记录型对象属性的响应}
     * 09：代理请求{01代理读取若干个服务器的若干个对象属性请求  02 代码读取一个服务器的一个记录型对象属性请求}
     *
     * @return type
     */
    public String getType() {
        return this.type;
    }

    /**
     * 设置 请求类型
     * 01：预连接请求
     * 02：应用连接请求
     * 03：断开应用连接请求
     * 04：
     * 05：读取请求{01读取一个对象属性请求  02 读取若干个对象属性请求}
     * 06：设置请求
     * 07：操作请求
     * 08：上报响应{01上报若干个对象属性的响应，02 上报若干个记录型对象属性的响应}
     * 09：代理请求{01代理读取若干个服务器的若干个对象属性请求  02 代码读取一个服务器的一个记录型对象属性请求}
     *
     * @return type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取 请求项
     *
     * @return number 请求项
     */
    public String getNumber() {
        return this.number;
    }

    /**
     * 设置 请求项
     *
     * @param number 请求项
     */
    public void setNumber(String number) {
        this.number = number;
    }
}

