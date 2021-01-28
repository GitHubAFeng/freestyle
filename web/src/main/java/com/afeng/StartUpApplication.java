package com.afeng;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 启动器
 * Profile多环境启动方案：
 * 1.直接运行jar,指定uat环境运行[java -jar -Dspring.profiles.active=test boot-simple.jar]
 * 2.Eclipse指定uat环境运行[在StartUpApplication.java启动环境的配置项中添加--spring.profiles.active=uat]
 * 3.直接在StartUpApplication主函数里面配置：
 * SpringApplication app = new SpringApplication(StartUpApplication.class);
 * app.setAdditionalProfiles("uat");   //default dev 或uat
 * app.run(args);
 */
@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class StartUpApplication extends SpringBootServletInitializer implements CommandLineRunner {


    @Autowired
    ConfigurableEnvironment env;

    @Autowired
    InitConfiguration init;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(StartUpApplication.class, args);
        //编码激活不同profile配置
//        SpringApplication app = new SpringApplication(StartUpApplication.class);
//        app.setAdditionalProfiles("dev");   //default dev 或uat
//        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        String activeProfiles = StringUtils.arrayToCommaDelimitedString(env.getActiveProfiles());
        activeProfiles = StringUtils.isEmpty(activeProfiles) ? "default" : activeProfiles;
        log.info("===========系统启动完成！" + "运行环境:[" + activeProfiles + "] IP:[" + init.getServerIp() + "] PORT:[" + init.getServerPort() + "]===========");
    }

    /*
     * 获取服务器启动参数
     */
    @Component
    public class InitConfiguration implements ApplicationListener<WebServerInitializedEvent> {
        private int serverPort;

        @Override
        public void onApplicationEvent(WebServerInitializedEvent event) {
            this.serverPort = event.getWebServer().getPort();
        }

        public int getServerPort() {
            return this.serverPort;
        }

        public String getServerIp() throws UnknownHostException {
            return InetAddress.getLocalHost().getHostAddress();
        }
    }

    /**
     * web容器中进行部署
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(StartUpApplication.class);
    }

}
