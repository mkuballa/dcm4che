package org.dcm4che3.hl7;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

public class HL7MessageTest {

	@Test
	public void testHL7MessageParsing() throws IOException{
		try (InputStream in = getClass().getResourceAsStream("/data/ORM^O01.hl7")) {
			byte[] messageArray = IOUtils.toByteArray(in);
			HL7Message hl7Message = HL7Message.parse(messageArray, "UTF-8");
			Assert.assertEquals("check ORM^O01 message type", "ORM^O01", hl7Message.getSegment("MSH").getMessageType());
		}
	}
	
	@Test
	public void testHL7MessageAck() throws IOException{
		try (InputStream in = getClass().getResourceAsStream("/data/ORM^O01.hl7")) {
			byte[] messageArray = IOUtils.toByteArray(in);
			HL7Message hl7Message = HL7Message.parse(messageArray, "UTF-8");
			Assert.assertEquals("check ORM^O01 message type", "ORM^O01", hl7Message.getSegment("MSH").getMessageType());
			HL7Message ack = HL7Message.makeACK(hl7Message.getSegment("MSH"), HL7Exception.AA, "testCase");
			Assert.assertEquals("check if Message is an ack-message", "ACK", ack.getSegment("MSH").getField(8, ""));
		}
	}
	
}
