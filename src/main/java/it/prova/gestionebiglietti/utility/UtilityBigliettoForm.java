package it.prova.gestionebiglietti.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import it.prova.gestionebiglietti.model.Biglietto;

public class UtilityBigliettoForm {

	public static Biglietto createBigliettoFromParams(String provenienzaInputParam, String destinazioneInputParam,
			String dataStringParam, String prezzoInputStringParam) {

		Biglietto result = new Biglietto(provenienzaInputParam, destinazioneInputParam);

		result.setData(parseDateFromString(dataStringParam));

		if (NumberUtils.isCreatable(prezzoInputStringParam)) {
			result.setPrezzo(Integer.parseInt(prezzoInputStringParam));
		}

		return result;
	}

	public static boolean validateBigliettoBean(Biglietto bigliettoToBeValidated) {
		if (StringUtils.isBlank(bigliettoToBeValidated.getProvenienza())
				|| StringUtils.isBlank(bigliettoToBeValidated.getDestinazione())
				|| bigliettoToBeValidated.getData() == null || bigliettoToBeValidated.getPrezzo() == null
				|| bigliettoToBeValidated.getPrezzo() < 1) {
			return false;
		}
		return true;
	}

	public static Date parseDateFromString(String dataStringParam) {
		if (StringUtils.isBlank(dataStringParam))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dataStringParam);
		} catch (ParseException e) {
			return null;
		}
	}

}
