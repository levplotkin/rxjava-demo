package com.tikalk.postoffice;

import com.tikalk.postoffice.mail.Mail;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

public class MailServer {

    static List<Mail> mailStock = new ArrayList<>();



    public static Observable<Mail> getData(){

        mailStock = PostOffice.makeLetters();
        System.out.println("*** Getting Mails from the main data source ***");

        // Create an observable passing subscriber (implements Observer)
        // provided by the client

        return
            Observable.create(subscriber -> {

                for (int i = 0; i < mailStock.size(); i++) {

                    subscriber.onNext(mailStock.get(i));

                    try {
                        Thread.sleep(500); // Emulating delay in getting data
                    } catch (InterruptedException e) {
                        subscriber.onError(new Throwable("Error in getting Mail info"));
                    }
                }

                subscriber.onCompleted();
        });
    }
}
