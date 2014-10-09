package services;

import com.google.inject.AbstractModule;

/**
 * Created by girishpandit on 10/8/14.
 */
public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DBService.class).to(DBServiceImpl.class).asEagerSingleton();
    }
}
