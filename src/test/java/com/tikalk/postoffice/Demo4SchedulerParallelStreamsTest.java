package com.tikalk.postoffice;

import com.tikalk.postoffice.PostOffice;
import com.tikalk.postoffice.mail.Mail;
import org.junit.Test;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.List;

public class Demo4SchedulerParallelStreamsTest {
    @Test
    public void test() {
        List<Mail> mails = PostOffice.makeLetters();  // populate the Mail collection

        Observable<Mail> observableMails = Observable.from(mails);

        observableMails
                .flatMap(Mail -> Observable.just(Mail)
                               .subscribeOn(Schedulers.computation())  // new thread for each observable
                               .map(mail -> putStamp(mail))
                 )

                .subscribe(mail -> System.out.println("Subscriber got " +
                                   mail.getAddress() + " on  " +
                                   Thread.currentThread().getName())
                );


        // Just to keep the program running
        try {
            Thread.sleep(5000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static Mail putStamp(Mail mail){
        try {
            System.out.println("** Putting stamp on  " + mail.getAddress() +" " + mail.getCountry() + " " +mail.getZip() +
                    " on " + Thread.currentThread().getName());

            Thread.sleep((int)(Math.random()*500));
            return mail;

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
