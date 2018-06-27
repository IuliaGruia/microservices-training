package ro.microservices.gateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class ZullLogFilter extends ZuulFilter {

    private static final Logger log = LoggerFactory.getLogger(ZullLogFilter.class);


    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = context.getRequest();
        log.info("Request to "+ httpServletRequest.getMethod() +"  "+ httpServletRequest.getRequestURL().toString());
                return null;
    }
}
