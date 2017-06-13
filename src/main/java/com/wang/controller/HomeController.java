package com.wang.controller;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.wang.pojo.User;
import com.wang.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by wang on 17-5-27.
 */

@Controller
@RequestMapping("/home")
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/myindex")
    public String index() {
        System.out.println("This is myindex");
        LOGGER.info("the first jsp pages");

        return "myindex";
    }

    @RequestMapping("/users")
    public String getUsers() {
        System.out.println("This is getUsers");
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
//        ApplicationContext context = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/spring-context.xml");
//        IUserService userService = (IUserService) context.getBean("userService");


        User user = userService.get("1");
        System.out.println("This is user:" + user.toString());
        LOGGER.info(user.toString());
        return "myindex";
    }


    @RequestMapping("/users/add")
    public String addUser() {
        return "addUser";
    }

    @RequestMapping("/users/addUserP")
    public String insertUser(ModelAndView modelAndView, HttpServletRequest request) {
        Map<String, String[]> maps = request.getParameterMap();
        for (String key : maps.keySet()) {
            System.out.println("key:" + key + " --------- ");
            for (String value : maps.get(key)) {
                System.out.println("value:" + value);
            }
        }

        String nickname = request.getParameter("nickname");
        System.out.println("NICK:" + nickname);

        User user = new User();
        user.setNickname(nickname);
        user.setPassword(request.getParameter("password"));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));

        userService.insert(user);

        return "forward:/home/users/list";
    }

    @RequestMapping("/users/list")
    public ModelAndView list() {

        List<User> list = userService.getList();
        System.out.println("user list:" + list.size());

        ModelAndView modelAndView = new ModelAndView("UserList");
        modelAndView.addObject("list", list);

        return modelAndView;
    }

    @RequestMapping("/json")
    @ResponseBody
    public User userToJson() {

        User user = userService.get("1");

        return user;
    }

    @RequestMapping("/jsonList")
    @ResponseBody
    public List<User> listToJson() {
        List<User> list = userService.getList();
        return list;
    }


    //----- RocketMQ ----
    @RequestMapping("/send")
    @ResponseBody
    public String send() {
        DefaultMQProducer producer = new DefaultMQProducer("group2");
        try {
            producer.setNamesrvAddr("localhost:9876");
            producer.setInstanceName("producer_instance");
            producer.start();
            Message msg = new Message("Topic1", "tag1", "Order1", "msg_bytes".getBytes());
            SendResult sendResult = producer.send(msg);
            System.out.println(sendResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            producer.shutdown();
        }

        return "Send ";
    }

    @RequestMapping("/consume")
    @ResponseBody
    public String consume() {
        System.out.println("consume");
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer_group1");
        try {
            consumer.setNamesrvAddr("localhost:9876");
            consumer.setInstanceName("consumer_instance1");
            consumer.setMessageModel(MessageModel.CLUSTERING);

            consumer.subscribe("Topic1", "*");
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.setConsumeTimestamp("2017042221800");
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                    System.out.println("Consumer:" + Thread.currentThread().getName() + ", msgs:" + list);
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

        return "Consume";
    }

    //------end-------
}
