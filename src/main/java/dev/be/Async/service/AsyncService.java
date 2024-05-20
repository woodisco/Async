package dev.be.Async.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsyncService {
    private final EmailService emailService;

    // ① Bean을 주입받은 경우 : 비동기 프로그래밍 가능
    public void asyncCall_1() {
        System.out.println("[asyncCall_1] :: " + Thread.currentThread().getName());
        emailService.sendMail();
        emailService.sendMailWithCustomThreadPool();
    }

    // ② 인스턴스를 이용한 경우 : 비동기 프로그래밍 불가능
    public void asyncCall_2() {
        System.out.println("[asyncCall_2] :: " + Thread.currentThread().getName());
        EmailService emailService = new EmailService();
        emailService.sendMail();
        emailService.sendMailWithCustomThreadPool();
    }

    // ③ 내부의 @Async를 이용한 경우 : 비동기 프로그래밍 불가능
    public void asyncCall_3() {
        System.out.println("[asyncCall_3] :: " + Thread.currentThread().getName());
        sendMail();
    }

    @Async
    public void sendMail() {
        System.out.println("[sendMail] :: " + Thread.currentThread().getName());
    }
}
