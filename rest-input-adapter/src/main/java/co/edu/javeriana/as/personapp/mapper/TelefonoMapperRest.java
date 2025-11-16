package co.edu.javeriana.as.personapp.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.javeriana.as.personapp.application.port.in.PersonInputPort;
import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.model.request.TelefonoRequest;
import co.edu.javeriana.as.personapp.model.response.TelefonoResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Mapper
public class TelefonoMapperRest {
	
	@Autowired
	private PersonInputPort personInputPort;
	
	public TelefonoResponse fromDomainToAdapterRestMaria(Phone phone) {
		return fromDomainToAdapterRest(phone, "MariaDB");
	}
	
	public TelefonoResponse fromDomainToAdapterRestMongo(Phone phone) {
		return fromDomainToAdapterRest(phone, "MongoDB");
	}
	
	public TelefonoResponse fromDomainToAdapterRest(Phone phone, String database) {
		return new TelefonoResponse(
				phone.getNumber(), 
				phone.getCompany(), 
				phone.getOwner() != null ? phone.getOwner().getIdentification()+"" : "", 
				database,
				"OK");
	}

	public Phone fromAdapterToDomain(TelefonoRequest request) {
		try {
			Person owner = personInputPort.findOne(Integer.parseInt(request.getOwnerId()));
			return new Phone(
					request.getNumber(),
					request.getCompany(),
					owner
			);
		} catch (NoExistException e) {
			log.warn("Owner not found: " + request.getOwnerId());
			return new Phone(request.getNumber(), request.getCompany(), null);
		}
	}
}
