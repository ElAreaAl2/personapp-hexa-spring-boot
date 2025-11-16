package co.edu.javeriana.as.personapp.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.javeriana.as.personapp.application.port.in.PersonInputPort;
import co.edu.javeriana.as.personapp.application.port.in.ProfessionInputPort;
import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.model.request.EstudioRequest;
import co.edu.javeriana.as.personapp.model.response.EstudioResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Mapper
public class EstudioMapperRest {
	
	@Autowired
	private PersonInputPort personInputPort;
	
	@Autowired
	private ProfessionInputPort professionInputPort;
	
	public EstudioResponse fromDomainToAdapterRestMaria(Study study) {
		return fromDomainToAdapterRest(study, "MariaDB");
	}
	
	public EstudioResponse fromDomainToAdapterRestMongo(Study study) {
		return fromDomainToAdapterRest(study, "MongoDB");
	}
	
	public EstudioResponse fromDomainToAdapterRest(Study study, String database) {
		return new EstudioResponse(
				study.getProfession() != null ? study.getProfession().getIdentification()+"" : "",
				study.getPerson() != null ? study.getPerson().getIdentification()+"" : "",
				study.getGraduationDate() != null ? study.getGraduationDate().toString() : "",
				study.getUniversityName(),
				database,
				"OK");
	}

	public Study fromAdapterToDomain(EstudioRequest request) {
		try {
			Person person = personInputPort.findOne(Integer.parseInt(request.getPersonId()));
			Profession profession = professionInputPort.findOne(Integer.parseInt(request.getProfessionId()));
			LocalDate graduationDate = null;
			if (request.getGraduationDate() != null && !request.getGraduationDate().isEmpty()) {
				graduationDate = LocalDate.parse(request.getGraduationDate(), DateTimeFormatter.ISO_LOCAL_DATE);
			}
			return new Study(
					person,
					profession,
					graduationDate,
					request.getUniversityName()
			);
		} catch (NoExistException e) {
			log.warn("Person or Profession not found");
			return new Study(null, null, null, request.getUniversityName());
		}
	}
}
