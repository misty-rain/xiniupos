/*
 * @(#)Snippet.java		       Project: CrashHandler
 * Date: 2014-5-27
 *
 * Copyright (c) 2014 CFuture09, Institute of Software, 
 * Guangdong Ocean University, Zhanjiang, GuangDong, China.
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xiniu.pos.support.crashmanager;

import android.util.Log;

import java.util.Date;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Author: msdx (645079761@qq.com) Time: 14-5-27 上午9:07
 */
public class LogMail extends Authenticator {
    private String host;
    private String port;
    private String user;
    private String pass;
    private String from;
    private String to;
    private String subject;
    private String body;

    private Multipart multipart;
    private Properties props;

    public LogMail() {
    }

    public LogMail(String user, String pass, String from, String to, String host, String port,
            String subject, String body) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.pass = pass;
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public LogMail setHost(String host) {
        this.host = host;
        return this;
    }

    public LogMail setPort(String port) {
        this.port = port;
        return this;
    }

    public LogMail setUser(String user) {
        this.user = user;
        return this;
    }

    public LogMail setPass(String pass) {
        this.pass = pass;
        return this;
    }

    public LogMail setFrom(String from) {
        this.from = from;
        return this;
    }

    public LogMail setTo(String to) {
        this.to = to;
        return this;
    }

    public LogMail setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public LogMail setBody(String body) {
        this.body = body;
        return this;
    }

    public void init() {
        multipart = new MimeMultipart();
        // There is something wrong with MailCap, javamail can not find a
        // handler for the multipart/mixed part, so this bit needs to be added.
        MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
        mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
        mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
        mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
        mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
        mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
        CommandMap.setDefaultCommandMap(mc);

        props = new Properties();

        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.socketFactory.port", port);
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
    }

    public boolean send() throws MessagingException {
        if (!user.equals("") && !pass.equals("") && !to.equals("") && !from.equals("")) {
            Session session = Session.getDefaultInstance(props, this);
            Log.d("SendUtil", host + "..." + port + ".." + user + "..." + pass);

            MimeMessage msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(from));

            InternetAddress addressTo = new InternetAddress(to);
            msg.setRecipient(MimeMessage.RecipientType.TO, addressTo);

            msg.setSubject(subject);
            msg.setSentDate(new Date());

            // setup message body
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);
            multipart.addBodyPart(messageBodyPart);

            // Put parts in message
            msg.setContent(multipart);

            // send email
            Transport.send(msg);

            return true;
        } else {
            return false;
        }
    }

    public void addAttachment(String filePath, String fileName) throws Exception {
        BodyPart messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(filePath);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(fileName);
        multipart.addBodyPart(messageBodyPart);
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, pass);
    }
}