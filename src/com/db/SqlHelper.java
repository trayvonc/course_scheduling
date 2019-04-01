/*
 * 对数据库操作的类
 * 对数据库的操作，就是crud
 * 调用存储过程
 * 
 *注意：如果连接数据库时出现如下异常则表示未引入三个JAR驱动包，另外一个原因就是SQL语句有语法错误
 *java.lang.ClassNotFoundException: com.microsoft.jdbc.sqlserver.SQLServerDviver
 * */
package com.db;
import java.sql.*;
public class SqlHelper {
	//定义需要的对象
	Connection ct=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	String driver="com.mysql.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/time_table?characterEncoding=utf8&useSSL=false";
	String user="root";
	String passwd="123123";
	int sum=0;
	//构造函数，初始化ct
	public SqlHelper()
	{
		try {
			//加载驱动
			Class.forName(driver);
                        System.out.println("Connecting to a selected database...");
			//得到连接
			ct=DriverManager.getConnection(url,user,passwd);
                        System.out.println("Connected database successfully!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//[]paras，通过?赋值方式可以防止漏洞注入方式，保证安全性
	public ResultSet query(String sql,String []paras)
	{
		try {
			ps=ct.prepareStatement(sql);
			//对sql的参数赋值
			for(int i=0;i<paras.length;i++)
			{
				ps.setString(i+1, paras[i]);
			}
			//执行查询
			rs=ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		//返回结果集
		return rs;
	}
        public int queryExecute(String sql)
	{
		try {
			ps=ct.prepareStatement(sql);
			
			rs=ps.executeQuery();
			if(rs.next())
			{
				sum=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		//返回结果集
		return sum;
	}
        public void Delete(String sql)
	{
		try {
	      Statement stmt=ct.createStatement();//创建Statement对象
              stmt.executeUpdate(sql);//执行sql语句
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public boolean updateExecete(String sql,String []params)
	{
            //System.out.println("com.db.SqlHelper.updateExecete()");
		boolean b=true;
		try {
			ps=ct.prepareStatement(sql);
			//对sql的参数赋值
			for(int i=0;i<params.length;i++)
			{
				ps.setString(i+1, params[i]);
			}
			//执行查询
			if(ps.executeUpdate()!=1)
			{
				b=false;
			}
		} catch (Exception e) {
			b=false;
			e.printStackTrace();
			// TODO: handle exception
		}
		finally
		{
			this.close();
		}
		//返回结果集
		return b;
		
	}
	//关闭资源的方法
	public void close()
	{
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(ct!=null) ct.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
