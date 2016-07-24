package com.tikalk.postoffice;


import com.tikalk.postoffice.mail.Mail;
import org.junit.Test;
import rx.Observable;

import java.util.List;



public class Demo1HelloWorldTest {
    @Test
    public void test() {
        Observable<List<Mail>> mails = PostOffice.makeMail();

        mails
                .flatMap(mail -> Observable.from(mail))
                .subscribe(
                        data -> System.out.println("Subscriber received " + data),
                        (error) -> System.err.println(error),
                        () -> System.out.println("*** The stream is over ***")
                );
    }
}
