package fr.iut;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import fr.iut.club.PutterExperimental;
import fr.iut.club.Wood;
import fr.iut.club.WoodExperimental;

/**
 * The Guice driven Container
 */
public class MainModuleExperimental extends AbstractModule {
    @Override
    protected final void configure() {
        bind(Caddy.class);
        bind(Club.class)
                .annotatedWith(Names.named("Putter"))
                .to(PutterExperimental.class);
        bind(Club.class)
                .annotatedWith(Names.named("Wood"))
                .to(WoodExperimental.class);
    }
}
