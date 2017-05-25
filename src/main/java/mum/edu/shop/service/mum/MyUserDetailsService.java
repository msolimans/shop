package mum.edu.shop.service.mum;


import java.util.*;

import mum.edu.shop.domain.Authority;
import mum.edu.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("usersDetailService")
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<mum.edu.shop.domain.User> user = userRepository.findOneByEmail(username);


		List<GrantedAuthority> authorities =
                	buildUserAuthority(user.get().getAuthorities());

		return buildUserForAuthentication(user.get(), authorities);
	}

	private User buildUserForAuthentication(mum.edu.shop.domain.User user,
                                            List<GrantedAuthority> authorities) {
			return new User(user.getEmail(), user.getPassword(),true, true, true, true, authorities);
		}

		private List<GrantedAuthority> buildUserAuthority(Set<Authority> userRoles) {

			Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

			// Build user's authorities
			for (Authority userRole : userRoles) {
				setAuths.add(new SimpleGrantedAuthority(userRole.getName()));
			}

			List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

			return Result;
		}

}
