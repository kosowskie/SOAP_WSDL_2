package pl.kosowski.soap_wsdl.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapConfig {

    @Value("${webservice.url}")
    private String webserviceUrl;

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("pl.kosowski.soapWsdl");
        return marshaller;
    }

    @Bean
    public SoapConnector soapConnector(Jaxb2Marshaller marshaller) {
        SoapConnector client = new SoapConnector();
        client.setDefaultUri(webserviceUrl);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}