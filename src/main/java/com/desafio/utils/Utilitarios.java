package com.desafio.utils;

import br.com.caelum.stella.validation.CPFValidator;

public class Utilitarios {
	
	public static boolean validaCpf(String cpf) { 
	    CPFValidator cpfValidator = new CPFValidator(); 
	    try{ 
	    	cpfValidator.assertValid(cpf); 
	    	return true; 
	    }catch(Exception e){
	        e.printStackTrace(); 
	        return false; 
	    }
	}

}
