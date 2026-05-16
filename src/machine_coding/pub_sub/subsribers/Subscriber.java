package machine_coding.pub_sub.subsribers;

import machine_coding.pub_sub.entities.Message;

public interface Subscriber {
    String getId();

    void onMessage(Message message);
}