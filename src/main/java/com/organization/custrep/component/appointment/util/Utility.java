package com.organization.custrep.component.appointment.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Utility {

	public static  String serializer(@SuppressWarnings("rawtypes") Class clazz, Object model) throws JAXBException {
		JAXBContext contextObj;
		contextObj = JAXBContext.newInstance(clazz);
		Marshaller marshallerObj = contextObj.createMarshaller();
		marshallerObj.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		StringWriter wr = new StringWriter();
		marshallerObj.marshal(model, wr);
		return wr.toString();
	}
	
	
	public static  Object deserializer(@SuppressWarnings("rawtypes") Class clazz, String model) throws JAXBException {
		JAXBContext contextObj;
		contextObj = JAXBContext.newInstance(clazz);
		Unmarshaller unmarshallerObj = contextObj.createUnmarshaller();
		StringReader sr = new StringReader(model);
		return unmarshallerObj.unmarshal(sr);
	}
}
