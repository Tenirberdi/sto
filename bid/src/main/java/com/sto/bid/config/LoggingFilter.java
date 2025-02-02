package com.sto.bid.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import static com.sto.bid.model.constant.Constants.HEADER_REQUEST_ID;
import static com.sto.bid.model.constant.Constants.MDC_REQUEST_ID;

@Slf4j
@Component
public class LoggingFilter extends GenericFilterBean {
    public static final List<String> NOT_LOG_PATTERN_LIST = List.of("/swagger", "/v3/api-docs");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        MutableRequestWrapper requestWrapper = new MutableRequestWrapper((HttpServletRequest) request);
        MutableResponseWrapper responseWrapper = new MutableResponseWrapper((HttpServletResponse) response);
        String uri = ((HttpServletRequest) request).getRequestURI();
        try {
            boolean doLog = NOT_LOG_PATTERN_LIST.stream().noneMatch(pattern -> Pattern.matches(String.format(".*%s.*", pattern), uri));
            if (doLog) {
                MDC.put(MDC_REQUEST_ID, getRequestId(request));

                String body = IOUtils.toString(requestWrapper.getInputStream(), request.getCharacterEncoding());
                log.info(
                        "Request[uri: {}, content-type: {}, method: {}, address: {}, query: {}, body: {}]",
                        ((HttpServletRequest) request).getRequestURI(),
                        ((HttpServletRequest) request).getContentType(),
                        ((HttpServletRequest) request).getMethod(),
                        request.getRemoteAddr(),
                        ((HttpServletRequest) request).getQueryString(),
                        body
                );
            }
            filterChain.doFilter(requestWrapper, responseWrapper);
            if (doLog) {
                log.info("Response[status: {}, body: {}]", ((HttpServletResponse) response).getStatus(), responseWrapper.getResponseContent());
            }
            IOUtils.write(responseWrapper.getResponseContent(), response.getOutputStream(), StandardCharsets.UTF_8);
        } finally {
            MDC.clear();
        }
    }

    private static String getRequestId(ServletRequest req) {
        String requestId = null;
        if (req instanceof HttpServletRequest) {
            requestId = ((HttpServletRequest) req).getHeader(HEADER_REQUEST_ID);
        }

        if (requestId == null || requestId.isEmpty()) {
            requestId = UUID.randomUUID().toString();
        }
        return requestId;
    }
}
