package net.sdo.stockrest;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

@Compress
public class GZIPWriterInterceptor implements WriterInterceptor {
    @Context
    private HttpHeaders requestHeaders;

    @Override
    public void aroundWriteTo(WriterInterceptorContext context)
    		throws IOException, WebApplicationException {
	String header = requestHeaders.getHeaderString("Accept-Encoding");
	if (header == null || header.indexOf("gzip") < 0) {
	    context.proceed();
	    return;
	}
	context.getHeaders().putSingle("Content-Encoding", "gzip");
	OutputStream outputStream = context.getOutputStream();
	context.setOutputStream(new GZIPOutputStream(outputStream));
	context.proceed();
    }
}
