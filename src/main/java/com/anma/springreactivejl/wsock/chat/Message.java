package com.anma.springreactivejl.wsock.chat;

import java.util.Date;

record Message(String clientId, String text, Date when) {
}