package demo.vonex.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by mohit bhutani on 25/10/16.
 */
public class ApiResponseErrorHandler implements ResponseErrorHandler {

    private static Logger logger = Logger.getLogger(ApiResponseErrorHandler.class.getName());
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().series().equals(HttpStatus.Series.CLIENT_ERROR)
                || response.getStatusCode().series().equals(HttpStatus.Series.SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        logger.severe("error in api "+response.getStatusCode()+ " "+ response.getStatusText());
    }
}
