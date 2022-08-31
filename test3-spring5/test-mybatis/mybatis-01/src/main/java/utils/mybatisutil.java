package utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

//sqlSession Factory 生产 sqlsession
public class mybatisutil {
    private static SqlSessionFactory sqlSessionFactory;//将原本的sqlSessionFactory提到这里，提升作用域
    static {
//        static初始的代码块，一开始就会加载
        InputStream inputStream = null;
        try {
            //获取sqlsession的对象，固定执行
            String resource = "mybatis-config.xml";
            inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();//这个工长就是生产各种的功能
    }
}
