package com.shogun.suzukisan.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("conversation").permitAll() // indexは全ユーザーアクセス許可
                .anyRequest().authenticated();  // それ以外は全て認証無しの場合アクセス不許可

        http.formLogin()
                .loginProcessingUrl("/login")//ログイン処理をするURL
                .loginPage("/loginFrom")//ログイン画面のURL
                .failureUrl("/login?error")//認証失敗時のURL
                .successForwardUrl("/success")//認証成功時のURL
                .usernameParameter("email")//ユーザのパラメータ名
                .passwordParameter("password");//パスワードのパラメータ名

        http.logout()
                .logoutUrl("/logout**")//ログアウト時のURL（今回は未実装）
                .logoutSuccessUrl("/login");//ログアウト成功時のURL

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/*.html", "/*.css")
                .antMatchers("/webjars/**");
    }

}