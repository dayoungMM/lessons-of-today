package ch8.item55;

import java.util.Optional;

public class Email {

    public void findUserEmailOrElse() {
        String userEmail = "Empty";
        String result = Optional.ofNullable(userEmail).orElse(getUserEmail());
        System.out.println(result);
    }

    public void findUserEmailOrElseGet() {
        String userEmail = "Empty";
        String result = Optional.ofNullable(userEmail).orElseGet(this::getUserEmail);
        System.out.println(result);
    }

    private String getUserEmail() {
        System.out.println("getUserEmail() Called");
        return "mangkyu@tistory.com";
    }


}
