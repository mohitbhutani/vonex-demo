package demo.vonex.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by mohit bhutani on 25/10/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InitialRequest {
    String request;

    public String getRequest() {
        return request;
    }

    public InitialRequest setRequest(String request) {
        this.request = request;
        return this;
    }

    @Override
    public String toString() {
        return "InitialRequest{" +
                "request='" + request + '\'' +
                '}';
    }
}
