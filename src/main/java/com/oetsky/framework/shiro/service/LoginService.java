package com.oetsky.framework.shiro.service;

import com.oetsky.common.constant.Constants;
import com.oetsky.common.constant.ShiroConstants;
import com.oetsky.common.constant.UserConstants;
import com.oetsky.common.exception.user.CaptchaException;
import com.oetsky.common.exception.user.UserBlockedException;
import com.oetsky.common.exception.user.UserDeleteException;
import com.oetsky.common.exception.user.UserNotExistsException;
import com.oetsky.common.exception.user.UserPasswordNotMatchException;
import com.oetsky.common.utils.DateUtils;
import com.oetsky.common.utils.MessageUtils;
import com.oetsky.common.utils.ServletUtils;
import com.oetsky.common.utils.StringUtils;
import com.oetsky.common.utils.security.ShiroUtils;
import com.oetsky.framework.manager.AsyncManager;
import com.oetsky.framework.manager.factory.AsyncFactory;
import com.oetsky.project.system.user.domain.User;
import com.oetsky.project.system.user.domain.UserStatus;
import com.oetsky.project.system.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 登录校验方法
 *
 * @author ruoyi
 */
@Component
public class LoginService {

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private IUserService userService;

    /**
     * 登录
     */
    public User login(String username, String password) {
        // 验证码校验
        if (ShiroConstants.CAPTCHA_ERROR
            .equals(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA))) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL,
                MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaException();
        }
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL,
                MessageUtils.message("not.null")));
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
            || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL,
                MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }

        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
            || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL,
                MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }

        // 查询用户信息
        User user = userService.selectUserByLoginName(username);

        /**
         if (user == null && maybeMobilePhoneNumber(username))
         {
         user = userService.selectUserByPhoneNumber(username);
         }

         if (user == null && maybeEmail(username))
         {
         user = userService.selectUserByEmail(username);
         }
         */

        if (user == null) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL,
                MessageUtils.message("user.not.exists")));
            throw new UserNotExistsException();
        }

        if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL,
                MessageUtils.message("user.password.delete")));
            throw new UserDeleteException();
        }

        if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL,
                MessageUtils.message("user.blocked", user.getRemark())));
            throw new UserBlockedException();
        }

        passwordService.validate(user, password);

        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS,
            MessageUtils.message("user.login.success")));
        recordLoginInfo(user.getUserId());
        return user;
    }

    /**
     private boolean maybeEmail(String username)
     {
     if (!username.matches(UserConstants.EMAIL_PATTERN))
     {
     return false;
     }
     return true;
     }

     private boolean maybeMobilePhoneNumber(String username)
     {
     if (!username.matches(UserConstants.MOBILE_PHONE_NUMBER_PATTERN))
     {
     return false;
     }
     return true;
     }
     */

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(Long userId) {
        User user = new User();
        user.setUserId(userId);
        user.setLoginIp(ShiroUtils.getIp());
        user.setLoginDate(DateUtils.getNowDate());
        userService.updateUserInfo(user);
    }
}
