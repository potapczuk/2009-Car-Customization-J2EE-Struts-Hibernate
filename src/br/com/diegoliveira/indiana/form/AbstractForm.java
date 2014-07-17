package br.com.diegoliveira.indiana.form;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;

import org.apache.struts.action.ActionForm;

/**
 * Classe Form abstrato com métodos genéricos
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class AbstractForm extends ActionForm implements Serializable {
	public static final String FORMATODATA = "dd/MM/yyyy";

    /**
     * Método que diz se uma String está vazia
     */
	protected boolean stringVazia(String valor) {
		return valor == null || valor.trim().length() == 0;
	}

    /**
     * Método que diz se uma data em String está no formato correto
     */
	protected boolean dataErrada(String data) {
		if (!stringVazia(data)) {
			return !data.matches("(\\d\\d)/(\\d\\d)/([12]\\d\\d\\d)");
		}
		return false;
	}

    /**
     * Método que diz se uma String contendo um int é inválido
     */
	protected boolean intInvalido(String data) {
		if (!stringVazia(data)) {
			return !data.matches("\\d+");
		}
		return false;
	}

    /**
     * Método que diz se uma String contendo um double é inválido
     */
	protected boolean doubleInvalido(String data) {
		if (!stringVazia(data)) {
			return !data.matches("\\d+[.]?\\d*");
		}
		return false;
	}

    /**
     * Método que diz se uma String contendo um corrency é inválido
     */
	protected boolean currencyInvalido(String data) {
		if (!stringVazia(data)) {
			System.out.println(data);
			String regex = "([0-9]?[0-9]\\.[0-9][0-9][0-9]\\.[0-9][0-9][0-9]|[0-9]?[0-9]?[0-9]\\.[0-9][0-9][0-9]|[0-9]?[0-9]?[0-9])([,][0-9][0-9])?";
			return !data.matches(regex);
		}
		return false;
	}	

    /**
     * Método que diz se uma String contendo um CEP é inválido
     */
	protected boolean cepInvalido(String data) {
		if (!stringVazia(data)) {
			return !data.matches("\\d(5)-\\d(3)");
		}
		return false;
	}

    /**
     * Método que diz se uma String contendo um telefone é inválido
     */
	protected boolean telefoneErrado(String telefone) {
		if (!stringVazia(telefone)) {
			return !telefone.matches("[(](\\d\\d)[)][ ](\\d\\d\\d\\d)-(\\d\\d\\d\\d)");
		}
		return false;
	}

    /**
     * Método que converte uma String contendo uma data em um Date
     */
	protected Date converterStringParaDate(String dataStr, String formatacao){
		java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat(formatacao);
		java.util.Date data = null;  
		
		try {  
			data = formato.parse(dataStr);
			return data;
		} catch (java.text.ParseException e) {  
			e.printStackTrace();  
		}
		return null;
	}

    /**
     * Método que converte um Date em String
     */
	public String converterDateParaString(Date data, String formatacao){
		java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat(formatacao);  

		return formato.format(data);
	}

    /**
     * Método que converte um double para Currency
     */
	public String converteDoubleParaCurrency(String numero){
		DecimalFormat myformat = new DecimalFormat("###,###,##0.00");
		myformat.setMinimumFractionDigits(2);  
		double valor = Double.parseDouble(numero);
		
		return myformat.format(valor);
	}

    /**
     * Método que converte um Currency para Double
     */
	public String converteCurrencyParaDouble(String numero) throws ParseException{
        numero = numero.replaceAll("[.]", "");
        numero = numero.replaceAll("[,]", ".");
		
		double valor = Double.parseDouble(numero);
		return String.valueOf(valor);
	}
}