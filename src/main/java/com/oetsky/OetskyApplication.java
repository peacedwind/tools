package com.oetsky;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 *
 * @author ruoyi
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class OetskyApplication {

    private static final Logger log = LoggerFactory.getLogger(OetskyApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(OetskyApplication.class, args);
        log.info("(♥◠‿◠)ﾉﾞ  格蓝若通讯工具启动成功   ლ(´ڡ`ლ)ﾞ  ");
    }
}