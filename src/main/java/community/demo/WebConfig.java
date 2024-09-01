package community.demo;

import community.demo.exception.resolver.MyHandlerExceptionResolver;
import community.demo.exception.resolver.UserhandlerExceptionResolver;
import community.demo.typeconverter.converter.IntegerToStringConverter;
import community.demo.typeconverter.converter.StringToIntegerConverter;
import community.demo.web.argumentResolver.LoginMemberArgumentResolver;
import community.demo.web.filter.LogFilter;
import community.demo.web.filter.LoginCheckFilter;
import community.demo.web.interceptor.LogInterceptor;
import community.demo.web.interceptor.LoginCheckInterceptor;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(new StringToIntegerConverter());
//        registry.addConverter(new IntegerToStringConverter());
//    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(new MyHandlerExceptionResolver());
        resolvers.add(new UserhandlerExceptionResolver());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver());
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LogInterceptor())
//                .order(1)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/css/**", "/*.ico", "/error", "/error-page/**");
//
//        registry.addInterceptor(new LoginCheckInterceptor())
//                .order(2)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/css/**", "/*.ico", "/error", "/login", "/logout", "/join", "/", "/error-page/**");
//    }

//    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();

        filterRegistrationBean.setFilter(new LogFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");
//        filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ERROR);
        // 기본 값: REQUEST -> DispatcherTypes = REQUEST 일때만 필터 적용
        return filterRegistrationBean;
    }

//    @Bean
    public FilterRegistrationBean loginCheckFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();

        filterRegistrationBean.setFilter(new LoginCheckFilter());
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }


}
