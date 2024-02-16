package com.dolphin.quilometragem.resources.exceptions;

import java.lang.reflect.InaccessibleObjectException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dolphin.quilometragem.services.exceptions.InvalidAssociation;
import com.dolphin.quilometragem.services.exceptions.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice //Responsavel por tratar possivel erros nas requisições
public class ResourceExceptionHandler {

	@ExceptionHandler( ObjectNotFoundException.class )
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND; //404 Não encontrado
		StandardError err = new StandardError(
				System.currentTimeMillis(),
				status.value(),
				"Não encontrado",
				e.getMessage(),
				request.getRequestURI()
				);
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler( InaccessibleObjectException.class )
	public ResponseEntity<StandardError> inaccessibleObject(InaccessibleObjectException e, HttpServletRequest request){
		
		HttpStatus status = HttpStatus.CONFLICT; //404 Não encontrado
		StandardError err = new StandardError(
				System.currentTimeMillis(),
				status.value(),
				"Não pode deletar registro contendo associações!",
				e.getMessage(),
				request.getRequestURI()
				);
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler( InvalidAssociation.class )
	public ResponseEntity<StandardError> invalidAssociation(InvalidAssociation e, HttpServletRequest request){
		HttpStatus status = HttpStatus.IM_USED;
		StandardError err = new StandardError(
				System.currentTimeMillis(),
				status.value(),
				"Erro ao fazer a associação",
				e.getMessage(),
				request.getRequestURI()
				);
		
		return ResponseEntity.status(status).body(err);
	}
	
}
