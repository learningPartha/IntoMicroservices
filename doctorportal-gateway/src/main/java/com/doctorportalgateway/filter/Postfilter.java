package com.doctorportalgateway.filter;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Configuration
public class Postfilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(Postfilter.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletResponse response = ctx.getResponse();
		log.info("Response status = {}",response.getStatus());
		
		try(InputStream is = ctx.getResponseDataStream()) {
			String responseData = CharStreams.toString(new InputStreamReader(is,CharEncoding.UTF_8));
			log.info("Response Data = {}",responseData);
			ctx.setResponseBody(responseData);
		}catch(IOException ioe) {
			ioe.printStackTrace();
			log.info(ioe.toString());
		}
		
		return null;
	}

}
