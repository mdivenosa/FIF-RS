package fif.rs.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import fif_core.*;
import fif_core.exceptions.MetadataWithSameAttributeException;
import fif_core.interfaces.Aggregator;

/**
 * Util class for integration of the RESTful Web Service with the fif-core package.
 * 
 * @author Mariagiovanna Di Venosa
 * @version 1.0, release 06 April 2017
 * @see fif-core package
 */
public class FifCoreIntegrationUtil {
	
	/**
	 * Provides a anchor to the filtering function of fif-core, decoupling
	 * Web Service from the core package.
	 * 
	 * @param rj the request with the resource to filter and the filter identifier
	 * @return a double value between 0 and 1.
	 * @throws Exception
	 * 
	 */
	public static double doFilter(RequestJ rj) throws Exception{
		
		if (rj!=null) {
			Filter filter = getFilter(rj);
			Resource resource = getResource(rj);
			Descriptor descriptor = getDescriptor(rj);
			ResourceRegister rr = ResourceRegister.getinstance();
			rr.associateDescriptor(resource, descriptor);
			return filter.doFilter(resource);
		} else {
			return -1;
		}
	}
	
	/*
	 * Retrieves a Descriptor from the input request.
	 */
	private static Descriptor getDescriptor(RequestJ rj) throws MetadataWithSameAttributeException {
		Descriptor d = new Descriptor();
		ArrayList<Metadata> meta = new ArrayList<Metadata>();
		Metadata m;
		Attribute att;
		FuzzySet fs;
		
		//restrieving resource
		HashMap<String,Hashtable<String,Double>> hm = rj.getResource();
		Set attributes = hm.keySet();   // It will return all the keys in hm in the form of the Set
		for (Iterator i = attributes.iterator(); i.hasNext();) {
			String attribute = (String) i.next();
			Hashtable<String, Double> fuzzySet = hm.get(attribute);
			att = new Attribute(attribute);
			fs = FuzzySet.createFuzzySet(fuzzySet);
			m = new Metadata(att, fs, PossibilisticInterpretation.getinstance());
			meta.add(m);
		}
		d.setMetadata(meta);
		return d;
	}
	
	/*
	 * Creates a new Resource with identifier setted in the input request.
	 */
	private static Resource getResource(RequestJ rj) throws URISyntaxException {
		
		return new Resource(new URI(rj.resource_id));
	}
	
