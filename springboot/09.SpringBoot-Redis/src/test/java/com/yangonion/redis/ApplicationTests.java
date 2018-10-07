package com.yangonion.redis;

import com.yangonion.redis.bean.User;
import com.yangonion.redis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	public void test() throws  Exception {
		User user1 = userService.queryUserById(1);
		System.out.println("姓名："+user1.getName());

		User user2 = userService.queryUserById(1);
		System.out.println("姓名："+user2.getName());

	}

}
