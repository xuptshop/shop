package cn.shop.merchant.service;
import cn.shop.user.dao.UserDao;
import cn.shop.user.vo.User;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商家模块Service
 *
 * @author striner
 * @create 2019/4/19 17:26
 */

@Transactional
public class MerchantService {

    private UserDao userDao;


    public User login(User adminUser) {
     return userDao.loginMerchant(adminUser);
    }

    // 按username查询是否有该商家用户:
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public User findByUid(Integer uid) {
        return userDao.findByUid(uid);
    }

    // 注册商家用户存入数据库代码实现
    public void save(User user) {
        userDao.save(user);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
