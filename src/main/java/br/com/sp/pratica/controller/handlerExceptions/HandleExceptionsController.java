package br.com.sp.pratica.controller.handlerExceptions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.sp.pratica.controller.handlerExceptions.body.Campo;
import br.com.sp.pratica.controller.handlerExceptions.body.RespostaException;
import br.com.sp.pratica.services.exceptions.ToDoListNotFoundException;
import br.com.sp.pratica.services.exceptions.UserNotFoundException;

@RestControllerAdvice
public class HandleExceptionsController extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Campo> listaDeErros = new ArrayList<>();
		List<FieldError> todosOsErros = ex.getBindingResult().getFieldErrors();
		
		todosOsErros.forEach(error -> {
			String nome = error.getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			listaDeErros.add(new Campo(nome, mensagem));
		});
		
		RespostaException respostaException = new RespostaException(status, listaDeErros);
		
		return handleExceptionInternal(ex, respostaException, headers, status, request);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<RespostaException> UserNotFoundException(UserNotFoundException ex, HttpServletRequest requestPath) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		RespostaException exception = new RespostaException(ex.getMessage(), status.value(), requestPath);
		
		return ResponseEntity.status(status).body(exception);
	}
	
	@ExceptionHandler(ToDoListNotFoundException.class)
	public ResponseEntity<RespostaException> ToDoListNotFoundException(ToDoListNotFoundException ex, HttpServletRequest requestPath) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		RespostaException exception = new RespostaException(ex.getMessage(), status.value(), requestPath);
		
		return ResponseEntity.status(status).body(exception);
	}

}
