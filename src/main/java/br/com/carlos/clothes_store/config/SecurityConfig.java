package br.com.carlos.clothes_store.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.carlos.clothes_store.core.enums.TipoUsuario;

@Configuration
public class SecurityConfig {

    private UserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder;

    @Value("${br.com.carlos.clothes_store.rememberMe.key}")
    private String rememberMeKey;

    @Value("${br.com.carlos.clothes_store.rememberMe.validitySeconds}")
    private int rememberMevaliditySeconds;

    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/admin/login", "/admin/logout").permitAll()
                .requestMatchers("/admin/**").hasAuthority(TipoUsuario.ADMIN.toString())
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/admin/login")
                .usernameParameter("email")
                .passwordParameter("senha")
                .defaultSuccessUrl("/admin/servico")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout", "GET"))
                .logoutSuccessUrl("/admin/login")
            )
            .rememberMe(rememberMe -> rememberMe
                .rememberMeParameter("lembrar-me")
                .tokenValiditySeconds(rememberMevaliditySeconds)
                .key(rememberMeKey)
            );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
            .requestMatchers("/webjars/**", "/css/**", "/js/**", "/img/**", "/favicon.ico");
    }
}
