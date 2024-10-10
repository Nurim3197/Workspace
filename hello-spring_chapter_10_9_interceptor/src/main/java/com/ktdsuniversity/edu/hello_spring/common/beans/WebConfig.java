package com.ktdsuniversity.edu.hello_spring.common.beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ktdsuniversity.edu.hello_spring.access.dao.AccessLogDao;

// application.yml에서 설정하지 못하는 디테일한 설정을 위한 annotation
@Configuration
// Spring WebMVC에 필요한 다양한 요소를 활성화 시키는 annotation
//	  - Spring Validator
//	  - Spring Inteceptor
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
	
	
	/**
	 * Auto DI가 필요한 경우 @Component
	 * Manual DI가 필요한 경우 @Bean
	 *  ->> 객체 생성을 스프링이 아닌 개발자가 직접하는 것!
	 * @return
	 */
	@Bean
	Sha createShaInstance() {
		Sha sha = new Sha();
		return sha;
	}
	
	@Autowired
	AccessLogDao accessLogDao;
	
	@Value("${app.interceptors.check-session.path-patterns}")
	private List<String> checkSessionPathPatterns;
	
	@Value("${app.interceptors.check-session.exclude-path-patterns}")
	private List<String> checkSessionExcludePathPatterns;
	
	@Value("${app.interceptors.check-dup-login.path-patterns}")
	private List<String> checkDupLoginPathPatterns;
	
	@Value("${app.interceptors.check-dup-login.exclude-path-patterns}")
	private List<String> checkDupLoginExcludePathPatterns;
	
	@Value("${app.interceptors.add-access-log.path-patterns}")
	private List<String> addAccessLogPathPatterns;
	
	@Value("${app.interceptors.add-access-log.exclude-path-patterns}")
	private List<String> addAccessLogExcludePathPatterns;
	
	/**
	 * JSP View Resolver 설정
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
	
	/**
	 * Static Resource 설정
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**") // http://localhost:8080/css/common/common.css
				.addResourceLocations("classpath:static/css/");
		registry.addResourceHandler("/js/**") // http://localhost:8080/hs/jquery/jquery-3.1.7.min.js
				.addResourceLocations("classpath:/static/js/");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		List<String> excludeCheckSessionInterceptorURL = new ArrayList<>();
		excludeCheckSessionInterceptorURL.add("/js/**");
		excludeCheckSessionInterceptorURL.add("/css/**");
		excludeCheckSessionInterceptorURL.add("/image/**");
		excludeCheckSessionInterceptorURL.add("/member/login");
		excludeCheckSessionInterceptorURL.add("/member/regist/**");
		
		// First Interceptor
		registry.addInterceptor(new CheckSessionInterceptor())
				.addPathPatterns(this.checkSessionPathPatterns)
				.excludePathPatterns(this.checkSessionExcludePathPatterns);
		
		// Second Interceptor
		List<String> excludeCheckDuplicateLoginInterceptorURList = new ArrayList<>();
		excludeCheckDuplicateLoginInterceptorURList.add("/member/regist/**");
		excludeCheckDuplicateLoginInterceptorURList.add("/member/login");
		
		registry.addInterceptor(new CheckDuplicateLoginInterceptor())
				.addPathPatterns(this.checkDupLoginPathPatterns)
				.excludePathPatterns(this.checkDupLoginExcludePathPatterns);
		
		// Third Interceptor
		registry.addInterceptor(new addAccessLogHistoryInterceptor(this.accessLogDao))
				.addPathPatterns(this.addAccessLogPathPatterns)
				.addPathPatterns(this.addAccessLogExcludePathPatterns);
		
	}
}
