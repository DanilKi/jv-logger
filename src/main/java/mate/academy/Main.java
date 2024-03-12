package mate.academy;

import mate.academy.exception.AuthenticationException;
import mate.academy.model.User;
import mate.academy.service.AuthenticationService;
import mate.academy.service.AuthenticationServiceImpl;
import mate.academy.service.OrderService;
import mate.academy.service.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Application started.");
        AuthenticationService authenticationService = new AuthenticationServiceImpl();
        User user;
        try {
            user = authenticationService.login("bob", "1234");
        } catch (AuthenticationException e) {
            logger.error("Error on user login. ", e);
            return;
        }
        logger.info("User {} successfully logged in.", user.getLogin());
        OrderService orderService = new OrderServiceImpl();
        orderService.completeOrder(user.getUserId());
        logger.info("Order completed for user {}(id={}).", user.getLogin(), user.getUserId());
        logger.info("Application successfully finished.");
    }
}
