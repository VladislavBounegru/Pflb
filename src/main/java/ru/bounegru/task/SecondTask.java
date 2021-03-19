package ru.bounegru.task;

import ru.pflb.mq.dummy.implementation.ConnectionImpl;
import ru.pflb.mq.dummy.implementation.DestinationImpl;
import ru.pflb.mq.dummy.implementation.ProducerImpl;
import ru.pflb.mq.dummy.interfaces.Connection;
import ru.pflb.mq.dummy.interfaces.Destination;
import ru.pflb.mq.dummy.interfaces.Producer;
import ru.pflb.mq.dummy.interfaces.Session;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class SecondTask {

    public void resolve(String pathToFile) {
        try (Connection connection = new ConnectionImpl();
             Session session = connection.createSession(true)) {
            Destination destination = new DestinationImpl("myQueue");
            Producer producer = new ProducerImpl(destination);
            RandomAccessFile randomAccessFile = new RandomAccessFile(new File(pathToFile), "r");
            while (true) {
                String current = randomAccessFile.readLine();
                if (current == (null)) {
                    randomAccessFile.seek(0);
                }
                producer.send(current);
                Thread.sleep(2000);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
