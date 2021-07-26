package com.cheng.core.config;

import com.cheng.core.security.filter.JwtAuthenticationTokenFilter;
import com.cheng.core.security.handle.AuthenticationEntryPointImpl;
import com.cheng.core.security.handle.LogoutSuccessHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.CorsFilter;

@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 自定義使用者認證邏輯
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 跨網域處理
     */
    @Autowired
    private CorsFilter corsFilter;

    /**
     * 認證失敗處理
     */
    @Autowired
    private AuthenticationEntryPointImpl unauthenticated;

    /**
     * 使用者登出處理
     */
    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;

    /**
     * Token認證過濾
     */
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    /**
     * anyRequest          |   所有請求路徑
     * access              |   SpringEl表達式結果為true時可以訪問
     * anonymous           |   匿名可以訪問
     * denyAll             |   不能訪問
     * fullyAuthenticated  |   完全認證可以訪問（非remember-me自動登入）
     * hasAnyAuthority     |   如果有參數，參數表示權限，則其中任何一個權限可以訪問
     * hasAnyRole          |   如果有參數，參數表示角色，則其中任何一個角色可以訪問
     * hasAuthority        |   如果有參數，參數表示權限，則其權限可以訪問
     * hasIpAddress        |   如果有參數，參數表示IP位置，如果使用者IP和參數符合，則可以訪問
     * hasRole             |   如果有參數，參數表示角色，則其角色可以訪問
     * permitAll           |   使用者允許訪問
     * rememberMe          |   允許通過remember-me登入的使用者訪問
     * authenticated       |   使用者登入後可訪問
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // 不使用Session，關閉CSRF
                // 認證失敗處理
                .exceptionHandling().authenticationEntryPoint(unauthenticated).and()
                // 使用Token，不使用Session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 過濾請求
                .authorizeRequests()
                // 登入和驗證碼顯示允許任何人訪問
                .antMatchers("/login", "/captchaImage").anonymous()
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/profile/**"
                ).permitAll()
                // 除了以上的路徑不用認證，其他請求都要認證
                .anyRequest().authenticated()
                .and()
                .headers().frameOptions().disable();
        http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
        // 新增JWT Filter
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // 新增CORS Filter
        http.addFilterBefore(corsFilter, JwtAuthenticationTokenFilter.class);
        http.addFilterBefore(corsFilter, LogoutFilter.class);
    }

    /**
     * 身份認證
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    /**
     * Bcrypt加密
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 解決無法注入AuthenticationManager問題
     *
     * @return AuthenticationManager
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }
}
