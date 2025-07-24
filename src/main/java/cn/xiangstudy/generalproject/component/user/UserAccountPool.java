package cn.xiangstudy.generalproject.component.user;

import cn.xiangstudy.generalproject.component.redis.RedisOperation;
import cn.xiangstudy.generalproject.service.UserService;
import cn.xiangstudy.generalproject.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 生成账号池
 * @author zhangxiang
 * @date 2025-07-23 08:33
 */
@Component
public class UserAccountPool {

    private final Queue<String> accountPool = new ConcurrentLinkedDeque<>();
    private final int batchSize = 10;
    private Long currentMaxAccount;
    private final UserService userService;

    @Autowired
    public UserAccountPool(RedisOperation redisOperation, UserService userService) {
        this.userService = userService;
        initData();
    }

    // 初始赋值
    private void initData() {
        // 从数据库中查找当前最大账号
        String maxAccount = userService.selectMaxUserAccount();

        // 判断从哪个号码开始生成
        if(!StringUtils.isEmpty(maxAccount)){
            this.currentMaxAccount = Long.parseLong(maxAccount) + 1;
        }else{
            this.currentMaxAccount = 100L;
        }

        // 生成账号列表
        List<String> generateList = StringUtils.generateAccount(currentMaxAccount, batchSize);

        // 添加到队列
        accountPool.addAll(generateList);

        // 起始生成账号预先赋值
        currentMaxAccount = currentMaxAccount + batchSize + 1;
    }

    // 获取账号
    public String getAccount() {

        if(accountPool.isEmpty()){

            List<String> generateList = StringUtils.generateAccount(currentMaxAccount, batchSize);

            accountPool.addAll(generateList);

            currentMaxAccount = currentMaxAccount + batchSize + 1;
        }

        return accountPool.poll();
    }
}
