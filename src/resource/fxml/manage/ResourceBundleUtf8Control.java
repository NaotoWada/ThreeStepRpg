package resource.fxml.manage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ResourceBundleUtf8Control extends ResourceBundle.Control {

    private static final String SUFFIX = "properties";
    private static final String ENCODE = "UTF-8";

    @Override
    public ResourceBundle newBundle(String baseName, Locale locale, String format,
            ClassLoader loader, boolean reload)
            throws IllegalAccessException, InstantiationException, IOException {

        String bundleName = toBundleName(baseName, locale);
        String resourceName = toResourceName(bundleName, SUFFIX);

        try (InputStream is = loader.getResourceAsStream(resourceName);
                InputStreamReader isr = new InputStreamReader(is, ENCODE);
                BufferedReader reader = new BufferedReader(isr)) {
            return new PropertyResourceBundle(reader);
        }
    }
}
