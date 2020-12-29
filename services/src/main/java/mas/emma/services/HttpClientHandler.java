/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.net.URI;
import mas.emma.data.statictypes.constants.ApplicationConstants;
import mas.emma.data.statictypes.constants.ControllerConstants;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.concurrent.Cancellable;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpMethod;

/**
 *
 * @author nlmsc
 */
public class HttpClientHandler extends BaseService {

    private final int timeoutSeconds = 100;
    private static Cancellable cancelToken;
    private CredentialsProvider provider;

    protected CredentialsProvider credentialsProviderInstance() {
        if (provider == null) {
            provider = new BasicCredentialsProvider();
            provider.setCredentials(
                    AuthScope.ANY,
                    new UsernamePasswordCredentials(Application.getSettings().getSec_u(), Application.getSettings().getSec_p())
            );
        }
        return provider;
    }

    public String getEntity(String uri, String user_id, HttpMethod method, HttpEntity entity) {

        return runHttpRequestWithErrorHandeling(() -> {
            HttpRequestBase request = getRequest(uri, user_id, method, entity);

            try ( CloseableHttpClient httpClient = HttpClientBuilder.create()
                    .setDefaultCredentialsProvider(credentialsProviderInstance())
                    .build();  CloseableHttpResponse response = httpClient.execute(request)) {

                // 401 if wrong user/password
                var httpStatus = response.getStatusLine().getStatusCode();

                HttpEntity returnEntity = null;
                String result = null;
                if (httpStatus == 200) {

                    returnEntity = response.getEntity();

                }
                if (returnEntity != null) {
                    // return it as a String
                    result = EntityUtils.toString(returnEntity);
                }

                logHttpRequest(user_id, method.name(), result, uri, String.valueOf(httpStatus));
                return result;
            }
        }
        );

    }

    private HttpRequestBase getRequest(String uri, String user_id, HttpMethod method, HttpEntity entity) throws JsonProcessingException {

        HttpRequestBase request = null;

        switch (method) {
            case GET ->
                request = new HttpGet(URI.create(uri));
            case POST -> {
                HttpPost post = new HttpPost(URI.create(uri));

                if (entity != null) {
                    post.setEntity(entity);
                    request = post;
                }
            }
            case PUT -> {
                HttpPut put = new HttpPut(URI.create(uri));
                if (entity != null) {
                    put.setEntity(entity);
                    request = put;
                }
            }
            case DELETE ->
                request = new HttpDelete(URI.create(uri));
            case PATCH ->
                request = new HttpPatch(URI.create(uri));
            default -> {
                return null;
            }

        }

        request.addHeader(ApplicationConstants.ACCEPT, ApplicationConstants.JSON);
        request.addHeader(ControllerConstants.HEADER_APP_ID, Application.getSettings().getApp_identifier());
        request.addHeader(ControllerConstants.HEADER_USERID, user_id);
        request.setCancellable(cancelToken);

        return request;
    }

}
