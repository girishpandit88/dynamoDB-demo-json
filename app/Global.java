import access.dynamo.DynamoAccessModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import play.Application;
import play.GlobalSettings;
import services.ServiceModule;

/**
 * Created by girishpandit on 10/8/14.
 */
public class Global extends GlobalSettings {
    private Injector injector;
    @Override
    public void onStart(final Application application) {
        injector = Guice.createInjector(
                new DynamoAccessModule(application.configuration().underlying()),
                new ServiceModule()
        );
    }
    @Override
    public <A> A getControllerInstance(Class<A> aClass) throws Exception {
        return injector.getInstance(aClass);
    }
}
