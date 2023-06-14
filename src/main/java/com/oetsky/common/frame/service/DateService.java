package com.oetsky.common.frame.service;

import java.util.Date;
import org.springframework.stereotype.Service;

/**
 * @Desc:
 * @AUTHOR: huwm
 * @DATE: 2022/5/31
 */
@Service("dateUtils")
public class DateService {

    /**
     * 获取服务器时间
     * @return
     */
    public static Date getNowDate(){
        return new Date();
    }
}
