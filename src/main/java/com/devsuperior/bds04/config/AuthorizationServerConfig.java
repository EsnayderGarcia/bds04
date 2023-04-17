package com.devsuperior.bds04.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer /*Essa anotação irá fazer com que esta classe represente o nosso
 							AuthorizationServer do Outh*/
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtAccessTokenConverter accessTokenConverter;
	
	@Autowired
	private JwtTokenStore tokenStore;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Value("${security.oauth2.client.client-id}")
	private String clientId;
	
	@Value("${security.oauth2.client.client-secret}")
	private String clientSecret;
	
	@Value("${jwt.duration}")
	private int tokenDuration;
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	/*Nesse método, definimos como será feita nossa autenticação, e configuramos as credenciais da aplicação*/
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
		.withClient(clientId)
		.secret(passwordEncoder.encode(clientSecret)) /*Senha da Aplicação*/
		.scopes("read", "write")
		.authorizedGrantTypes("password")
		.accessTokenValiditySeconds(tokenDuration);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager)
		.tokenStore(tokenStore)                          /**/
		.accessTokenConverter(accessTokenConverter);		/*Esses objetos irão processar o token*/
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
