package com.yangonion.config;

import com.yangonion.config.model.GithubBean;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
//使用ConfigurationProperties时需要以下一句
@EnableConfigurationProperties(GithubBean.class)
public class ConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigApplication.class, args);
		//隐藏banner
		/*SpringApplication app = new SpringApplication(ConfigApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run();*/

		// 禁用 项目的配置被命令行修改 java -jar xxx.jar --server.port=9090,不能修改端口
		/*SpringApplication app = new SpringApplication(ConfigApplication.class);
		app.setAddCommandLineProperties(false);
		app.run();*/
	}
}
