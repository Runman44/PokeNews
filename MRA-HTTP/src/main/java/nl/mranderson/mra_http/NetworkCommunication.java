package nl.mranderson.mra_http;


public interface NetworkCommunication {

    //Callback right? 
    Response request(RequestBuilder requestBuilder);

}
