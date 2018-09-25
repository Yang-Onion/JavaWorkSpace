package com.yangonion.jdbcTemplate.dao.impl;

import com.yangonion.jdbcTemplate.bean.User;
import com.yangonion.jdbcTemplate.dao.UserDao;
import com.yangonion.jdbcTemplate.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;
import java.util.Map;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(User user) {
       String sql = "insert into user(id,name,age) values(:id,:name,:age)";
       NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(this.jdbcTemplate.getDataSource());
       return npjt.update(sql,new BeanPropertySqlParameterSource(user));
    }

    @Override
    public int update(User user) {
        String sql = "update user set name=?,age=? where id=?";
        Object[] args ={user.getName(),user.getAge(),user.getId()};
        int[] argTypes = {Types.VARCHAR,Types.INTEGER,Types.BIGINT};
        return this.jdbcTemplate.update(sql,args,argTypes);
    }

    @Override
    public int deleteById(Long id) {
        String sql = "delete from user  where id=?";
        Object[] args ={id};
        int[] argTypes = {Types.BIGINT};
        return this.jdbcTemplate.update(sql,args,argTypes);
    }

    @Override
    public List<Map<String, Object>> queryUserListMap() {
       String sql ="select * from user";
       return  this.jdbcTemplate.queryForList(sql);

    }

    @Override
    public User queryUserById(Long id) {
        User user=null;
        String sql ="select * from user where id=?";
        Object[] args ={id};
        int[] argTypes = {Types.BIGINT};
        List<User> userList = this.jdbcTemplate.query(sql,args,argTypes,new UserMapper() );
        if (userList !=null &&userList.size()>0){
            user= userList.get(0);
        }
        return  user;
    }
}
