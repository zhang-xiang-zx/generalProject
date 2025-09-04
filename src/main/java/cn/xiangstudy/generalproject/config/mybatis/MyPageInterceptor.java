package cn.xiangstudy.generalproject.config.mybatis;

import cn.xiangstudy.generalproject.config.threadLocal.ContextKeys;
import cn.xiangstudy.generalproject.config.threadLocal.ContextManager;
import cn.xiangstudy.generalproject.pojo.utils.Page;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author zhangxiang
 * @date 2025-07-25 10:44
 */
@Intercepts({
        @Signature(type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class MyPageInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        // 判断是否有分页进行拦截
        Page page = ContextManager.get(ContextKeys.PAGE);

        if (page == null) {
            return invocation.proceed();
        }

        MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
        Object parameter = invocation.getArgs()[1];
        RowBounds rowBounds = (RowBounds) invocation.getArgs()[2];
        ResultHandler resultHandler = (ResultHandler) invocation.getArgs()[3];
        Executor executor = (Executor) invocation.getTarget();

        BoundSql boundSql = ms.getBoundSql(parameter);
        String originalSql = boundSql.getSql();

        String countSql = "select count(1) from (" + originalSql + ") tmp";
        Connection connection = ms.getConfiguration().getEnvironment().getDataSource().getConnection();
        long total = 0;
        try (PreparedStatement stmt = connection.prepareStatement(countSql)) {
            DefaultParameterHandler handler = new DefaultParameterHandler(ms, parameter, boundSql);
            handler.setParameters(stmt);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                total = rs.getLong(1);
            }
        }
        System.out.println("总数:" + total);

        int pageNum = page.getPageNum();
        int pageSize = page.getPageSize();
        int offset = (pageNum - 1) * page.getPageSize();

        // 2. 改写 SQL，加 LIMIT
        String pageSql = originalSql + " LIMIT " + offset + ", " + pageSize;
        setFieldValue(boundSql, "sql", pageSql);

        // 3. 执行查询（注意要带 CacheKey）
        CacheKey cacheKey = executor.createCacheKey(ms, parameter, rowBounds, boundSql);
        List<?> result = executor.query(ms, parameter, rowBounds, resultHandler, cacheKey, boundSql);

        page.setTotal(total);
        page.addAll(result);
        int pages = page.getPages();


        if (pageNum >= pages) {
            page.setHasNextPage(false);
        } else {
            page.setHasNextPage(true);
        }

        System.out.println(result.toString());

        ContextManager.remove(ContextKeys.PAGE);
        return page;
    }


    private void setFieldValue(Object obj, String fieldName, Object value) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

}
