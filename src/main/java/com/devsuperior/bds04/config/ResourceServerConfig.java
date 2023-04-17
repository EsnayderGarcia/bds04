package com.devsuperior.bds04.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    /*No ResourceServer liberamos os endpoints de acordo com as roles*/
    @Autowired
    private JwtTokenStore tokenStore;

    @Autowired
    private Environment env;

    private static final String[] PUBLIC = {"/oauth/token", "/h2-console/**"};
    private static final String[] CLIENT_OR_ADMIN = {"/cities/**", "/events/**"};

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore); /*Esta configuração permitirá ao ResourceServer decodificar o Token e assim analisar a validade das informações passadas.*/
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        /*Configuração para liberar os frames do banco de dados h2-console*/
        if (Arrays.asList(env.getActiveProfiles()).contains("test"))
            http.headers().frameOptions().disable();

        http.authorizeRequests() /*antMatchers define as autorizações*/
                .antMatchers(PUBLIC).permitAll()
                .antMatchers(HttpMethod.GET,CLIENT_OR_ADMIN).permitAll()
                .antMatchers(HttpMethod.POST, "/events/**").hasAnyRole("CLIENT", "ADMIN")
                .anyRequest().hasRole("ADMIN");
    }
}
