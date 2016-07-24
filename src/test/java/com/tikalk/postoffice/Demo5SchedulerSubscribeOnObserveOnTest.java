package com.tikalk.postoffice;

import com.tikalk.postoffice.PostOffice;
import com.tikalk.postoffice.mail.Mail;
import org.junit.Test;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class Demo5SchedulerSubscribeOnObserveOnTest {

    @Test
    public void test() {

        List<Mail> mails = PostOffice.makeLetters();

        Observable<Mail> observableMails = null;

        observableMails.from(mails)
                .subscribeOn(Schedulers.computation())  // push data on computation thread
                .doOnNext(Mail -> log(Mail))            // Side effect: Log on computation thread
                .observeOn(Schedulers.io())             // Process on another io thread
                .subscribe(mail -> putStamp(mail));

        // Sleep just to keep the program running
        try {
            Thread.sleep(5000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void putStamp(Mail mail) {
        try {
            System.out.println("** Putting stamp " + mail.getAddress() + " " + mail.getCountry() + " " + mail.getZip() +
                    " on " + Thread.currentThread().getName());

            Thread.sleep((int) (Math.random() * 500));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void log(Mail mail) {
        System.out.println("===> Logging " + mail.getAddress() + " " + mail.getCountry() + " " + mail.getZip() +
                " on " + Thread.currentThread().getName());
    }


}