	/*
	 * Dummy method for the retrieving of an existent filter.
	 * Creates filter with the identifier setted in the input request.
	 */
	private static Filter getFilter(RequestJ rj) {
		
		Filter filter = null;
		
		Attribute attribute=new Attribute("genere");
		Attribute attribute2=new Attribute("editore");
		Attribute attribute3=new Attribute("autore");
		Attribute attribute4=new Attribute("anno");
		Attribute attribute5=new Attribute("versione");
		
		switch (rj.getFilterId()){
		case "filtro1": {
			FuzzySet fs_utente1=new FuzzySet();
			FuzzySet fs2_utente1=new FuzzySet();
			FuzzySet fs3_utente1=new FuzzySet();
			FuzzySet fs4_utente1=new FuzzySet();
			FuzzySet fs5_utente1=new FuzzySet();
			
			fs_utente1.setValue("fantasy", 1.0);
			fs_utente1.setValue("romanzo gotico", 1.0);
			fs_utente1.setValue("horror", 0.7);
			fs_utente1.setValue("thriller", 0.5);
			fs_utente1.setValue("fantascienza", 0.3);
			fs_utente1.setValue("romanzo distopico", 0.4);
			fs_utente1.setValue("steampunk", 0.3);
			fs_utente1.setValue("storico", 0.3);
			
			fs2_utente1.setValue("salani", 1.0);
			fs2_utente1.setValue("mondadori", 0.9);
			fs2_utente1.setValue("urania", 0.5);
			fs2_utente1.setValue("TEA", 0.4);
			
			fs3_utente1.setValue("J.K Rowling", 1.0);
			fs3_utente1.setValue("David Eddings", 1.0);
			fs3_utente1.setValue("Terry Brooks", 1.0);
			fs3_utente1.setValue("Anne Rice", 0.8);
			
			fs4_utente1.setValue("1980", 0.2);
			fs4_utente1.setValue("1981", 0.4);
			fs4_utente1.setValue("1982", 0.7);
			fs4_utente1.setValue("1983", 0.3);
			fs4_utente1.setValue("1984", 0.3);
			fs4_utente1.setValue("1985", 0.2);
			fs4_utente1.setValue("1986", 0.3);
			fs4_utente1.setValue("1987", 0.4);
			fs4_utente1.setValue("1988", 0.4);
			fs4_utente1.setValue("1989", 0.9);					
			fs4_utente1.setValue("1990", 1.0);			
			fs4_utente1.setValue("1991", 1.0);
			fs4_utente1.setValue("1992", 0.8);
			fs4_utente1.setValue("1993", 0.6);
			fs4_utente1.setValue("1994", 0.5);
			fs4_utente1.setValue("1995", 0.5);
			fs4_utente1.setValue("1996", 0.3);
			fs4_utente1.setValue("1997", 0.4);
			fs4_utente1.setValue("1998", 0.5);
			fs4_utente1.setValue("1999", 0.7);					
			fs4_utente1.setValue("2000", 0.9);
			fs4_utente1.setValue("2001", 1.0);
			fs4_utente1.setValue("2002", 0.9);
			fs4_utente1.setValue("2003", 1.0);
			fs4_utente1.setValue("2004", 0.8);
			fs4_utente1.setValue("2005", 0.7);
			fs4_utente1.setValue("2006", 0.9);
			fs4_utente1.setValue("2007", 1.0);
			
			fs5_utente1.setValue("rilegata", 1.0);
			fs5_utente1.setValue("brossura", 0.7);
			fs5_utente1.setValue("tascabile", 0.6);
			fs5_utente1.setValue("digitale", 0.8);
			
			Metadata metadata_utente1=new Metadata(attribute,fs_utente1,PossibilisticInterpretation.getinstance());
			Metadata metadata2_utente1=new Metadata(attribute2,fs2_utente1,PossibilisticInterpretation.getinstance());
			Metadata metadata3_utente1=new Metadata(attribute3,fs3_utente1,PossibilisticInterpretation.getinstance());
			Metadata metadata4_utente1=new Metadata(attribute4,fs4_utente1,PossibilisticInterpretation.getinstance());
			Metadata metadata5_utente1=new Metadata(attribute5,fs5_utente1,PossibilisticInterpretation.getinstance());
			
			DescriptionBasedFilter filtro_utente1=new DescriptionBasedFilter(metadata_utente1);
			DescriptionBasedFilter filtro2_utente1=new DescriptionBasedFilter(metadata2_utente1);
			DescriptionBasedFilter filtro3_utente1=new DescriptionBasedFilter(metadata3_utente1);
			DescriptionBasedFilter filtro4_utente1=new DescriptionBasedFilter(metadata4_utente1);
			DescriptionBasedFilter filtro5_utente1=new DescriptionBasedFilter(metadata5_utente1);
			
			Aggregator owa=new OWA(0.2,0.2,0.2,0.2,0.2); // wi è 1/5=0.2 (peso)
			
			filter=new ParallelFilter(owa, filtro_utente1,filtro2_utente1,filtro3_utente1,filtro4_utente1,filtro5_utente1);
		}; break;
		case "filtro2": {
			FuzzySet fs_utente2=new FuzzySet();

			fs_utente2.setValue("fantasy", 0.7); 
			fs_utente2.setValue("romanzo gotico", 0.1); 
			fs_utente2.setValue("horror", 1.0); 
			fs_utente2.setValue("thriller", 1.0);
			fs_utente2.setValue("fantascienza", 0.9); 
			fs_utente2.setValue("romanzo distopico", 0.2);
			fs_utente2.setValue("steampunk", 0.05); 
			fs_utente2.setValue("storico", 0.25); 
			fs_utente2.setValue("romanzo", 0.5);

			Metadata metadata_utente3=new Metadata(attribute,fs_utente2,PossibilisticInterpretation.getinstance());

			filter=new DescriptionBasedFilter(metadata_utente3);
		}; break;
		default: {
			Metadata metadata = new Metadata(new Attribute(""), new FuzzySet(), PossibilisticInterpretation.getinstance());
			filter = new DescriptionBasedFilter(metadata);
		}; break;
		}
		return filter;
	}
	
}	
