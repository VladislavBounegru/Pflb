package ru.bounegru.task;

import ru.pflb.mq.dummy.implementation.ConnectionImpl;
import ru.pflb.mq.dummy.implementation.DestinationImpl;
import ru.pflb.mq.dummy.implementation.ProducerImpl;
import ru.pflb.mq.dummy.interfaces.Connection;
import ru.pflb.mq.dummy.interfaces.Destination;
import ru.pflb.mq.dummy.interfaces.Producer;
import ru.pflb.mq.dummy.interfaces.Session;

import java.util.ArrayList;
import java.util.List;

public class FirstTask {

    public FirstTask() {
    }

    public void resolve(){
        try (Connection connection = new ConnectionImpl();
             Session session = connection.createSession(true)) {
            Destination destination = new DestinationImpl("myQueue");
            Producer producer = new ProducerImpl(destination);
            List<String> messages = new ArrayList<String>() {{
                add("Раз");
                add("Два");
                add("Три");
            }};
            for (String message : messages) {
                producer.send(message);
                Thread.sleep(2000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
