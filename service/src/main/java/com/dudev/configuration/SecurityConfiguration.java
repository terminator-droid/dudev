package com.dudev.configuration;

import com.dudev.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Set;

import static com.dudev.entity.Role.ADMIN;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
//                .csrf().disable()
                .authorizeHttpRequests(
                        urlConfig -> urlConfig
                                .antMatchers(HttpMethod.POST, "/users").permitAll()
                                .antMatchers("/admin/**").hasAuthority(ADMIN.getAuthority())
                                .antMatchers("/login", "/users/registration", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                                .antMatchers("/users/{\\d+}/delete").hasAuthority(ADMIN.getAuthority())
                                .anyRequest()
                                .authenticated()
                )
//                .httpBasic(Customizer.withDefaults())
                .logout(logout -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .deleteCookies("JSESSIONID"))
                .formLogin(login -> login
                        .loginPage("/login")
                        .successForwardUrl("/users"))
                .oauth2Login(config -> config
                        .loginPage("/login")
                        .defaultSuccessUrl("/users")
                        .userInfoEndpoint(userInfo -> userInfo.oidcUserService(oidcUserService()))).build();
    }

    private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
        return  userRequest -> {
            String email = userRequest.getIdToken().getClaim("email");
            UserDetails user = userService.loadUserByUsername(email);
            // TODO: 21.04.2023 create user
            DefaultOidcUser defaultOidcUser = new DefaultOidcUser(user.getAuthorities(), userRequest.getIdToken());

            Set<Method> userDetailsMethods = Set.of(UserDetails.class.getDeclaredMethods());

            return (OidcUser) Proxy.newProxyInstance(SecurityConfiguration.class.getClassLoader(), new Class[]{UserDetails.class, OidcUser.class},
                    (proxy, method, args) -> userDetailsMethods.contains(method)
                                            ? method.invoke(user)
                                            : method.invoke(defaultOidcUser));
        };
    }
}
