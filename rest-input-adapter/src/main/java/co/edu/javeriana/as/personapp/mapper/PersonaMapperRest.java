package co.edu.javeriana.as.personapp.mapper;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Gender;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.model.request.PersonaRequest;
import co.edu.javeriana.as.personapp.model.response.PersonaResponse;

@Mapper
public class PersonaMapperRest {
	
	public PersonaResponse fromDomainToAdapterRestMaria(Person person) {
		return fromDomainToAdapterRest(person, "MariaDB");
	}
	public PersonaResponse fromDomainToAdapterRestMongo(Person person) {
		return fromDomainToAdapterRest(person, "MongoDB");
	}
	
	public PersonaResponse fromDomainToAdapterRest(Person person, String database) {
		return new PersonaResponse(
				person.getIdentification()+"", 
				person.getFirstName(), 
				person.getLastName(), 
				person.getAge()+"", 
				person.getGender().toString(), 
				database,
				"OK");
	}

	public Person fromAdapterToDomain(PersonaRequest request) {
		return new Person(
				Integer.parseInt(request.getDni()),
				request.getFirstName(),
				request.getLastName(),
				parseGender(request.getSex()),
				parseAge(request.getAge()),
				null,
				null
		);
	}
	
	private Gender parseGender(String sex) {
		if (sex == null || sex.isEmpty()) {
			return Gender.OTHER;
		}
		switch (sex.toUpperCase()) {
			case "M":
			case "MALE":
				return Gender.MALE;
			case "F":
			case "FEMALE":
				return Gender.FEMALE;
			default:
				return Gender.OTHER;
		}
	}
	
	private Integer parseAge(String age) {
		try {
			return Integer.parseInt(age);
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
