package com.tikalk.postoffice;
import com.tikalk.postoffice.mail.Mail;
import org.junit.Test;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

public class Demo3StreamVsObservable {



    @Test
    public void test() {

        List<Mail> mails = PostOffice.makeLetters();  // populate the Mail collection

        // === Java 8 Stream
        System.out.println("\n== Iterating over Java 8 Stream");

        mails.stream()
                .skip(1)
                .limit(3)
                .filter(m -> "Israel".equals(m.getCountry()))
                .map(b -> b.getAddress() + ": zip " + b.getZip())
                .forEach(Mail -> System.out.println(Mail));

        // === RxJava Observable

        Observable<Mail> observableMail = null;

        System.out.println("\n== Subscribing to Observable ");

        observableMail = Observable.from(mails);

        observableMail
                .skip(1)
                .take(3)
                .filter(m -> "Israel".equals(m.getCountry()))
                .map(b -> b.getAddress() + ": zip " + b.getZip())
                .subscribe(
                        Mail -> System.out.println(Mail),
                        err -> System.out.println(err),
                        () -> System.out.println("Streaming is complete")
        );
    }
}
