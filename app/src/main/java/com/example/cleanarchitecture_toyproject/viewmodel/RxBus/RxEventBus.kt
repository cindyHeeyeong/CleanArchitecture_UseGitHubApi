package com.example.cleanarchitecture_toyproject.viewmodel.RxBus

import android.util.Log
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

//참고 자료 :  https://nittaku.tistory.com/216
class RxEventBus private constructor() {

    /*
    BehaviorSubject vs PublishSubject
    behaviorsubject : 구독 시 최신 정보를 발행한다.
    publishSubject : 구독시점에서 첫번째 아이템이 안나온다. (bus로 쓰기 알맞다)
     */
    private val subject: BehaviorSubject<Any> //중개자


    /*
      subject가 간직하고 있는 object를 return해준다
     */
    val bus: Observable<Any>
        get() {
            Log.d("RxEventBus", "getBus")
            return subject
        }


    init {
        subject = BehaviorSubject.create()
    }


    //sendbus , getbus object type이 같아야 한다.
    /*
    * object를 인자로 받아서 중개자인 subject를 이용하여, object를 간직하고 있는다.
    * */
    fun sendBus(`object`: Any) {
        Log.d("RxEventBus", "sendBus")
        subject.onNext(`object`)
    }

    companion object {

        private var rxEventBus: RxEventBus? = null

        //singleton 생성
        val instance: RxEventBus?
            get() {
                if (rxEventBus == null) {
                    rxEventBus = RxEventBus()
                }
                return rxEventBus
            }
    }
}
