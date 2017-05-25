package mum.edu.shop.service.mum;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import mum.edu.shop.domain.Address;
import mum.edu.shop.domain.Authority;
import mum.edu.shop.domain.Person;
import mum.edu.shop.domain.User;
import mum.edu.shop.repository.PersonRepository;
import mum.edu.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service("usersService")
@Transactional
public class UserService {

	private static final String DEFAULT_PHONE = "(123) 789-1234";

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PersonRepository personRepository;

	public User save(User users) {
		users.setActivated(true);
		users.setPassword(encodePassword(users.getPassword()));

		Set<Authority> authorities = new HashSet<>();
		Authority authority = new Authority();
		authority.setName("ROLE_USER");


//		authority.setUser(users);

		authorities.add(authority);
		users.setAuthorities(authorities);

		Person person = createPersonByUser(users);
		personRepository.save(person);
		return userRepository.save(users);
	}

	public User findUser(String username) {
        Optional<User> user =  userRepository.findOneByEmail(username);
        if(user.isPresent())
            return user.get();
        return null;

	}

	public String encodePassword(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}

	private Person createPersonByUser(User users) {
		Person person = new Person();
		person.setEmail(users.getEmail());
		person.setEnable(true);
		person.setFirstName(users.getFirstName());
		person.setLastName(users.getLastName());
		person.setPhone(DEFAULT_PHONE);
		person.setAddress(new Address("FairField", "IA", "US", "52766"));
		return person;
	}
}
