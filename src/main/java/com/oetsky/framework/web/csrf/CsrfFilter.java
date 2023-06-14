package com.oetsky.framework.web.csrf;

import com.oetsky.common.utils.StringUtils;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 防止csrf攻击的过滤器
 *
 * @author ruoyi
 */
public class CsrfFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String spath = req.getServletPath();
        String[] urls = {"/json", ".js", ".css", ".ico", ".jpg", ".png"};
        boolean flag = true;
        for (String url : urls) {
            if (StringUtils.isNotEmpty(spath) && spath.indexOf(url) != -1) {
                flag = false;
                break;
            }
        }
        if (!flag) {
            filterChain.doFilter(request, response);
            return;
        }
        String referer = req.getHeader("Referer");
        String serverName = req.getServerName();
        if (StringUtils.isNotEmpty(serverName) &&
                StringUtils.isNotEmpty(referer) &&
                referer.trim().indexOf(serverName) == -1) {
            throw new RuntimeException("Illegal Origin");
        }
        filterChain.doFilter(request, response);
    }

}
