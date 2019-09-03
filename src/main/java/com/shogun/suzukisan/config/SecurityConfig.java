package com.shogun.suzukisan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/css/**", "/images/**", "/js/**")
                .antMatchers("/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(
                        "/",
                        "/conversation"
                ).permitAll() // indexは全ユーザーアクセス許可 運用時は/conversationはアクセスできない様に
                .anyRequest().authenticated();  // それ以外は全て認証無しの場合アクセス不許可

        http.formLogin()
                .loginProcessingUrl("/login")//ログイン処理をするURL
//                .loginPage("/login")//ログイン画面のURL
                .failureUrl("/login?error")//認証失敗時のURL
                .successForwardUrl("/home")//認証成功時のURL
                .usernameParameter("email")//ユーザのパラメータ名
                .passwordParameter("password");//パスワードのパラメータ名

        http.logout()
                .logoutUrl("/logout**")//ログアウト時のURL（今回は未実装）
                .logoutSuccessUrl("/login");//ログアウト成功時のURL

//      TODO: xxs対策を一時的に外す
        http.cors().and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
            throws Exception
    {
        authenticationManagerBuilder.userDetailsService(this.userDetailsService);
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService)
    {
        this.userDetailsService = userDetailsService;
    }

}