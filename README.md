
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder)
	{
		UserDetails admin = User.withUsername("rkd")
				.password(encoder.encode("rkd1")).roles("ADMIN").build();
				
		UserDetails user = User.withUsername("abc")
				.password(encoder.encode("abc1")).roles("USER").build();
		
		return new InMemoryUserDetailsManager(admin,user);
	}
	
	  @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception {  
		  return http.csrf()
				  .disable().authorizeHttpRequests().requestMatchers("/test/m2").permitAll()
				  .and().authorizeHttpRequests().requestMatchers("/test/**").authenticated()
				  .and().formLogin() 
				  .and().build();  
	  }
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}

![Uploading image.png…]()
