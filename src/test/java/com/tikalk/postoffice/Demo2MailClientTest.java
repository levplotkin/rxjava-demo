package com.tikalk.postoffice;

import com.tikalk.postoffice.mail.Mail;
import org.junit.Test;
import rx.Observable;
import rx.Subscriber;

public class Demo2MailClientTest {
    @Test
    public void test() {

        Observable<Mail> mails = MailServer.getData(); // no data coming in yet!!!

        mails
            .subscribe(new Subscriber<Mail>() {

                // Implementing the Observer
                public void onNext(Mail mail) {
                    System.out.println(mail);
                }

                public void onError(Throwable throwable) {
                    System.err.println("Client received: " + throwable.getMessage());
                }

                public void onCompleted() {
                    System.out.println("*** The stream is over ***");
                }
        });
    }
}