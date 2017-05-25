package mum.edu.shop.service.mum;

import java.util.List;

import mum.edu.shop.domain.Person;
import mum.edu.shop.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public Person savePerson(Person person) {
		return personRepository.save(person);
	}

	public List<Person> findByEmail(String email) {
		return personRepository.findByEmail(email);
	}

	public Person findById(long id) {
		return personRepository.findOne(id);
	}

	public void removePerson(Person person) {
		personRepository.delete(person);
	}

	public List<Person> getAllPerson() {
		return personRepository.findAll();
	}

	public List<Person> SaveOrUpdatePersonAPI(Person person) {
		savePerson(person);
		return getAllPerson();
	}

}
