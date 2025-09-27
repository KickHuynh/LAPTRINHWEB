package vn.iostar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import vn.iostar.configs.MySiteMeshFilter;

@SpringBootApplication
public class DemoSpingboot1Application {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpingboot1Application.class, args);
	}
	
	@Bean
	public FilterRegistrationBean<MySiteMeshFilter> siteMeshFilter() {
	    FilterRegistrationBean<MySiteMeshFilter> filterRegistrationBean = new FilterRegistrationBean<MySiteMeshFilter>();
	    filterRegistrationBean.setFilter(new MySiteMeshFilter()); // adding sitemesh filter ??
	    filterRegistrationBean.addUrlPatterns("/*");
	    return filterRegistrationBean;
	}

}
