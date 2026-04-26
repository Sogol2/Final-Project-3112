package Strategy;
import java.util.ArrayList;
import java.util.List;

import Interfaces.IApplication;
import Interfaces.ISearchStrategy;

public class DateSearchStrategy implements ISearchStrategy {
    @Override
    public List<IApplication> search(List<IApplication> apps, String query) {
        List<IApplication> results = new ArrayList<>();

        for (IApplication app : apps) {
            if (app.getStartDate().equalsIgnoreCase(query)) {
                results.add(app);
            }
        }

        return results;
    }
}
