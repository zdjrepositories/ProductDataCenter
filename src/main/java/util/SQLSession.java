package util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.io.InputStream;

public class SQLSession {
    static private InputStream is;
    static private SqlSessionFactory factory;
    private SqlSession session;
    public SqlSession getSqlsession()  {
        if (is==null||factory==null || session==null) {
            try {
                is = Resources.getResourceAsStream("mybatis");
                factory = new SqlSessionFactoryBuilder().build(is);
                session = factory.openSession();
            } catch (IOException e) {
                new Mail().sendMail("数据中心项目f发生异常\r\n数据库配置文件异常："+e.toString());
                Log4j.getLog4j().error("数据库配置文件异常："+e.toString());
                e.printStackTrace();
            }

        }
        return session;
    }
    public void closeSession(){
        if (is==null||factory==null || session==null) {
            session.close();
        }
    }
}
