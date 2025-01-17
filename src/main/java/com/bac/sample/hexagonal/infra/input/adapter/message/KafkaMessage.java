package com.bac.sample.hexagonal.infra.input.adapter.message;

import com.bac.sample.hexagonal.infra.input.port.MessageBrokerInputPort;
import com.bac.sample.hexagonal.infra.utils.ConversionUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class KafkaMessage {

    final
    MessageBrokerInputPort messageBrokerInputPort;

    public KafkaMessage(MessageBrokerInputPort messageBrokerInputPort) {
        this.messageBrokerInputPort = messageBrokerInputPort;
    }

    @KafkaListener(topicPattern = "dbserver1.public.*", groupId = "group1")
    public void consumeEvent(@Payload(required = false) String eventMsg) {
        if (eventMsg == null) return;

        Map<String, Object> event = ConversionUtils.jsonstring2Map(eventMsg);

        Map<String, Object> payload = (Map<String, Object>) event.get("payload");
        String operation = (String) payload.get("op");
        String table = (String) ((Map<String, Object>) payload.get("source")).get("table");

        if (operation.equals("u")) {
            messageBrokerInputPort.updateReg(table, (Map<String, Object>) payload.get("after"));
        } else if (operation.equals("c")) {
            messageBrokerInputPort.insertReg(table, (Map<String, Object>) payload.get("after"));
        } else if (operation.equals("d")) {
            messageBrokerInputPort.deleteReg(table, (Map<String, Object>) payload.get("before"));
        }
    }

}
