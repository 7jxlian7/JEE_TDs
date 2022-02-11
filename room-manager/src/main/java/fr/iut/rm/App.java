package fr.iut.rm;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;
import fr.iut.rm.control.ControlRoom;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Entry point for command-line program. It's mainly a dumb main static method.
 */
public final class App {
    /**
     * quit constant
     */
    private static final String QUIT = "q";
    /**
     * help constant
     */
    private static final String HELP = "h";
    /**
     * create constant
     */
    private static final String CREATE = "c";
    /**
     * remove constant
     */
    private static final String REMOVE = "r";
    /**
     * list constant
     */
    private static final String LIST = "l";

    /**
     * standard logger
     */
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    /**
     * the available options for CLI management
     */
    private final Options options = new Options();

    @Inject
     ControlRoom cr;

    /**
     * Invoked at module initialization time
     */
    public App() {
        // build options command line options
        options.addOption(OptionBuilder.withDescription("List all rooms").create(LIST));
        options.addOption(OptionBuilder.withArgName("name description").hasArgs(2).withDescription("Create new room").create(CREATE));
        options.addOption(OptionBuilder.withArgName("name").hasArgs().withDescription("Remove a room").create(REMOVE));
        options.addOption(OptionBuilder.withDescription("Display help message").create(HELP));
        options.addOption(OptionBuilder.withDescription("Quit").create(QUIT));
    }



    /**
     * Displays help message
     */
    private void showHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("room-manager.jar", options);
    }

    public void run() {
        CommandLineParser parser = new BasicParser();
        CommandLine cmd = null;
        Scanner sc = new Scanner(System.in);
        do {
            String str = sc.nextLine();
            try {
                cmd = parser.parse(options, str.split(" "));
                if (cmd.hasOption(HELP)) {
                    showHelp();
                } else if (cmd.hasOption(LIST)) {
                    cr.showRooms();
                } else if (cmd.hasOption(CREATE)) {
                    String args[] = cmd.getOptionValues(CREATE);
                    String name = args[0];
                    String description = args[1];
                    if (name != null && !name.isEmpty() && description != null && !description.isEmpty()) {
                        cr.createRoom(name, description);
                    }
                } else if (cmd.hasOption(REMOVE)) {
                    String name = cmd.getOptionValue(REMOVE);
                    if(name != null && !name.isEmpty()){
                        cr.removeRoom(name);
                    }
                }

            } catch (ParseException e) {
                e.printStackTrace();
                showHelp();
            }
        } while (!cmd.hasOption(QUIT));
    }

    /**
     * Main program entry point
     *
     * @param args main program args
     */
    public static void main(final String[] args) {
        logger.info("Room-Manager version {} started", Configuration.getVersion());
        logger.debug("create guice injector");
        Injector injector = Guice.createInjector(new JpaPersistModule("room-manager"), new MainModule());
        logger.info("starting persistency service");
        PersistService ps = injector.getInstance(PersistService.class);
        ps.start();

        App app =  injector.getInstance(App.class);

        app.showHelp();
        app.run();

        logger.info("Program finished");
        System.exit(0);
    }


}
