package machine_coding.library_management_system.states;

import machine_coding.library_management_system.entites.BookCopy;
import machine_coding.library_management_system.entites.Member;

public interface ItemState {
    void checkout(BookCopy copy, Member member);

    void returnItem(BookCopy copy);

    void placeHold(BookCopy copy, Member member);
}
