package pubsub.subsribers;

import pubsub.entities.Message;

public interface Subscriber {
    String getId();

    void onMessage(Message message);
}