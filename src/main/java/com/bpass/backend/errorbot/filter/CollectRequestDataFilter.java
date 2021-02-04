package com.bpass.backend.errorbot.filter;

import com.bpass.backend.errorbot.util.AgentUtils;
import com.bpass.backend.errorbot.util.MdcUtil;
import com.bpass.backend.errorbot.util.RequestWrapper;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Request 정보들을 수집하여 MDC에 보관하는 필터.
 *
 * @author always0ne
 * @version 1.0
 */
public class CollectRequestDataFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    RequestWrapper requestWrapper = RequestWrapper.of(request);

    MdcUtil.setJsonValueAndPutMdc(MdcUtil.HEADER_MAP_MDC, requestWrapper.headerMap());
    MdcUtil.setJsonValueAndPutMdc(MdcUtil.PARAMETER_MAP_MDC, requestWrapper.parameterMap());
    MdcUtil.setJsonValueAndPutMdc(MdcUtil.BODY_MDC, requestWrapper.body());
    MdcUtil.setJsonValueAndPutMdc(MdcUtil.AGENT_DETAIL_MDC, AgentUtils.getAgentDetail((HttpServletRequest) request));
    MdcUtil.putMdc(MdcUtil.REQUEST_URI_MDC, requestWrapper.getRequestUri());

    try {
      chain.doFilter(request, response);
    } finally {
      MdcUtil.clear();
    }
  }
}