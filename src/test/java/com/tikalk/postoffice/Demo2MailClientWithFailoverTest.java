package com.tikalk.postoffice;

import com.tikalk.postoffice.MailServerWithFailover;
import com.tikalk.postoffice.mail.Mail;
import org.junit.Test;
import rx.Observable;

public class Demo2MailClientWithFailoverTest {
    @Test
    public void test() {

        Observable<Mail> mailData = MailServerWithFailover.getData(); // get data from main server

        mailData
                .onErrorResumeNext(err -> {
                    System.out.println("!!! Switching to an alternative data source because of : " + err.getMessage());
                    return MailServerWithFailover.getDataFromAnotherServer();
                })   // get data from alternative server
                .subscribe(
                        // Implementing the observer
                        mail -> System.out.println(mail),
                        (error) -> System.err.println("Client received: " + error.getMessage()),
                        () -> System.out.println("*** The stream is over ***")
                );
    }
}