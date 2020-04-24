package com.iessanvicente.microservicios.app.respuestas.models.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iessanvicente.microservicios.app.respuestas.models.entities.Respuesta;
import com.iessanvicente.microservicios.app.respuestas.models.repositories.IRespuestaRepository;

@Service
public class RespuestaServiceImpl implements IRespuestaService{
	@Autowired
	private IRespuestaRepository repository;
	//@Autowired
	//private ExamenFeignClient examenFeignClient;
	@Override
	public List<Respuesta> saveAll(List<Respuesta> respuestas) {
		return (List<Respuesta>) repository.saveAll(respuestas);
	}
	
	@Override
	public List<Respuesta> findByAlumnoByExamen(Long alumnoId, Long examenId) {
		/*Examen examen = examenFeignClient.obtenerExamenPorId(examenId);
		Set<Pregunta> preguntas = examen.getPreguntas();
		List<Long> preguntaIds = 
				preguntas
				.stream()
				.map(p -> p.getId())
				.collect(Collectors.toList());
		List<Respuesta> respuestas = 
				repository.findRespuestaByAlumnoByPreguntaIds(alumnoId, preguntaIds);
		respuestas = respuestas.stream()
				.map(r -> {
					preguntas.forEach(p ->{
						if(p.getId() == r.getPreguntaId()) {
							r.setPregunta(p);
						}
					});
					return r;
				}).collect(Collectors.toList());
		return respuestas;
		 */
		return repository.findRespuestaByAlumnoByExamen(alumnoId, examenId);
		
	}
	@Override
	public List<Long> findExamenesIdConRespuestasByAlumnoId(Long alumnoId) {
		/*List<Respuesta> respuestaAlumno = repository.findByAlumnoId(alumnoId);
		List<Long> examenIds = Collections.emptyList();
		if(!respuestaAlumno.isEmpty()) {
			List<Long> preguntaIds = respuestaAlumno.stream()
					.map(p -> p.getPreguntaId())
					.collect(Collectors.toList());
			examenIds = examenFeignClient
					.obtenerExamenesIdsConRespuestasPorPreguntasIds(preguntaIds);
		}
		*/
		List<Respuesta> respuestas = repository.findExamenesIdConRespuestaByAlumno(alumnoId);
		return respuestas.stream()
				.map(r -> r.getPregunta().getExamen().getId())
				.distinct()
				.collect(Collectors.toList());
	}

	@Override
	public List<Respuesta> findByAlumnoId(Long alumnoId) {
		return repository.findByAlumnoId(alumnoId);
	}

}
