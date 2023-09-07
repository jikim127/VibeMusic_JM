package com.example.vibemusic.config;

import com.example.vibemusic.security.CustomUserDetailsService;
import com.example.vibemusic.security.handler.Custom403Handler;
import com.example.vibemusic.security.handler.CustomSocialLoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

import javax.sql.DataSource;

@Configuration
@Log4j2
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class CustomSecurityConfig {

    private final DataSource dataSource;
    private final CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomSocialLoginSuccessHandler(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        log.info("-------------config--------인증, 인가권한 설정-------------");

        // 커스텀 로그인 기능
        http.formLogin().loginPage("/member/login").defaultSuccessUrl("/index",true);  //formLogin() --> 스프링부트가 제공하는 로그인창이 뜸
        // CSRF 비활성화 --> (Cross-Site Request Forgery 크로스 사이트 요청 위조)
        http.csrf().disable(); //login.html을 띄우기 위해선 비활성화를 안해놓으면 로그인이 안됨.

        http.rememberMe()
                .key("12345678")
                .tokenRepository(persistentTokenRepository())
                .userDetailsService(userDetailsService)
                .tokenValiditySeconds(60*60*24*30);
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());

        http.oauth2Login().loginPage("/member/login").successHandler(authenticationSuccessHandler());

        //로그아웃
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index")
                .deleteCookies("JSESSIONID", "remember-me");

        return http.build();
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer(){
//        return (web) -> web.ignoring().requestMatchers(PathRequest.
//                toStaticResources().atCommonLocations());
//    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new Custom403Handler();
    }



    public void configure(WebSecurity web) throws Exception{
        web.httpFirewall(defaultHttpFirewall());
    }

    @Bean
    public HttpFirewall defaultHttpFirewall() {
        return new DefaultHttpFirewall();
    }
}
