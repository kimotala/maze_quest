package com.daedalus.impl.remote;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

import com.daedalus.exception.MazeRequestException;

/**
 * A HTTP response handler to handle position check and solve requests
 *
 * @author kimo
 *
 */
public class ConfirmationRespHandler implements ResponseHandler<Boolean> {

	@Override
	public Boolean handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
		int status = response.getStatusLine().getStatusCode();
		Boolean ok = status == 200;

		if (!(status == 200 || status == 403)) {
			onUnexpectedResp(response);
		}
		return ok;
	}

	public void onUnexpectedResp(HttpResponse response) {
		int status = response.getStatusLine().getStatusCode();
		String str = "Encountered unexpected response. Status code: " + status ;
		// Note: We can handle the different class of error, 4xx or 5xx differently in this method.
		System.out.print(str);
		throw new MazeRequestException(str);
	}
}
