package src.main.java.com.jcolaiacovo.armored.cars.service.application;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.ExecutorThreadPool;
import org.eclipse.jetty.util.thread.ThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.exception.ExceptionUtils.getRootCause;

public class ArmoredCarsApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArmoredCarsApplication.class);

    private ArmoredCarsApplication() {

    }

    public static void main(String[] args) {

        try {
            PropertyConfigurator.configure("log4j.properties");

            LOGGER.info("[main] Starting Armored Cars Application ...");

            Config root = ConfigFactory.parseFile(getFile());
            Config serverConf = root.getConfig("server");

            DispatcherServlet dispatcherServlet = new DispatcherServlet();
            dispatcherServlet.setContextClass(AnnotationConfigWebApplicationContext.class);
            String webDir = dispatcherServlet.getClass().getClassLoader().getResource("src/main/webapp").toExternalForm();
            dispatcherServlet.setContextConfigLocation("com.jcolaiacovo.armored.cars.service.application.config.WebBeansConfig");

            WebAppContext handler = new WebAppContext();
            handler.setContextPath("/armored-cars");
            handler.addServlet(new ServletHolder(dispatcherServlet), "/*");

            handler.setResourceBase(webDir);
            handler.setParentLoaderPriority(true);

            ThreadPool threadPool = new ExecutorThreadPool(20, 256, 60, TimeUnit.SECONDS);
            Server server = new Server(threadPool);

            ServerConnector serverConnector = getConnector(server, serverConf);
            server.setConnectors(new Connector[]{serverConnector});
            server.setHandler(handler);

            server.start();
            LOGGER.info("[main] Armored Cars started...");
            server.join();
            LOGGER.info("[main] Armored Cars is shutdown!");
        } catch (Exception e) {
            LOGGER.error("[main] Error starting Armored Cars:" + e.getMessage(), getRootCause(e));
        }
    }

    public static ServerConnector getConnector(Server server, Config serverConf) {
        ServerConnector serverConnector = new ServerConnector(server);
        serverConnector.setPort(serverConf.getInt("port"));
        return serverConnector;
    }

    private static File getFile() throws Exception {
        ClassPathResource classPathResource = new ClassPathResource("conf/env/startup.conf");

        File file = classPathResource.getFile();
        if (!file.exists()) {
            classPathResource = new ClassPathResource("startup.conf");
            file = classPathResource.getFile();
            if (!file.exists()) {
                throw new RuntimeException("startup.conf file not found in classpath (it should be in the root or inside conf/conf.env/) -- I don't know how to start dammit!");
            }
        }

        return file;
    }

}