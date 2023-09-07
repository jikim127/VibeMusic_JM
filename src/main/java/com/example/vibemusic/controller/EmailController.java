package com.example.vibemusic.controller;

import org.springframework.stereotype.Controller;

@Controller
public class EmailController {

//    @Autowired
//    private JavaMailSender javaMailSender;
//
//    @PostMapping("/send-email")
//    @ResponseBody
//    public ResponseEntity<Object> sendEmail(@RequestBody Map<String, String> emailData) {
//        String recipientEmail = "admin@example.com"; // 관리자 이메일 주소 설정
//        String subject = emailData.get("subject");
//        String message = emailData.get("message");
//
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(recipientEmail);
//        mailMessage.setSubject(subject);
//        mailMessage.setText(message);
//
//        try {
//            javaMailSender.send(mailMessage);
//            return ResponseEntity.ok().body(Map.of("success", true));
//        } catch (MailException e) {
//            return ResponseEntity.ok().body(Map.of("success", false));
//        }
//    }
}