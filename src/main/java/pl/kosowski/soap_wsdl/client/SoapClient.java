package pl.kosowski.soap_wsdl.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.kosowski.soap_wsdl.soapWsdl.RegisterCallRequest;
import pl.kosowski.soap_wsdl.soapWsdl.RegisterCallResponse;
import pl.kosowski.soap_wsdl.soapWsdl.ResultsRequest;
import pl.kosowski.soap_wsdl.soapWsdl.ResultsResponse;

@Service
public class SoapClient {
    private final SoapConnector soapConnector;
    private final String url;

    @Autowired
    public SoapClient(@Value("${webservice.url}") String url, SoapConnector soapConnector) {
        this.url = url;
        this.soapConnector = soapConnector;
        getRegisterCallRequest();
        getResultsRequest();
    }

    private void getRegisterCallRequest() {
        RegisterCallRequest request = new RegisterCallRequest();
        request.setStudent("123");
        RegisterCallResponse response = (RegisterCallResponse) soapConnector.callWebService(url, request);

        System.out.println("Name: " + response.getExercise().getName());
        System.out.println("Note: " + response.getExercise().getNote());
    }

    private void getResultsRequest() {
        ResultsRequest request = new ResultsRequest();
        request.setStudent("123");
        ResultsResponse response = (ResultsResponse) soapConnector.callWebService(url, request);

        response.getEntry().forEach(output -> System.out.println("Entry: " + output));
    }
}