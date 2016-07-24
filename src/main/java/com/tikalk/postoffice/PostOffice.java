package com.tikalk.postoffice;


import com.tikalk.postoffice.mail.Letter;
import com.tikalk.postoffice.mail.Mail;
import com.tikalk.postoffice.mail.Package;
import com.tikalk.postoffice.mail.PostCard;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

public class PostOffice {

    static private List<List<? extends Mail>> mail = new ArrayList<>();

    static public List<Mail> makePostCards() {
        List<Mail> postcards = new ArrayList<>();
        long sentTime = System.currentTimeMillis();
        postcards.add(PostCard.builder().country("Israel").zip(123).sentTime(sentTime).build());
        postcards.add(PostCard.builder().country("Israel").zip(345).sentTime(sentTime).build());
        postcards.add(PostCard.builder().country("Israel").zip(456).sentTime(sentTime).build());
        return postcards;
    }

    static public List<Mail> makeLetters() {
        List<Mail> letters = new ArrayList<>();
        long sentTime = System.currentTimeMillis();
        letters.add(Letter.builder().country("Israel").address("Hertzl 1").zip(456).sentTime(sentTime).certified(true).build());
        letters.add(Letter.builder().country("Israel").address("Aba Hilel 2").zip(345).sentTime(sentTime).certified(false).build());
        letters.add(Letter.builder().country("Israel").address("Begin 3").zip(123).sentTime(sentTime).certified(false).build());
        letters.add(Letter.builder().country("Israel").address("Fisher 4").zip(345).sentTime(sentTime).certified(true).build());
        return letters;
    }

    static public List<Mail> makePackages() {
        List<Mail> packages = new ArrayList<>();
        long sentTime = System.currentTimeMillis();
        packages.add(Package.builder().country("Israel").zip(345).sentTime(sentTime).weight(12).build());
        packages.add(Package.builder().country("Israel").zip(456).sentTime(sentTime).weight(2).build());
        return packages;
    }

    public static Observable<List<Mail>> makeMail() {

        Observable<List<Mail>> mail = Observable.create(subscriber -> {

            subscriber.onNext(makeLetters());   // push the letters
            subscriber.onNext(makePackages()); // push the packages
            subscriber.onNext(makePostCards()); // push the postcards

            subscriber.onCompleted();
        });

        return mail;
    }
}
