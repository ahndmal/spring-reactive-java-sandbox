package com.anma.springreactivejl.wsock.chat;

import org.springframework.web.reactive.socket.WebSocketSession;

record Connection(String id, WebSocketSession session) {
}