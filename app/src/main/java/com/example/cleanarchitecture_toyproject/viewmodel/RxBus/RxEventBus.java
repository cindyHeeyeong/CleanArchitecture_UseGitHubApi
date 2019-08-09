package com.example.cleanarchitecture_toyproject.viewmodel.RxBus;

import android.util.Log;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

//참고 자료 :  https://nittaku.tistory.com/216
public class RxEventBus {

    private static RxEventBus rxEventBus;

    /*
    BehaviorSubject vs PublishSubject
    behaviorsubject : 구독 시 최신 정보를 발행한다.
    publishSubject : 구독시점에서 첫번째 아이템이 안나온다. (bus로 쓰기 알맞다)
     */
    private final BehaviorSubject<Object> subject; //중개자


    private RxEventBus() {
        subject = BehaviorSubject.create();
    }

    //singleton 생성
    public static RxEventBus getInstance() {
        if(rxEventBus==null) {
            rxEventBus = new RxEventBus();
        }
        return rxEventBus;
    }


    //sendbus , getbus object type이 같아야 한다.
    /*
    * object를 인자로 받아서 중개자인 subject를 이용하여, object를 간직하고 있는다.
    * */
    public void sendBus(Object object) {
        Log.d("RxEventBus","sendBus");
        subject.onNext(object);
    }


    /*
      subject가 간직하고 있는 object를 return해준다
     */
    public Observable<Object> getBus() {
        Log.d("RxEventBus","getBus");
        return subject;
    }
}
