package com.cj.cga101g1.chatRoom;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/*
 * 1. @EnableWebSocketMessageBroker：
 * 這個註解表示我們將使用基於代理的 STOMP WebSocket 消息處理。
 * 它允許在 WebSocket 連接上使用 STOMP 訊息協議，並使用消息代理（如內置的 SimpleBroker 或外部的 RabbitMQ、ActiveMQ 等）將消息路由到其他客戶端。
 *
 * 2. WebSocketMessageBrokerConfigurer 介面：
 * 這個接口允許我們自定義 WebSocket 和 STOMP 訊息代理的配置。
 *
 */


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /*
     * 這個方法允許我們配置訊息代理。
     * 1.config.enableSimpleBroker：
     *  （SimpleBroker）是 Spring WebSocket 的一個輕量級組件，它可以將消息緩存在內存中，然後將這些消息分發給訂閱相應主題的客戶端。
     *  它非常適合快速開發和原型設計，但對於大規模生產環境，您可能需要考慮使用更強大的消息代理，如 RabbitMQ 或 Apache ActiveMQ。
     *  這使得客戶端可以訂閱以 "/topic" 開頭的主題，以便接收來自服務器的消息，這邊使用/topic/channelA
     *
     * 2.config.setApplicationDestinationPrefixes
     *   設置了應用程序目的地前綴，該前綴將用於客戶端發送消息的目的地。
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }


    /*
     *  這個方法允許我們註冊 WebSocket 端點，客戶端將使用這些端點連接到我們的 WebSocket 服務器。
     *  1.registry.addEndpoint：
     *  這個方法用於註冊 WebSocket 端點。
     * 在此示例中，我們註冊了一個端點，路徑為 "/chat"。
     * 客戶端將使用此路徑來建立與 WebSocket 服務器的連接。
     *  withSockJS() 方法的使用表示我們希望支持 SockJS。SockJS 是一個 JavaScript 函式庫，它提供了一個 WebSocket 的模擬實現。當 WebSocket 不可用（例如，由於瀏覽器兼容性問題）時，它會自動使用其他可用的通信方式（如 AJAX 長輪詢）作為後備。使用 SockJS 可以提高應用程序的兼容性和穩定性。
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat").withSockJS();
    }
}
