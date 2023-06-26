

package com.mita.agent.ws.servlet;

import com.mita.agent.ws.adapter.MobileFrameSocketAdapter;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class MobileFrameServlet extends WebSocketServlet {
  @Override
  public void configure(WebSocketServletFactory webSocketServletFactory) {
    webSocketServletFactory.getPolicy().setIdleTimeout(3600000);
    webSocketServletFactory.getPolicy().setMaxBinaryMessageBufferSize(100 * 1024 * 1024);
    webSocketServletFactory.getPolicy().setMaxTextMessageSize(1024 * 1024 * 1024);
    webSocketServletFactory.register(MobileFrameSocketAdapter.class);
  }
}
