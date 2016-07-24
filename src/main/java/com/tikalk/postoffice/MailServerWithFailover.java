package com.tikalk.postoffice;

import com.tikalk.postoffice.PostOffice;
import com.tikalk.postoffice.mail.Mail;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

public class MailServerWithFailover {
    static List<Mail> mailStock = new ArrayList<>();

    public static Observable<Mail> getData() {

        mailStock = PostOffice.makeLetters();
        System.out.println("*** Getting Mails from the main data source ***");

        // Create an observable passing subscriber (implements Observer)
        // provided by the client
        return
                Observable.create(subscriber -> {
                    try {
                        for (int i = 0; i < mailStock.size(); i++) {

                            subscriber.onNext(mailStock.get(i));

                            Thread.sleep(500); // Emulating delay in getting data

                            if (Math.random() < 0.3) {  // Emulating data error
                                throw new Throwable("random fake error");
                            }
                        }
                    } catch (Throwable err) {
                        subscriber.onError(new Throwable("Error in getting Mail info"));
                    }

                    subscriber.onCompleted();
                });
    }


    public static Observable<Mail> getDataFromAnotherServer() {

        System.out.println("*** Getting Mails from the ALTERNATIVE data source ***");

        return
                Observable.create(subscriber -> {
                    try {
                        for (int i = 0; i < mailStock.size(); i++) {

                            subscriber.onNext(mailStock.get(i));

                            Thread.sleep(1000); // Emulating delay in getting data

                        }
                    } catch (Throwable err) {
                        subscriber.onError(new Throwable("Error in getting Mail info"));
                    }

                    subscriber.onCompleted();
                });
    }
}

